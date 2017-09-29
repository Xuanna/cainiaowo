package ui.entiity;

import android.support.v4.app.Fragment;

/**
 * Created by xuchichi on 2017/9/29.
 */
public class FragmentInfo {
    public String title;
    public Fragment fragment;

    public FragmentInfo(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }
}
