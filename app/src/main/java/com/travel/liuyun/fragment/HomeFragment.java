package com.travel.liuyun.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.travel.liuyun.R;
import com.travel.liuyun.activity.DialogActivity;
import com.travel.liuyun.activity.home.ImpressionActivity;
import com.travel.liuyun.activity.home.ToEntertainActivity;
import com.travel.liuyun.activity.home.ToLiveActivity;
import com.travel.liuyun.activity.home.ToPlayActivity;
import com.travel.liuyun.activity.home.TripNewsActivity;
import com.travel.liuyun.adapter.GridAdapter;
import com.travel.liuyun.adapter.SceneGridAdapter;
import com.travel.liuyun.bean.Result;
import com.travel.liuyun.okhttp.LoadCallBack;
import com.travel.liuyun.okhttp.OkHttpManager;
import com.travel.liuyun.retrofit.LoginApi;
import com.travel.liuyun.retrofit.TestApi;
import com.travel.liuyun.utils.CommonUtils;
import com.travel.liuyun.utils.DataProvider;
import com.travel.liuyun.utils.ViewFindUtils;
import com.travel.liuyun.widget.CustomGridView;
import com.travel.liuyun.widget.SimpleImageBanner;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by liuguizhou on 2016/5/1.
 */
public class HomeFragment extends BaseFragment {

    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;

    private SimpleImageBanner sib;

    private LinearLayout trip_layout;

    private CustomGridView menuList;

    private CustomGridView sceneList;

    private GridAdapter mAdapter;

    private SceneGridAdapter gridAdapter;

    private LinearLayout impression_layout;

    private int images[] = {R.mipmap.home_icon_play, R.mipmap.home_icon_live,
            R.mipmap.home_icon_entertainment, R.mipmap.home_icon_eat, R.mipmap.home_icon_traffic,
            R.mipmap.home_icon_shopping, R.mipmap.home_icon_periphery, R.mipmap.home_icon_wechat,};
    private String str[] = {"去哪玩", "住哪儿", "娱乐", "美食", "交通", "购物", "周边", "微信微博"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        sib = ViewFindUtils.find(view, R.id.simple_banner);
        menuList = ViewFindUtils.find(view, R.id.menu_grid);
        impression_layout = ViewFindUtils.find(view, R.id.impression_layout);
        trip_layout = ViewFindUtils.find(view, R.id.trip_news_layout);
        sceneList = ViewFindUtils.find(view, R.id.scene_gridview);
        sib.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_page_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initData();
        initListener();

    }

    private void initData() {
        sib.setSource(DataProvider.getList()).startScroll();
        mAdapter = new GridAdapter(getActivity(), str, images);
        gridAdapter = new SceneGridAdapter(getActivity());
        sceneList.setAdapter(gridAdapter);
        menuList.setAdapter(mAdapter);

    }

    private void initListener() {

        sib.setOnItemClickL(new SimpleImageBanner.OnItemClickL() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), "position>>" + position, Toast.LENGTH_SHORT).show();
            }
        });
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(getActivity(), ToPlayActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(getActivity(), ToLiveActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(getActivity(), ToEntertainActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                       /* try {
//                            uploadFile("storage/sdcard0/MGJ-IM/images/ff.jpg");
//                            upLoadHeadImage("storage/sdcard0/yymobile/image/hh.jpg");


                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }*/
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("platform", "android");
                        params.put("version", "1.0");
                        params.put("key", "123456");
                        params.put("ID", "6488");
                        OkHttpManager.getInstance().postUploadSingleImage("http://api.7mlzg.com/Business/ModifyGuestInfo", new LoadCallBack<String>(getActivity()) {
                            @Override
                            protected void onSuccess(Call call, Response response, String s) {
                                Log.e("lgz", "onSuccess =  " + s);
                                Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            protected void onEror(Call call, int statusCode, Exception e) {

                            }
                        }, new File("storage/emulated/0/LGImgCompressor/Images/Hhh.jpg"), "HeadImg", params);
                        break;
                    case 4:
                       /* Snackbar.make(view, "要删除数据吗？", Snackbar.LENGTH_LONG).setAction("Undo?", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        }).show();*/
//                        Intent intent22 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        startActivityForResult(intent22, 0011);
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
                        startActivity(new Intent(getActivity(), ImpressionActivity.class));
                        startActivity(new Intent(getActivity(), DialogActivity.class));
//                            }
//                        }, 50);


                        break;
                    case 5:

                       /* FamousService service = BaseApi.getRetrofit().create(FamousService.class);
                        PhoneApi phoneApi = PhoneApi.getApi();
                        LoginService loginService = phoneApi.getService();
                        Map<String, String> options = new HashMap<String, String>();
                        options.put("platform", "android");
                        options.put("version", "1.0");
                        options.put("key", "123456");
                        options.put("Mobile", "15256298062");
                        options.put("PassWord", "123456");
                        loginService.getFamousList("android", "123456", "1.0", "15256298062", "123456")
                                .subscribeOn(Schedulers.newThread())    //子线程访问网络
                                .observeOn(AndroidSchedulers.mainThread())  //回调到主线程
                                .subscribe(new Observer<Result>() {
                                    @Override
                                    public void onCompleted() {
                                        Log.e("lgz", "1111111111111111 ");
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Toast.makeText(getActivity(), "请求失败" + e.toString(), Toast.LENGTH_LONG).show();
                                        Log.e("lgz", "Throwable = : " + e.toString());
                                    }

                                    @Override
                                    public void onNext(Result result) {
                                        Log.e("lgz", "result = :" + result.getStatus());
                                        if (result != null && result.getStatus() == 1) {
//                                            PhoneResult.RetDataEntity entity = result.getRetData();
//                                            resultView.append("地址：" + entity.getCity());
                                            Toast.makeText(getActivity(), "请求成功" + result.getStatus() + "message = " + result.getMessage(), Toast.LENGTH_LONG).show();
                                            Log.e("lgz", "getStatus = : " + result.getStatus() + "data = " + result.getData().toString());
                                        }
                                    }
                                });
*/
                        new LoginApi().getUser("15256298062", "123456").subscribe(new Observer<Result>() {
                            @Override
                            public void onCompleted() {
                                Log.e("lgz", "onCompleted:");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("lgz", "e: " + e.toString());
                            }

                            @Override
                            public void onNext(Result result) {
                                Log.e("lgz", "status = : " + result.getStatus() + " message = " + result.getMessage());
                            }
                        });

                        break;
                    case 6:
                        Intent intentScanning = new Intent(getActivity(), CaptureActivity.class);
                        startActivityForResult(intentScanning, REQUEST_CODE);
                        break;
                    case 7:
                        LoadCallBack callBack = new LoadCallBack<String>(getActivity()) {

                            @Override
                            protected void onSuccess(Call call, Response response, String s) {
                                super.onSuccess(call,response,s);
                                Log.e("lgz", "status = : " + s);
                                Toast.makeText(getActivity(), "下载成功", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                                Uri uri = Uri.fromFile(new File(s));
                                intent.setData(uri);
                                getActivity().sendBroadcast(intent);
                            }

                            @Override
                            protected void onEror(Call call, int statusCode, Exception e) {
                                Log.e("lgz", "Exception = : " + e);
                            }
                        };
                        OkHttpManager.getInstance().asynDownloadFile("http://img06.tooopen.com/images/20160919/tooopen_sy_179298217832.jpg", "storage/emulated/0", callBack);
                        callBack.setMsg("正在下载...");
                        break;
                    default:
                        Toast.makeText(getActivity(), "position>>" + position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        impression_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImpressionActivity.class);
                startActivity(intent);
            }
        });
        trip_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TripNewsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
//        else if ()

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void uploadFile(String path) throws FileNotFoundException {
        File file = new File(path);
        if (file.exists() && file.length() > 0) {
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put("platform", "android");
            params.put("version", "1.0");
            params.put("key", "123456");
            params.put("ID", "6488");
            params.put("HeadImg", file);
            // 上传文件
            client.post("http://api.7mlzg.com/Business/ModifyGuestInfo", params, new AsyncHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers,
                                      byte[] responseBody) {
                    // 上传成功后要做的工作
                    Toast.makeText(getActivity(), "上传成功" + statusCode, Toast.LENGTH_LONG).show();
//                    progress.setProgress(0);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers,
                                      byte[] responseBody, Throwable error) {
                    // 上传失败后要做到工作
                    Toast.makeText(getActivity(), "上传失败" + statusCode, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onProgress(long bytesWritten, long totalSize) {

                    super.onProgress(bytesWritten, totalSize);
                    int count = (int) ((bytesWritten * 1.0 / totalSize) * 100);
                    // 上传进度显示
//                    progress.setProgress(count);
                    Log.e("上传 Progress>>>>>", bytesWritten + " / " + totalSize);

                }

                @Override
                public void onRetry(int retryNo) {
                    // TODO Auto-generated method stub
                    super.onRetry(retryNo);
                    // 返回重试次数
                }

            });
        } else {
            Toast.makeText(getActivity(), "文件不存在", Toast.LENGTH_LONG).show();
        }
    }

    public void upLoadHeadImage(String path) throws FileNotFoundException {
        File file = new File(path);
        if (file.exists() && file.length() > 0) {
            Map<String, RequestBody> params = new HashMap<>();
            params.put("platform", CommonUtils.toRequestBody("android"));
            params.put("version", CommonUtils.toRequestBody("1.0"));
            params.put("key", CommonUtils.toRequestBody("123456"));
            params.put("ID", CommonUtils.toRequestBody("6488"));
            params.put("HeadImg\"; filename=\"" + file.getName(), RequestBody.create(MediaType.parse("image/png"), file));

            TestApi.getApi().getService().upLoadPicture(params).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Result>() {
                @Override
                public void onCompleted() {
                    Log.e("lgz", "onCompleted");
                }

                @Override
                public void onError(Throwable e) {
                    Log.e("lgz", "e = " + e.toString());
                }

                @Override
                public void onNext(Result result) {
                    Log.e("lgz", "result = " + result.getStatus() + "data = " + result.getData());
                    Toast.makeText(getActivity(), result.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getActivity(), "文件不存在", Toast.LENGTH_LONG).show();
        }
    }
}
