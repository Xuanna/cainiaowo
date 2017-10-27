package utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xuchichi on 2017/10/27.
 */
public interface AdapterOnItemclick {

    void onItemClick(RecyclerView.ViewHolder holder,int position);
}
