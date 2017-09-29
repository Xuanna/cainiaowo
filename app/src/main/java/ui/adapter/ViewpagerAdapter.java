package ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import ui.entiity.FragmentInfo;

/**
 * Created by xuchichi on 2017/9/29.
 */
public class ViewpagerAdapter extends FragmentPagerAdapter {
    List<FragmentInfo> list;

    public ViewpagerAdapter(FragmentManager fm, List<FragmentInfo> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position).fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).title;
    }
}
