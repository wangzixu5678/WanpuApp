package com.example.administrator.wanpuapp.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.administrator.wanpuapp.R;
import com.example.administrator.wanpuapp.model.CompanyInfoModel;
import com.example.administrator.wanpuapp.net.NetService;
import com.example.administrator.wanpuapp.utils.NetUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyNameActivity extends AppCompatActivity {

    @BindView(R.id.acti_companyname_companyname)
    EditText mActiCompanynameCompanyname;
    @BindView(R.id.activity_company_name)
    LinearLayout mActivityCompanyName;
    @BindView(R.id.acti_company_toolbar)
    Toolbar mActiCompanyToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_name);
        ButterKnife.bind(this);
        /**
         * 设置ActionBar
         */
        if (mActiCompanyToolbar!=null) {
            setSupportActionBar(mActiCompanyToolbar);
        }
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.acti_company_save)
    public void onClick(View view){
        CompanyInfoModel companyInfoModel = CompanyInfoModel.getNewInstance();
        switch (view.getId()){
            case R.id.acti_company_save:
                /**
                 * 获取公司名并发送
                 */
                String companyName = mActiCompanynameCompanyname.getText().toString().trim();
                companyInfoModel.setCompanyName(companyName);
                EventBus.getDefault().post(companyInfoModel);
                /**
                 * 上传到服务器
                 */
                NetService netServices = NetUtil.getNetServices();
                Intent intent = getIntent();
                String id = intent.getStringExtra("id");
                Call<String> call = netServices.changeCompanyName(id, companyName);
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
                
                finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return true;
    }
}
