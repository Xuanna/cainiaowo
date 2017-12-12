package ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by chichi on 2017/10/5.
 */

public abstract class BaseFragment extends Fragment{
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    if(view==null){
        view=inflater.inflate(setLayout(),null);
    }
        ButterKnife.inject(this,view);
        initView();
        return view;
    }
    public abstract int setLayout();
    public abstract void initView();

    @Override
    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint();
        // TODO: 2017/12/12
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
