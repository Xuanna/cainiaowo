package ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import utils.OnItemClickListener;

/**
 * Created by xuchichi on 2017/11/7.
 */

public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private List<T> dataList;
    private int LayoutId;
    private Context context;
    private LayoutInflater inflater;
    public OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View itemView,int position);
    }
    public void setOnitemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }




    public BaseRecycleViewAdapter(List<T> mlist, int layoutId, Context context) {
        this.dataList = mlist;
        LayoutId = layoutId;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public BaseRecycleViewAdapter(List<T> dataList, int layoutId, Context context, LayoutInflater inflater, OnItemClickListener onItemClickListener) {
        this.dataList = dataList;
        LayoutId = layoutId;
        this.context = context;
        this.inflater = inflater;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(LayoutId,null);
        BaseViewHolder holder=new BaseViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        bindData(holder,dataList.get(position),position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view,position);
            }
        });
    }
    protected abstract void bindData(BaseViewHolder holder, T data, int position);
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
