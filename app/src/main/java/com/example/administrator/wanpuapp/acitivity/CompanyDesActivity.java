package com.example.administrator.wanpuapp.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.wanpuapp.R;
import com.example.administrator.wanpuapp.model.CompanyInfoModel;
import com.example.administrator.wanpuapp.net.NetService;
import com.example.administrator.wanpuapp.utils.NetUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompanyDesActivity extends AppCompatActivity {

    @BindView(R.id.acti_companydes_toolbar)
    Toolbar mActiCompanydesToolbar;
    @BindView(R.id.acti_companydes_des)
    EditText mActiCompanydesDes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_des);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String des = intent.getStringExtra("des");
        mActiCompanydesDes.setText(des);

        /**
         * 设置ActionBar
         */
        if (mActiCompanydesToolbar != null) {
            setSupportActionBar(mActiCompanydesToolbar);
            ActionBar bar = getSupportActionBar();
            bar.setDisplayShowTitleEnabled(false);
            bar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @OnClick(R.id.acti_companydes_save)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.acti_companydes_save:
                CompanyInfoModel companyInfoModel = CompanyInfoModel.getNewInstance();
                String companyDes = mActiCompanydesDes.getText().toString().trim();
                companyInfoModel.setCompanyDes(companyDes);
                /**
                 * 上传到服务器
                 */
                Intent intent = getIntent();
                String sId = intent.getStringExtra("id");
                int id = Integer.parseInt(sId);
                NetService netServices = NetUtil.getNetServices();

                break;
        }
        finish();

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
