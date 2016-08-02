package com.travel.liuyun.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.travel.liuyun.R;
import com.travel.liuyun.activity.ImpressionActivity;
import com.travel.liuyun.activity.ToEntertainActivity;
import com.travel.liuyun.activity.ToLiveActivity;
import com.travel.liuyun.activity.ToPlayActivity;
import com.travel.liuyun.activity.TripNewsActivity;
import com.travel.liuyun.adapter.GridAdapter;
import com.travel.liuyun.adapter.SceneGridAdapter;
import com.travel.liuyun.bean.Result;
import com.travel.liuyun.retrofit.LoginService;
import com.travel.liuyun.retrofit.PhoneApi;
import com.travel.liuyun.utils.DataProvider;
import com.travel.liuyun.utils.ViewFindUtils;
import com.travel.liuyun.widget.CustomGridView;
import com.travel.liuyun.widget.SimpleImageBanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liuguizhou on 2016/5/1.
 */
public class HomeFragment extends BaseFragment {

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
                        try {
                            uploadFile("/mnt/sdcard/MCM/1.JPG");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        Snackbar.make(view, "要删除数据吗？", Snackbar.LENGTH_LONG).setAction("Undo?", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        }).show();
                        break;
                    case 5:
                       /* LoginApi.FamousService service = BaseApi.getRetrofit().create(LoginApi.FamousService.class);
                        Map<String, String> options = new HashMap<String, String>();
                        options.put("platform", "android");
                        options.put("version", "1.0");
                        options.put("key", "123456");
                        options.put("Mobile", "15256298062");
                        options.put("PassWord", "123456");

                        Call<Result> call = service.getFamousList(options);
                        call.enqueue(new Callback<Result>() {
                            @Override
                            public void onResponse(Call<Result> call, Response<Result> response) {
                                if (response.isSuccessful()){
                                   Result result = response.body();
                                    Toast.makeText(getActivity(), "请求成功,status = " +result.getStatus()+"message = "+result.getMessage() , Toast.LENGTH_LONG).show();
                                    Log.e("lgz", "getStatus = : "+ result.getStatus()+"data = "+result.getData().toString());
                                }
                            }

                            @Override
                            public void onFailure(Call<Result> call, Throwable t) {
                                Toast.makeText(getActivity(), "请求失败" +t.toString() , Toast.LENGTH_LONG).show();
                                Log.e("lgz", "Throwable = : "+t.toString() );
                            }

                        });*/
                        PhoneApi phoneApi = PhoneApi.getApi();
                        LoginService loginService = phoneApi.getService();
                        Map<String, String> options = new HashMap<String, String>();
                        options.put("platform", "android");
                        options.put("version", "1.0");
                        options.put("key", "123456");
                        options.put("Mobile", "15256298062");
                        options.put("PassWord", "123456");
                        loginService.getFamousList("android","123456","1.0","15256298062","123456")
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
                                        Log.e("lgz", "result = :"+result.getStatus());
                                        if (result != null && result.getStatus() == 1) {
//                                            PhoneResult.RetDataEntity entity = result.getRetData();
//                                            resultView.append("地址：" + entity.getCity());
                                            Toast.makeText(getActivity(), "请求成功" + result.getStatus() + "message = " + result.getMessage(), Toast.LENGTH_LONG).show();
                                            Log.e("lgz", "getStatus = : " + result.getStatus() + "data = " + result.getData().toString());
                                        }
                                    }
                                });

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
            params.put("ID", "6488");
            params.put("version", "1.0");
            params.put("key", "123456");
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
}
