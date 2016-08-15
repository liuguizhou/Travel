package com.travel.liuyun.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.travel.liuyun.R;
import com.travel.liuyun.bean.ItemBean;
import com.travel.liuyun.loading.RecyclerLoadMoreAdapater;
import com.travel.liuyun.view.SlideRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Young Pioneers on 16/6/30.
 */
public class SlideAdapter extends RecyclerLoadMoreAdapater {

    public static final int NORMAL = 1000;
    public static final int SLIDE = 2000;
    private int mState = NORMAL;
    //    private List<T> mItemBeans = new ArrayList<T>();
    private List<SlideViewHolder> mSlideViewHolders = new ArrayList<>();

    public void openItemAnimation() {
        mState = SLIDE;
        for (SlideViewHolder holder : mSlideViewHolders) {
            holder.openItemAnimation();
        }
    }

    public void closeItemAnimation() {
        mState = NORMAL;
        for (SlideViewHolder holder : mSlideViewHolders) {
            holder.closeItemAnimation();
        }
    }


    /*  public void setItemBeans(List<ItemBean> beans) {
        mItemBeans = beans;
        notifyDataSetChanged();
    }*/

   /* @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SlideViewHolder slideViewHolder = new SlideViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
        mSlideViewHolders.add(slideViewHolder);
        return slideViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SlideViewHolder) holder).bind(mItemBeans.get(position));
    }*/

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderSuper(ViewGroup viewGroup, int viewType) {
        SlideViewHolder slideViewHolder = new SlideViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false));
        mSlideViewHolders.add(slideViewHolder);
        return slideViewHolder;
    }

    @Override
    public int getItemViewTypeSuper(int position) {
        return super.getItemViewTypeSuper(position);
    }

    @Override
    public void onBindViewHolderSuper(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolderSuper(holder, position);
        SlideViewHolder slideViewHolder = (SlideViewHolder) holder;
        ItemBean mBean = (ItemBean) mDataList.get(slideViewHolder.getLayoutPosition());
        slideViewHolder.bind(mBean);
        slideViewHolder.title.setText(mBean.getTitle());
    }

    @Override
    public int getItemCount() {
//        return mDataList == null ? 0 : mDataList.size();
        return super.getItemCount();
    }

    private class SlideViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private SlideRelativeLayout mSlideRelativeLayout;
        private CheckBox mCheckBox;
        private ItemBean mItemBean;
        private TextView title;

        public SlideViewHolder(View itemView) {
            super(itemView);
            mSlideRelativeLayout = (SlideRelativeLayout) itemView.findViewById(R.id.item_root);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.item_checkbox);
            title = (TextView) itemView.findViewById(R.id.item_title_tv);
            itemView.setOnClickListener(this);
        }

        public void bind(ItemBean itemBean) {
            mItemBean = itemBean;
            mCheckBox.setChecked(itemBean.isChecked());
            switch (mState) {
                case NORMAL:
                    mSlideRelativeLayout.close();
                    break;

                case SLIDE:
                    mSlideRelativeLayout.open();
                    break;
            }
        }

        public void openItemAnimation() {
            mSlideRelativeLayout.openAnimation();
        }

        public void closeItemAnimation() {
            mSlideRelativeLayout.closeAnimation();
        }

        public void setCheckBox() {
            mCheckBox.setChecked(!mCheckBox.isChecked());
            mItemBean.setChecked(mCheckBox.isChecked());
        }

        @Override
        public void onClick(View v) {
            setCheckBox();
        }
    }
}
