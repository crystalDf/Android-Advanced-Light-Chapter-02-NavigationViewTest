package com.star.navigationviewtest;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class NavigationViewActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;

    private ActionBar mActionBar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private FragmentAdapter mFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mActionBar = getSupportActionBar();
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mActionBar.setDisplayHomeAsUpEnabled(true);

        mViewPager = findViewById(R.id.viewpager);
        initViewPager();

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);

        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(item -> {
                item.setChecked(true);

                String title = item.getTitle().toString();

                Toast.makeText(getApplicationContext(), title, Toast.LENGTH_LONG).show();

                mDrawerLayout.closeDrawers();

                return true;
            });
        }
    }

    private void initViewPager() {

        mTabLayout = findViewById(R.id.tab_layout);

        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();

        titles.add("精选");
        titles.add("体育");
        titles.add("巴萨");
        titles.add("购物");
        titles.add("明星");
        titles.add("视频");
        titles.add("健康");
        titles.add("励志");
        titles.add("图文");
        titles.add("本地");
        titles.add("动漫");
        titles.add("搞笑");
        titles.add("精选");

        for (String title : titles) {

            mTabLayout.addTab(mTabLayout.newTab().setText(title));
            fragments.add(new ListFragment());
        }

        mFragmentAdapter = new FragmentAdapter(
                getSupportFragmentManager(), fragments, titles);

        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
