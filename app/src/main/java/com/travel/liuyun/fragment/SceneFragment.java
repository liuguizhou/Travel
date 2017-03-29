package com.travel.liuyun.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.travel.liuyun.Constants;
import com.travel.liuyun.R;
import com.travel.liuyun.activity.GreenDaoActivity;
import com.travel.liuyun.callback.OnItemClickListener;
import com.travel.liuyun.okhttp.LoadCallBack;
import com.travel.liuyun.okhttp.OkHttpManager;
import com.travel.liuyun.okhttp.download.DownloadUtil;
import com.travel.liuyun.okhttp.download.OnDownloadListener;
import com.travel.liuyun.utils.AppInfo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liuguizhou on 2016/5/1.
 */
public class SceneFragment extends BaseFragment implements View.OnClickListener {
    public static String DEVICEID;
    public static String TOKEN;
    @BindView(R.id.create_dialog)
    Button dialog;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.greendao)
    Button greendao;
    @BindView(R.id.commit)
    Button commit;
    @BindView(R.id.downloadfile)
    Button download;
    public static SceneFragment sceneFragment;

    // 外存sdcard存放路径
    private static final String FILE_PATH = "storage/emulated/0/";//Environment.getExternalStorageDirectory() +"/" + "AutoUpdate" +"/";

    public SceneFragment() {
        // Required empty public constructor sometimes.
    }

    public static SceneFragment newInstance() {
        sceneFragment = new SceneFragment();
        return sceneFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sceneFragment = this;

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        Log.e("lgz", "initView");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.scene_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initData();
        Log.e("lgz", "onViewCreated");
    }

    private int generateRandom() {
        return new Random(100000000).nextInt();
    }

    private void initData() {
        download.setOnClickListener(this);
        commit.setOnClickListener(this);
        dialog.setOnClickListener(this);
        greendao.setOnClickListener(this);

        HashMap params = new HashMap<String, String>();
        params.put("deviceUUID", DEVICEID);
        params.put("IMEI", DEVICEID);
        params.put("clientOS", "Android");
        params.put("clientOSVersion", AppInfo.getDeviceVersion());
        params.put("clientResolution", AppInfo.getScreenSize());
        HashMap a = new HashMap<String, String>();
        a.put("string", generateRandom());
        a.put("token", TOKEN);
        a.put("deviceUUID", DEVICEID);
//        a.put("clientOS", "Android");
//        a.put("clientOSVersion", AppInfo.getDeviceVersion());
//        a.put("clientVersion", AppInfo.getAppVersionName());
//        'b':{'userId':'100401201609021237044627442435699555',
//                'objectNo':'100201201609051722312991751659096335',
//                'objectType':5,'userType':1},'c':'swtest'}
        HashMap pri = new HashMap<String, String>();
        pri.put("userId", "100401201609021237044627442435699555");
        pri.put("objectNo", "100201201609051722312991751659096335");
        pri.put("objectType", "5");
        pri.put("userType", "1");


//        TBaseApi.postJsonRequest("createsession/1.0", null, params, a, new TBaseCallback<Student>() {
//
//            @Override
//            public void onResponse(TOkHttpResponse<Student> response, int id) {
//
//            }
//        });


        OkHttpUtils
                .post()
                .url("http://115.29.206.244:9080/xwy_traveltools/common/bizCreateQRcode/1.0")
                .addParams("data", initParam(pri, a))
                .build()
                .execute(new MyStringCallback());

//        OkHttpUtils
//                .post()
//                .url("http://115.29.206.244:9080/xwy_traveltools/user/createsession/1.0")
//                .addParams("data",initParam(params,a))
//                .build()
//                .execute(new MyStringCallback());

        Map<String, String> loginParams = new HashMap<String, String>();
        loginParams.put("platform", "android");
        loginParams.put("version", "1.0");
        loginParams.put("key", "123456");
        loginParams.put("Mobile", "15256298062");//phoneNumber:15256298062
        loginParams.put("PassWord", "123456");//password:123456

//        OkHttpUtils
//                .post()
//                .url(Constants.LOGIN_URL).params(loginParams)
////                .addParams("platform","android")
////                .addParams("version","1.0")
////                .addParams("key","123456")
////                .addParams("Mobile", "15256298062")
////                .addParams("PassWord", "123456")
//                .build()
//                .execute(new MyStringCallback());
    }

    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {

        }

        @Override
        public void onAfter(int id) {

        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            Log.e("lgz", "onError：" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e("lgz", "onResponse：complete");

            switch (id) {
                case 100:
                    Toast.makeText(getActivity(), "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(getActivity(), "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    private String initParam(Map<String, String> pri_param, Map<String, String> pub_param) {

        Map<String, Object> args = new HashMap<>();
        args.put("a", pub_param);
        args.put("b", pri_param);
        args.put("c", new JsonObject());
        String json = new Gson().toJson(args);
        return json;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit:
                /***************************妙游C端 登录接口*************************/
                if (!TextUtils.isEmpty(username.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())) {
                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("platform", "android");params.put("version", "1.0");params.put("key", "123456");//请求头 公共参数
                    params.put("Mobile", username.getText().toString());//phoneNumber:15256298062
                    params.put("PassWord", password.getText().toString());//password:123456

                    OkHttpManager.getInstance().postRequest(Constants.LOGIN_URL, new LoadCallBack<String>(getActivity()) {
                                @Override
                                protected void onSuccess(Call call, Response response, String s) {
                                    Log.e("lgz", "onSuccess = " + s);
                                    Toast.makeText(getActivity(), "登录成功！", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                protected void onEror(Call call, int statusCode, Exception e) {
                                    Log.e("lgz", "Exception = " + e.toString());
                                }
                            }
                            , params);
                }
                break;
            case R.id.downloadfile:
//                OkHttpManager.getInstance().asynDownloadFile("http://img06.tooopen.com/images/20160919/tooopen_sy_179298217832.jpg", "lgz", new FileCallBack<String>(getActivity()) {
//                    @Override
//                    protected void onResponse(Response response) {}
//                    @Override
//                    protected void onSuccess(Call call, Response response, String s) {
//                        super.onSuccess(call, response, s);
//                        Log.e("lgz", "status = : " + s);
//                        Toast.makeText(getActivity(), "下载成功", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//                        Uri uri = Uri.fromFile(new File(s));
//                        intent.setData(uri);
//                        getActivity().sendBroadcast(intent);// 下载完成后，发送广播更新相册...
//                    }
//                });
                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("下载中...");
                progressDialog.setMax(100);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setCanceledOnTouchOutside(true);
                progressDialog.show();
                DownloadUtil.get().download("http://img04.tooopen.com/images/20121019/tooopen_201210192258098098.jpg", "liuguizhou", new OnDownloadListener() {
                    @Override
                    public void onDownloadSuccess(final String s) {
                        Looper.prepare();
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "下载成功", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        Uri uri = Uri.fromFile(new File(s));
                        intent.setData(uri);
                        getActivity().sendBroadcast(intent);// 下载完成后，发送广播更新相册...
                        Looper.loop();
                    }

                    @Override
                    public void onDownloading(int progress) {
                        progressDialog.setProgress(progress);
                    }

                    @Override
                    public void onDownloadFailed() {
                        Looper.prepare();
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "下载失败", Toast.LENGTH_LONG).show();
                        Looper.loop();
                    }
                });
                break;
            case R.id.create_dialog:
                RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(getActivity())
                        .inflate(R.layout.only_recycler, null);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                Adapter adapter = new Adapter();
                recyclerView.setAdapter(adapter);

                final BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                dialog.setContentView(recyclerView);
                dialog.show();
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, String text) {
                        Toast.makeText(getHoldingActivity(), text, Toast.LENGTH_SHORT).show();
                        Log.e("lgz", "onItemClick: ");
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.greendao:
                startActivity(new Intent(getActivity(), GreenDaoActivity.class));
                break;
            default:
                break;
        }

    }


    static class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        private OnItemClickListener mItemClickListener;

        public void setOnItemClickListener(OnItemClickListener li) {
            mItemClickListener = li;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
            return new Holder(item);
        }

        @Override
        public void onBindViewHolder(final Holder holder, int position) {
            holder.tv.setText("item " + position);
            if (mItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mItemClickListener.onItemClick(holder.getLayoutPosition(),
                                holder.tv.getText().toString());
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return 30;
        }

        class Holder extends RecyclerView.ViewHolder {
            TextView tv;

            public Holder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.text);
            }
        }

    }


    @Override
    public void onPause() {
        super.onPause();
        Log.e("lgz", "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("lgz", "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("lgz", "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("lgz", "onDestroy: ");
    }
}
