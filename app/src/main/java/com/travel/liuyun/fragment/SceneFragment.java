package com.travel.liuyun.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.liuyun.R;
import com.travel.liuyun.adapter.ImageAdapter;
import com.travel.liuyun.callback.OnActivityResultPickListener;

import java.util.ArrayList;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;

/**
 * Created by liuguizhou on 2016/5/1.
 */
public class SceneFragment extends BaseFragment implements OnActivityResultPickListener {
    private View rootView;
    private ArrayList<String> filePaths;
    private RecyclerView recyclerView;
    private Button button_photo;
    private Button button_doc;
    private Button dialog;
    public static SceneFragment sceneFragment;

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

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Log.e("lgz", "initView");
        button_doc = (Button) view.findViewById(R.id.pick_doc);
        button_photo = (Button) view.findViewById(R.id.pick_photo);
        dialog = (Button) view.findViewById(R.id.create_dialog);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
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
        button_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilePickerBuilder.getInstance().setMaxCount(6)
                        .setSelectedFiles(filePaths)
                        .setActivityTheme(R.style.AppTheme)
                        .pickPhoto(getActivity());
            }
        });
        button_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilePickerBuilder.getInstance().setMaxCount(12)
                        .setSelectedFiles(filePaths)
                        .setActivityTheme(R.style.AppTheme)
                        .pickDocument(getActivity());
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
                        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void addThemToView(ArrayList<String> filePaths) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ImageAdapter(getActivity(), filePaths));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityResultPicker(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FilePickerConst.REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    filePaths = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_PHOTOS);
                    Log.e("lgz", "filePaths = " + filePaths.size());
                    addThemToView(filePaths);
                }
        }
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
