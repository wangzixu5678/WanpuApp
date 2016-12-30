package com.example.administrator.wanpuapp;

import android.content.Intent;
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
import com.example.administrator.wanpuapp.model.CompanyInfoModel;
import com.google.gson.JsonObject;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;
import org.json.JSONObject;

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
    private ProductFragment mProductFragment;
    private UserFragment mUserFragment;
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
    private FragmentManager mManager;
    private FragmentTransaction mTransaction;
    private boolean isFirst=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 状态栏
         */
        setLand();
        ButterKnife.bind(this);
        initDrawable();

        /**
         * 初始化公司信息
         */
        Intent intent = getIntent();
        String companyinfo = intent.getStringExtra("companyinfo");
        try {
            JSONObject jsonObject = new JSONObject(companyinfo);
            JSONObject data = jsonObject.getJSONObject("data");
            CompanyInfoModel infoModel = CompanyInfoModel.getNewInstance();
            infoModel.setCompanyId(data.getString("company_id"));
            infoModel.setPhoneNumber(data.getString("phone"));
            infoModel.setCompanyAddress(data.getString("address"));
            infoModel.setCompanyName(data.getString("name"));
            infoModel.setCompanyDes(data.getString("des"));
            infoModel.setCompanyLogo(data.getString("logo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /**
         * 初始化Fragment
         */
        mHomeFragment = new HomeFragment();
        mMessageFragment = new MessageFragment();
        mResourceFragment = new ResourceFragment();
        mProductFragment = new ProductFragment();
        mUserFragment = new UserFragment();
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


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mManager = getSupportFragmentManager();
        mTransaction = mManager.beginTransaction();
        while (isFirst) {
            mTransaction.add(R.id.main_container, mHomeFragment,"tab1");
            mTransaction.add(R.id.main_container, mMessageFragment,"tab2");
            mTransaction.add(R.id.main_container, mResourceFragment,"tab3");
            mTransaction.add(R.id.main_container, mProductFragment,"tab4");
            mTransaction.add(R.id.main_container, mUserFragment,"tab5");
            isFirst =false;
        }
        hideFragment(mTransaction);
        mMainHome.setCompoundDrawablesRelativeWithIntrinsicBounds(null,mDrawable_home_white,null,null);
        mMainMessage.setCompoundDrawablesRelativeWithIntrinsicBounds(null,mDrawable_message_white,null,null);
        mMainResource.setCompoundDrawablesRelativeWithIntrinsicBounds(null,mDrawable_supply_white,null,null);
        mMainProduct.setCompoundDrawablesRelativeWithIntrinsicBounds(null,mDrawable_product_white,null,null);
        mMainUser.setCompoundDrawablesRelativeWithIntrinsicBounds(null,mDrawable_me_white,null,null);
        switch (checkedId){
            case R.id.main_home:
                mMainHome.setCompoundDrawablesWithIntrinsicBounds(null,mDrawable_home_black,null,null);
                mTransaction.show(mHomeFragment);
                break;
            case R.id.main_message:
                mMainMessage.setCompoundDrawablesWithIntrinsicBounds(null,mDrawable_message_black,null,null);
                mTransaction.show(mMessageFragment);
                break;
            case R.id.main_resource:
                mMainResource.setCompoundDrawablesWithIntrinsicBounds(null,mDrawable_supply_black,null,null);
                mTransaction.show(mResourceFragment);
                break;
            case R.id.main_product:
                mMainProduct.setCompoundDrawablesWithIntrinsicBounds(null,mDrawable_product_black,null,null);
                mTransaction.show(mProductFragment);
                break;
            case R.id.main_user:
                mMainUser.setCompoundDrawablesWithIntrinsicBounds(null,mDrawable_me_black,null,null);
                mTransaction.show(mUserFragment);
                break;
        }
        mTransaction.commit();
    }
    private void hideFragment(FragmentTransaction transaction) {
            transaction.hide(mHomeFragment);
            transaction.hide(mMessageFragment);
            transaction.hide(mResourceFragment);
            transaction.hide(mProductFragment);
            transaction.hide(mUserFragment);
    }

    private void setLand() {
        /**
         * 设置沉浸状态栏
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // 激活状态栏
            tintManager.setStatusBarTintEnabled(true);
            // enable navigation bar tint 激活导航栏
            tintManager.setNavigationBarTintEnabled(true);
            //设置系统栏设置颜色
            //tintManager.setTintColor(R.drawable.black);
            //给状态栏设置颜色
            tintManager.setStatusBarTintResource(R.drawable.land_bg);
            //Apply the specified drawable or color resource to the system navigation bar.
            //给导航栏设置资源
            tintManager.setNavigationBarTintResource(R.drawable.land_bg);
        }
    }

}
