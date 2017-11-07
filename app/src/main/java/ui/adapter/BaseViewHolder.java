package ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xuchichi on 2017/11/7.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private View itemView;
    private Context context;


    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public static BaseViewHolder getHolder(Context context,int LayoutId){
        View view=LayoutInflater.from(context).inflate(LayoutId,null);
        return new BaseViewHolder(view);
    }
}

