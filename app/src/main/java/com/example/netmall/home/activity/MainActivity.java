package com.example.netmall.home.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.netmall.R;
import com.example.netmall.community.fragment.CommunityFragment;
import com.example.netmall.home.fragment.HomeFragment;
import com.example.netmall.shoppingcart.fragment.ShoppingcartFragment;
import com.example.netmall.type.fragment.TypeFragment;
import com.example.netmall.user.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.main_fl_fragment)
    FrameLayout mainFlFragment;
    @InjectView(R.id.main_rb_home)
    RadioButton mainRbHome;
    @InjectView(R.id.main_rb_type)
    RadioButton mainRbType;
    @InjectView(R.id.main_rb_community)
    RadioButton mainRbCommunity;
    @InjectView(R.id.main_rb_cart)
    RadioButton mainRbCart;
    @InjectView(R.id.main_rb_user)
    RadioButton mainRbUser;
    @InjectView(R.id.main_rg)
    RadioGroup mainRg;

    private List<Fragment> fragments;
    private int position;
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initFragment();

        initListener();
    }


    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingcartFragment());
        fragments.add(new UserFragment());
    }

    private void initListener() {
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.main_rb_home:
                        position = 0;
                        break;
                    case R.id.main_rb_type:
                        position = 1;
                        break;
                    case R.id.main_rb_community:
                        position = 2;
                        break;
                    case R.id.main_rb_cart:
                        position = 3;
                        break;
                    case R.id.main_rb_user:
                        position = 4;
                        break;
                }
                Fragment fragment = fragments.get(position);
                switchFragment(fragment);
            }

        });
        mainRg.check(R.id.main_rb_home);
    }

    private void switchFragment(Fragment currentFragment) {

        if (currentFragment != tempFragment) {
            if (currentFragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //如果没有添加就添加
                if (!currentFragment.isAdded()) {
                    //隐藏之前的
                    if (tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    ft.add(R.id.main_fl_fragment, currentFragment);
                } else {
                    //隐藏上次显示的
                    if (tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    ft.show(currentFragment);
                }
                ft.commit();
            }
            tempFragment = currentFragment;
        }
    }
}
