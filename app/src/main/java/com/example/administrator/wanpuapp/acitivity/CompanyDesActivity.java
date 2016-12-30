package com.example.administrator.wanpuapp.acitivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.administrator.wanpuapp.R;
import com.example.administrator.wanpuapp.model.CompanyInfoModel;
import com.example.administrator.wanpuapp.net.NetService;
import com.example.administrator.wanpuapp.utils.NetUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyDesActivity extends AppCompatActivity {

    @BindView(R.id.acti_companydes_toolbar)
    Toolbar mActiCompanydesToolbar;
    @BindView(R.id.acti_companydes_des)
    EditText mActiCompanydesDes;
    private CompanyInfoModel mCompanyInfoModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_des);
        ButterKnife.bind(this);
        mCompanyInfoModel = CompanyInfoModel.getNewInstance();
        mActiCompanydesDes.setText(mCompanyInfoModel.getCompanyDes());

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

                 String companyDes = mActiCompanydesDes.getText().toString().trim();
                 mCompanyInfoModel.setCompanyDes(companyDes);
                /**
                 * 上传到服务器
                 */
                NetService netServices = NetUtil.getNetServices();
                Call<String> call = netServices.changeCompanyDes(mCompanyInfoModel.getCompanyId(), mActiCompanydesDes.getText().toString());
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String body = response.body();
                        Log.d("CompanyNameActivity", "onResponse:"+body);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

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
