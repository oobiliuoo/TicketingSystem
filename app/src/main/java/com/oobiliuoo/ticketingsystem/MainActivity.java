package com.oobiliuoo.ticketingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.oobiliuoo.ticketingsystem.adapter.MyFragmentPagerAdapter;
import com.oobiliuoo.ticketingsystem.ui.HomeFragment;
import com.oobiliuoo.ticketingsystem.ui.MineFragment;
import com.oobiliuoo.ticketingsystem.ui.NotifyFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ViewPager2 viewPager;
    private LinearLayout llHome, llNotify,llMine;
    private ImageView ivHome, ivNotify,ivMine,ivCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 键盘弹出时不改变布局
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        setContentView(R.layout.activity_main);

        initPager();
        initTabView();

    }

    private void initPager() {
        viewPager = findViewById(R.id.id_viewpager);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("通信录","参数"));
        fragments.add(NotifyFragment.newInstance("发现","参数"));
        fragments.add(MineFragment.newInstance("我","参数"));
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),getLifecycle(),fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

    }


    private void initTabView() {

        llHome = findViewById(R.id.id_tab_home);
        llHome.setOnClickListener(this);
        llNotify = findViewById(R.id.id_tab_order);
        llNotify.setOnClickListener(this);
        llMine = findViewById(R.id.id_tab_mine);
        llMine.setOnClickListener(this);

        ivHome = findViewById(R.id.id_iv_home);
        ivNotify = findViewById(R.id.id_iv_order);
        ivMine = findViewById(R.id.id_iv_mine);

        ivHome.setSelected(true);
        ivCurrent = ivHome;
    }

    private void changeTab(int position) {
        ivCurrent.setSelected(false);
        switch (position){
            case R.id.id_tab_home:
                viewPager.setCurrentItem(0);
            case 0:
                ivHome.setSelected(true);
                ivCurrent = ivHome;
                break;
            case R.id.id_tab_order:
                viewPager.setCurrentItem(1);
            case 1:
                ivNotify.setSelected(true);
                ivCurrent = ivNotify;
                break;
            case R.id.id_tab_mine:
                viewPager.setCurrentItem(2);
            case 2:
                ivMine.setSelected(true);
                ivCurrent = ivMine;
                break;

        }
    }

    @Override
    public void onClick(View v) {
        changeTab(v.getId());
    }
}