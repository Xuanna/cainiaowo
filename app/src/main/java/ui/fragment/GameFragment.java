package ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.custom.cainiaowo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ui.BaseFragment;

/**
 * Created by xuchichi on 2017/9/29.
 */
public class GameFragment extends BaseFragment {
    @InjectView(R.id.recycleView)
    RecyclerView recycleView;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.Srl_refresh)
    SwipeRefreshLayout SrlRefresh;

    @Override
    public int setLayout() {
        return R.layout.fragment_game;
    }

    @Override
    public void initView() {
        toolbar.setTitle("使用recycleview，Logo");
        toolbar.setLogo(R.drawable.account);
        toolbar.setLogoDescription("Logo的描述");
        toolbar.setNavigationIcon(R.drawable.form);
        toolbar.setSubtitle("Subtitle");
        toolbar.inflateMenu(R.menu.recycle_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Linear:
                        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.Grid:
                        recycleView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.Staggered:
                        recycleView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                        adapter.notifyDataSetChanged();
                        break;

                }
                return false;
            }
        });
        initRefresh();
        initRecycle();
    }

    RecycleAdapter adapter;
    public void initRefresh(){
        SrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(),"555",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void initRecycle() {
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecycleAdapter();
        recycleView.setAdapter(adapter);
    }

    public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_textview, null);
            return new MyViewHolder(view);
        }

        @Override
        public int getItemCount() {
            return 20;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText("第" + (position + 1) + "个值");
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.tv);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
