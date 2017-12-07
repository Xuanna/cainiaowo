package ui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
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
        tabhost.setup(getContext(),getChildFragmentManager(),R.id.realTanContent);
        mlist.add(new Tab("推荐1",CommendFragment.class));
        mlist.add(new Tab("推荐2",SortFragment.class));
        mlist.add(new Tab("推荐3",GameFragment.class));
        for (Tab tab:mlist) {
            tabhost.addTab(getTab(tab),tab.getFragment(),null);
        }
        tabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);//去掉分割线

        tabhost.setCurrentTab(0);
//
//        TabHost.TabSpec tabSpec=tabhost.newTabSpec("fg1");
//        View view=LayoutInflater.from(getContext()).inflate(R.layout.item_textview,null);
//        TextView tv1= (TextView) view.findViewById(R.id.tv);
//        tv1.setText("排行1");
//        tabSpec.setIndicator(view);
//
//        TabHost.TabSpec tabSpec2=tabhost.newTabSpec("fg2");
//        View view2=LayoutInflater.from(getContext()).inflate(R.layout.item_textview,null);
//        TextView tv2= (TextView) view2.findViewById(R.id.tv);
//        tv2.setText("排行2");
//        tabSpec2.setIndicator(view2);
//
//        TabHost.TabSpec tabSpec3=tabhost.newTabSpec("fg3");
//        View view3=LayoutInflater.from(getContext()).inflate(R.layout.item_textview,null);
//        TextView tv3= (TextView) view3.findViewById(R.id.tv);
//        tv3.setText("排行3");
//        tabSpec3.setIndicator(view3);
//
//        tabhost.addTab(tabSpec,CommendFragment.class,null);
//        tabhost.addTab(tabSpec2,SortFragment.class,null);
//        tabhost.addTab(tabSpec3,GameFragment.class,null);

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
