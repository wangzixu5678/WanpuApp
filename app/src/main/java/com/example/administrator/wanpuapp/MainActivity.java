package com.example.administrator.wanpuapp;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.wanpuapp.fragments.HomeFragment;
import com.example.administrator.wanpuapp.fragments.MessageFragment;
import com.example.administrator.wanpuapp.fragments.ProductFragment;
import com.example.administrator.wanpuapp.fragments.ResourceFragment;
import com.example.administrator.wanpuapp.fragments.UserFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.main_home)
    RadioButton mMainHome;
    @BindView(R.id.main_message)
    RadioButton mMainMessage;
    @BindView(R.id.main_resource)
    RadioButton mMainResource;
    @BindView(R.id.main_product)
    RadioButton mMainProduct;
    @BindView(R.id.main_user)
    RadioButton mMainUser;
    @BindView(R.id.main_radiogroup)
    RadioGroup mMainRadiogroup;
    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private ResourceFragment mResourceFragment;
    private UserFragment mUserFragment;
    private ProductFragment mProductFragment;
    private Drawable mDrawable_home_white;
    private Drawable mDrawable_home_black;
    private Drawable mDrawable_message_white;
    private Drawable mDrawable_message_black;
    private Drawable mDrawable_supply_white;
    private Drawable mDrawable_supply_black;
    private Drawable mDrawable_product_white;
    private Drawable mDrawable_product_black;
    private Drawable mDrawable_me_white;
    private Drawable mDrawable_me_black;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDrawable();
        initFragment();
        mMainRadiogroup.setOnCheckedChangeListener(this);
        mMainRadiogroup.check(R.id.main_home);
    }

    private void initDrawable() {
        mDrawable_home_white = this.getResources().getDrawable(R.drawable.home_white);
        mDrawable_home_black = this.getResources().getDrawable(R.drawable.home_black);
        mDrawable_message_white = this.getResources().getDrawable(R.drawable.message_white);
        mDrawable_message_black = this.getResources().getDrawable(R.drawable.message_black);
        mDrawable_supply_white = this.getResources().getDrawable(R.drawable.supply_white);
        mDrawable_supply_black = this.getResources().getDrawable(R.drawable.supply_black);
        mDrawable_product_white = this.getResources().getDrawable(R.drawable.product_white);
        mDrawable_product_black = this.getResources().getDrawable(R.drawable.product_black);
        mDrawable_me_white = this.getResources().getDrawable(R.drawable.me_white);
        mDrawable_me_black = this.getResources().getDrawable(R.drawable.me_black);
    }

    private void initFragment() {
        mHomeFragment = new HomeFragment();
        mMessageFragment = new MessageFragment();
        mResourceFragment = new ResourceFragment();
        mProductFragment = new ProductFragment();
        mUserFragment = new UserFragment();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //初始化Fragment管理器
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = manager.beginTransaction();
        //初始化GroupButton
        mMainHome.setCompoundDrawablesRelativeWithIntrinsicBounds(null,mDrawable_home_white,null,null);
        mMainMessage.setCompoundDrawablesRelativeWithIntrinsicBounds(null,mDrawable_message_white,null,null);
        mMainResource.setCompoundDrawablesRelativeWithIntrinsicBounds(null,mDrawable_supply_white,null,null);
        mMainProduct.setCompoundDrawablesRelativeWithIntrinsicBounds(null,mDrawable_product_white,null,null);
        mMainUser.setCompoundDrawablesRelativeWithIntrinsicBounds(null,mDrawable_me_white,null,null);
        switch (checkedId){
            case R.id.main_home:
                mMainHome.setCompoundDrawablesWithIntrinsicBounds(null,mDrawable_home_black,null,null);
                beginTransaction.replace(R.id.main_container,mHomeFragment);
                break;
            case R.id.main_message:
                mMainMessage.setCompoundDrawablesWithIntrinsicBounds(null,mDrawable_message_black,null,null);
                beginTransaction.replace(R.id.main_container,mMessageFragment);
                break;
            case R.id.main_resource:
                mMainResource.setCompoundDrawablesWithIntrinsicBounds(null,mDrawable_supply_black,null,null);
                beginTransaction.replace(R.id.main_container,mResourceFragment);
                break;
            case R.id.main_product:
                mMainProduct.setCompoundDrawablesWithIntrinsicBounds(null,mDrawable_product_black,null,null);
                beginTransaction.replace(R.id.main_container,mProductFragment);
                break;
            case R.id.main_user:
                mMainUser.setCompoundDrawablesWithIntrinsicBounds(null,mDrawable_me_black,null,null);
                beginTransaction.replace(R.id.main_container,mUserFragment);
                break;
        }
        beginTransaction.commit();
    }
}
