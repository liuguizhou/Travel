package com.travel.liuyun.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.liuyun.Constants;
import com.travel.liuyun.R;
import com.travel.liuyun.okhttp.FileCallBack;
import com.travel.liuyun.okhttp.LoadCallBack;
import com.travel.liuyun.okhttp.OkHttpManager;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by liuguizhou on 2016/5/1.
 */
public class SceneFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private Button dialog;
    private EditText username;
    private EditText password;
    private Button commit;
    private Button download;
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
        Log.e("lgz", "initView");
        dialog = (Button) view.findViewById(R.id.create_dialog);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        username = (EditText) view.findViewById(R.id.username);
        password = (EditText) view.findViewById(R.id.password);
        commit = (Button) view.findViewById(R.id.commit);
        download = (Button) view.findViewById(R.id.downloadfile);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.scene_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initData();
        initListener();
        Log.e("lgz", "onViewCreated");
    }

    private void initData() {

    }

    private void initListener() {

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpManager.getInstance().asynDownloadFile("http://gdown.baidu.com/data/wisegame/df65a597122796a4/weixin_821.apk", FILE_PATH, new FileCallBack<String>(getActivity()) {
                    @Override
                    protected void onResponse(Response response) {

                    }

                    @Override
                    protected void onSuccess(Call call, Response response, String s) {
                        super.onSuccess(call, response, s);
                        Log.e("lgz", "status = : " + s);
                        Toast.makeText(getActivity(), "下载成功", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//                        Uri uri = Uri.fromFile(new File(s));
//                        intent.setData(uri);
//                        getActivity().sendBroadcast(intent);// 下载完成后，发送广播更新相册...
                    }
                });
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(username.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())) {
                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("platform", "android");
//                    params.put("version", "1.0");
//                    params.put("key", "123456");
                    params.put("Mobile", username.getText().toString());
                    params.put("PassWord", password.getText().toString());

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
            }
        });
        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(getActivity())
                        .inflate(R.layout.only_recycler, null);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                Adapter adapter = new Adapter();
                recyclerView.setAdapter(adapter);

                final BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                dialog.setContentView(recyclerView);
                dialog.show();
                adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, String text) {
                        Toast.makeText(getHoldingActivity(), text, Toast.LENGTH_SHORT).show();
                        Log.e("lgz", "onItemClick: ");
                        dialog.dismiss();
                    }
                });
            }
        });
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

    static class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        private OnItemClickListener mItemClickListener;

        public void setOnItemClickListener(OnItemClickListener li) {
            mItemClickListener = li;
        }

        @Override
        public Adapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
            return new Holder(item);
        }

        @Override
        public void onBindViewHolder(final Adapter.Holder holder, int position) {
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

        interface OnItemClickListener {
            void onItemClick(int position, String text);
        }
    }
}
