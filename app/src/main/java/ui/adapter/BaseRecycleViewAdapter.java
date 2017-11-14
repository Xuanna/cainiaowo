package ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by xuchichi on 2017/11/7.
 */

public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private List<T> dataList;
    private int LayoutId;
    private Context context;
    private LayoutInflater inflater;

    public BaseRecycleViewAdapter(List<T> mlist, int layoutId, Context context) {
        this.dataList = mlist;
        LayoutId = layoutId;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(LayoutId,parent,false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        bindData(holder,dataList.get(position),position);
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
