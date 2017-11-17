package ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ui.BaseFragment;
import ui.adapter.BaseRecycleViewAdapter;
import ui.adapter.BaseViewHolder;
import ui.entiity.User;

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
    List<User> mlist=new ArrayList<>();
    @Override
    public int setLayout() {
        return R.layout.fragment_game;
    }

    @Override
    public void initView() {
        User user;
        for (int i=0;i<10;i++){
            user=new User("张三"+(i+1),"Email"+(i+1));
            mlist.add(user);
        }
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
        adapter = new RecycleAdapter(mlist,R.layout.item_textview,getActivity());
        recycleView.setAdapter(adapter);
        recycleView.addItemDecoration(new utils.DividerItemDecoration(getActivity()));
//        recycleView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
    }
    public class RecycleAdapter extends BaseRecycleViewAdapter<User>{

        public RecycleAdapter(List<User> mlist, int layoutId, Context context) {
            super(mlist, layoutId, context);
        }

        @Override
        protected void bindData(BaseViewHolder holder, User data, int position) {
            TextView tv=holder.getView(R.id.tv);
            tv.setText(data.email);
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
