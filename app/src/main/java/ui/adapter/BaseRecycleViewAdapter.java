package ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import utils.OnItemClickListener;

/**
 * Created by xuchichi on 2017/11/7.
 */

public class BaseRecycleViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    List<Objects> mlist;
    int LayoutId;
    Context context;
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.getHolder(context,LayoutId);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
