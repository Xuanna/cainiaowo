package ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.custom.cainiaowo.R;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ui.adapter.ViewpagerAdapter;
import ui.entiity.FragmentInfo;
import ui.fragment.CommendFragment;
import ui.fragment.GameFragment;
import ui.fragment.RankFragment;
import ui.fragment.SortFragment;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.design_navigation_view)
    NavigationView designNavigationView;
    @InjectView(R.id.drawlayout)
    DrawerLayout drawlayout;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tab_layout)
    TabLayout tabLayout;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;

    List<FragmentInfo> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        init();
        initTablayout();
    }

    private void initTablayout() {
        list.add(new FragmentInfo("推荐",new CommendFragment()));
        list.add(new FragmentInfo("排行",new RankFragment()));
        list.add(new FragmentInfo("游戏",new GameFragment()));
        list.add(new FragmentInfo("分类",new SortFragment()));

        ViewpagerAdapter adapter=new ViewpagerAdapter(getSupportFragmentManager(),list);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
    }

    public void init() {
        toolbar.inflateMenu(R.menu.toolbar_menu);
        View view = designNavigationView.getHeaderView(0);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "头部点击", Toast.LENGTH_LONG).show();
            }
        });
        designNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete:
                        Toast.makeText(MainActivity.this, "delete", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.form:
                        Toast.makeText(MainActivity.this, "form", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.mine:
                        Toast.makeText(MainActivity.this, "mine", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawlayout, toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();

        drawlayout.addDrawerListener(actionBarDrawerToggle);
    }
}
