package ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by chichi on 2017/10/5.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        ButterKnife.inject(this);
        initView();
    }

    public abstract int setLayout();

    public abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
