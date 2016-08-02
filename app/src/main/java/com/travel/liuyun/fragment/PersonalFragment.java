package com.travel.liuyun.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.travel.liuyun.Constants;
import com.travel.liuyun.R;
import com.travel.liuyun.bean.Callback;
import com.travel.liuyun.bean.DialogButtonItem;
import com.travel.liuyun.bean.Result;
import com.travel.liuyun.bean.UserInfo;
import com.travel.liuyun.network.NetAccess;
import com.travel.liuyun.parse.CircleTransform;
import com.travel.liuyun.utils.DialogUtil;
import com.travel.liuyun.utils.ImageUtil;
import com.travel.liuyun.utils.InternetUtil;
import com.travel.liuyun.view.Tip;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by liuguizhou on 2016/5/1.
 */
public class PersonalFragment extends BaseFragment {
    private RelativeLayout background;
    private View rootView;
    private ImageView avatar;
    private String filePath;
    private ChooseType chooseType;

    private enum ChooseType {
        AVATAR, BACKGROUND
    }

    public static PersonalFragment getInstance() {
        PersonalFragment fragment = new PersonalFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        background = (RelativeLayout) view.findViewById(R.id.backimage);
        avatar = (ImageView) view.findViewById(R.id.touxiang);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.personal_fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initData();
        initListener();

    }

    private void initData() {
        avatar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                chooseType = ChooseType.AVATAR;
                selectImage("请选择图片更改您的头像");
            }
        });
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseType = ChooseType.BACKGROUND;
                selectImage("请选择图片更改您的头像背景");
            }
        });
    }

    private void initListener() {

    }

    protected final void selectImage(String title) {
        DialogUtil.dialogMoreButtons(getActivity(), title, new ArrayList<DialogButtonItem>() {
                    {
                        add(new DialogButtonItem(R.string.take_photo, R.drawable.bg_white_fillet_5,
                                R.color.yj_gray5));
                        add(new DialogButtonItem(R.string.take_album, R.drawable.bg_white_fillet_5,
                                R.color.yj_gray5));
                        add(new DialogButtonItem(R.string.cancel, R.drawable.bg_white_fillet_5,
                                R.color.yj_gray5));
                    }
                }, new Callback<DialogButtonItem>() {
                    @Override
                    public void call(DialogButtonItem... values) {

                        final DialogButtonItem value = values[0];
                        switch (value.getRid()) {
                            case R.string.take_photo:
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                filePath = Environment.getExternalStorageDirectory() + "/"
                                        + System.currentTimeMillis() + ".png";
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(filePath)));
                                startActivityForResult(intent, Constants.PHOTOHRAPH);
                                break;
                            case R.string.take_album:
                                intent = new Intent(Intent.ACTION_PICK, null);
                                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                        Constants.IMAGE_UNSPECIFIED);
                                startActivityForResult(intent, Constants.PHOTOZOOM);
                                break;
                            case R.string.cancel:
                                break;
                        }
                    }
                }
        );
    }

    private void loadUserInfo(String backImage,String avatarImage) {
        Glide.with(getActivity()).load(backImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(new SimpleTarget<GlideDrawable>() {
            @SuppressLint("NewApi")
            @Override
            public void onResourceReady(GlideDrawable glideDrawable,
                                        GlideAnimation<? super GlideDrawable> glideAnimation) {
                background.setBackground(glideDrawable);
            }
        });
        Glide.with(getActivity()).load(avatarImage)
                .transform(new CircleTransform(getActivity()))
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(avatar);
    }
    public void startPhotoZoom(Activity activity, Uri uri, int width, int height) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", width / 100);
        intent.putExtra("aspectY", height / 100);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, Constants.PHOTORESOULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Constants.NONE)
            return;
        // 拍照
        if (requestCode == Constants.PHOTOHRAPH) {
            // 将保存在本地的图片取出并缩小后显示在界面上
            // Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            readyToUpdate(filePath);
        }
        // 读取相册缩放图片
        if (requestCode == Constants.PHOTOZOOM) {
            // 照片的原始资源地址
            Uri originalUri = data.getData();
            readyToUpdate(ImageUtil.getRealPathFromURI(getActivity(), originalUri));
        }

        // 处理结果
        if (requestCode == Constants.PHOTORESOULT) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                // ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // photo.compress(Bitmap.CompressFormat.PNG, 80, stream);// (0 -
                // 100)压缩文件
                // avatar.setImageBitmap(photo);
                try {
                    final String avatarPath = getActivity().getFilesDir().getAbsolutePath() + "/"
                            + System.currentTimeMillis() + ".png";
                    final File avatar = new File(avatarPath);
                    if (!avatar.exists())
                        avatar.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(avatar);
                    photo.compress(Bitmap.CompressFormat.PNG, 80, fOut);// (0 -
                    // 100)压缩文件
                    fOut.flush();
                    fOut.close();
                    uploadAvatarOrBackgroundImage(avatarPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void uploadAvatarOrBackgroundImage(final String imagePath) {
        InternetUtil.startMyTask(new AsyncTask<Object, Object, Result<UserInfo>>() {
            @Override
            protected void onPreExecute() {
                Tip.showLoading(getActivity(), "正在上传...");
            }

            @Override
            protected Result<UserInfo> doInBackground(Object... objects) {
                try {
                    switch (chooseType) {
                        case AVATAR:
                            return NetAccess.uploadUserAvatarOrBackground("HeadImg", imagePath,
                                    "6488");
                        case BACKGROUND:
                            return NetAccess.uploadUserAvatarOrBackground("Pictures", imagePath,
                                    "6488");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Result.error();
            }

            @Override
            protected void onPostExecute(Result<UserInfo> result) {
                Tip.hideLoading();
                if (result.isSuccess()) {
                    final UserInfo userInfo = result.getData();
                    loadUserInfo(userInfo.getPictures(),userInfo.getHeadImg());
                } else {
                    Tip.showTip(getActivity(), "图片上传失败，请稍候再试！");
                }
            }
        });
    }

    private void readyToUpdate(String filePath) {
        switch (chooseType) {
            case AVATAR:
                startPhotoZoom(getActivity(), Uri.parse("file:///" + filePath), 300, 300);
                break;
            case BACKGROUND:
                startPhotoZoom(getActivity(), Uri.parse("file:///" + filePath), 400, 300);
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
