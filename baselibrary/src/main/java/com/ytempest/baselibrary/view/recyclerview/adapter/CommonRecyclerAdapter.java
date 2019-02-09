package com.ytempest.baselibrary.view.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author ytempest
 *         Description：通用RecyclerView适配器，对RecyclerView的Adapter进行了封装
 */
public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<CommonViewHolder>
        implements View.OnClickListener, View.OnLongClickListener {
    /**
     * 列表数据
     */
    private List<T> mDataList;
    private LayoutInflater mInflater;
    /**
     * RecyclerView子View的布局id
     */
    private int mLayoutId;
    /**
     * 多布局支持类
     */
    private MultiTypeSupport<T> mMultiTypeSupport;


    public CommonRecyclerAdapter(Context context, List<T> dataList, int layoutId) {
        this.mDataList = dataList;
        this.mLayoutId = layoutId;
        this.mInflater = LayoutInflater.from(context);
    }

    public CommonRecyclerAdapter(Context context, List<T> dataList, MultiTypeSupport<T> multiTypeSupport) {
        this(context, dataList, -1);
        this.mMultiTypeSupport = multiTypeSupport;
    }

    /**
     * 根据子View的位置返回相应的子View的布局id
     *
     * @param position 子View的位置
     * @return 返回一个布局id，这个id会传到 onCreateViewHolder方法的第二个参数 viewType
     */
    @Override
    public int getItemViewType(int position) {
        if (mMultiTypeSupport != null) {
            return mMultiTypeSupport.getLayoutId(mDataList.get(position), position);
        }
        return super.getItemViewType(position);
    }

    /**
     * 根据viewType的种类创建ViewHolder
     *
     * @param viewType 子View的布局id
     * @return
     */
    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mMultiTypeSupport != null) {
            // 指定当前子View的布局id
            mLayoutId = viewType;
        }
        View view = mInflater.inflate(mLayoutId, parent, false);
        CommonViewHolder holder = new CommonViewHolder(view);
        holder.itemView.setTag(holder);
        if (holder.isNeedClick()) {
            holder.itemView.setOnClickListener(this);
        }
        if (holder.isNeedLongClick()) {
            holder.itemView.setOnLongClickListener(this);
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {
        // 绑定数据
        bindViewData(holder, mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    /**
     * 绑定数据到ViewHolder
     *
     * @param holder 当前位置的ViewHolder
     * @param item   当前位置的数据
     */
    protected abstract void bindViewData(CommonViewHolder holder, T item);

     /* Click */

    private OnItemClickListener itemClickListener;
    private OnLongClickListener itemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnLongClickListener listener) {
        itemLongClickListener = listener;
    }

    @Override
    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof RecyclerView.ViewHolder) {
            RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) tag;
            int position = holder.getAdapterPosition();
            if (position >= 0) {
                onItemClick(view, position);
            }
        }
    }

    protected void onItemClick(View view, int position) {
        if (itemClickListener != null) {
            itemClickListener.onItemClick(view, position);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof RecyclerView.ViewHolder) {
            RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) tag;
            int position = holder.getAdapterPosition();
            if (position >= 0) {
                return onLongItemClick(view, position);
            }
        }
        return false;
    }

    private boolean onLongItemClick(View view, int position) {
        return itemLongClickListener != null && itemLongClickListener.onItemLongClick(view, position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnLongClickListener {
        boolean onItemLongClick(View view, int position);
    }
}
