package com.travel.liuyun.activity.scene;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.travel.liuyun.R;
import com.travel.liuyun.activity.BaseActivity;
import com.travel.liuyun.adapter.WelfareAdapter;
import com.travel.liuyun.bean.GankRootBean;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by liuguizhou on 2017/4/19.
 */

public class ImageActivity extends BaseActivity {
    private static final String TAG = ImageActivity.class.getSimpleName();
    @BindView(R.id.image_list)
    RecyclerView imageList;
    private RequestQueue requestQueue;
    private WelfareAdapter welfareAdapter;
    private String URL = "http://gank.io/api/data/Android/10/1";
    private String base_url = "http://gank.io/api/data/";

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        try {
            String str  = URLEncoder.encode("福利","UTF-8");
            doRequest(base_url+str+"/20/1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initView() {
        requestQueue = Volley.newRequestQueue(this);
        welfareAdapter = new WelfareAdapter(this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        imageList.setLayoutManager(layoutManager);//
        imageList.setHasFixedSize(true);//
        imageList.setAdapter(welfareAdapter);
    }

    private void doRequest(String url) {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "result  = " + response);
                GankRootBean bean = new Gson().fromJson(response, GankRootBean.class);
                welfareAdapter.setBeanList(bean.getResults());
                welfareAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Charset", "UTF-8");
                return headers;
            }

        };
        requestQueue.add(request);
    }


}
