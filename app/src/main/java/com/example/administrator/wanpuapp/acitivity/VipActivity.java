package com.example.administrator.wanpuapp.acitivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.example.administrator.wanpuapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VipActivity extends AppCompatActivity {

    @BindView(R.id.acti_vip_toolbar)
    Toolbar mActiVipToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);
        ButterKnife.bind(this);
        /**
         * 设置Toolbar
         */
        if (mActiVipToolbar!=null) {
            setSupportActionBar(mActiVipToolbar);
            ActionBar bar = getSupportActionBar();
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
