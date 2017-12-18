package ui.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.custom.cainiaowo.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dmax.dialog.SpotsDialog;
import ui.BaseFragment;
import ui.tools.GlideImageLoader;

/**
 * A simple {@link Fragment} subclass.
 */
public class BannerFragment extends BaseFragment {

    @InjectView(R.id.banner)
    Banner banner;
    List<Integer> images=new ArrayList<>();
    List<String> title=new ArrayList<>();
    @Override
    public int setLayout() {
        return R.layout.fragment_blank;
    }

    @Override
    public void initView() {
        title.clear();
        images.clear();
        images.add(R.drawable.iv_home_banner_eight);
        images.add(R.drawable.iv_home_banner_five);
        images.add(R.drawable.iv_home_banner_four);
        images.add(R.drawable.iv_home_banner_first);

        title.add("标题1");
        title.add("标题2");
        title.add("标题3");
        title.add("标题4");

        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerTitles(title);
        //设置图片集合
        banner.setImages(images);
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
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
