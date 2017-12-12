package ui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.custom.cainiaowo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ui.BaseFragment;
import ui.adapter.ViewpagerAdapter;
import ui.entiity.Tab;

/**
 * Created by xuchichi on 2017/9/29.
 * 这里我用的Fragment，Activity里面也是一样的用法
 */
public class RankFragment extends BaseFragment {
    @InjectView(R.id.realTanContent)
    FrameLayout realTanContent;
    @InjectView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @InjectView(android.R.id.tabhost)
    FragmentTabHost tabhost;
    List<Tab> mlist=new ArrayList<>();
    @Override
    public int setLayout() {
        return R.layout.fragment_rank;
    }

    @Override
    public void initView() {
        initTabs();
    }
    public void initTabs(){
        Log.e("init","initTabs");
//        mlist.clear();
        tabhost.setup(getContext(),getChildFragmentManager(),R.id.realTanContent);
        mlist.add(new Tab("轮播图",BannerFragment.class));
        mlist.add(new Tab("推荐2",SortFragment.class));
        mlist.add(new Tab("推荐3",GameFragment.class));
//        tabhost.clearAllTabs();
        for (Tab tab:mlist) {
            tabhost.addTab(getTab(tab),tab.getFragment(),null);
        }
        tabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);//去掉分割线
        tabhost.setCurrentTab(0);
    }
    public TabHost.TabSpec getTab(Tab tab){
        TabHost.TabSpec tabSpec=tabhost.newTabSpec(tab.getTitle());
        View view=LayoutInflater.from(getContext()).inflate(R.layout.item_textview,null);
        TextView tv= (TextView) view.findViewById(R.id.tv);
        tv.setText(tab.getTitle());
        tabSpec.setIndicator(view);
        return tabSpec;
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
