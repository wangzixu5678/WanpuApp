package com.example.administrator.wanpuapp.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.wanpuapp.R;
import com.example.administrator.wanpuapp.model.CompanyInfoModel;
import com.example.administrator.wanpuapp.net.NetService;
import com.example.administrator.wanpuapp.utils.NetUtil;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneNumberActivity extends AppCompatActivity {

    @BindView(R.id.acti_phonenumber_toolbar)
    Toolbar mActiPhonenumberToolbar;
    @BindView(R.id.acti_phonenumber_phonenumber)
    EditText mActiPhonenumberPhonenumber;
    @BindView(R.id.acti_phonenumber_checkcode)
    EditText mActiPhonenumberCheckcode;
    @BindView(R.id.acti_phonenumber_sendcheckcode)
    TextView mActiPhonenumberSendcheckcode;
    @BindView(R.id.acti_phonenumber_commit)
    Button mActiPhonenumberCommit;
    private String mState;
    private String mVerify;
    private String mPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        ButterKnife.bind(this);
        /**
         * 设置Toolbar
         */
        setSupportActionBar(mActiPhonenumberToolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false);
            bar.setDisplayHomeAsUpEnabled(true);
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

    @OnClick({R.id.acti_phonenumber_sendcheckcode,R.id.acti_phonenumber_commit})
    public void onClick(View view){
        NetService netServices = NetUtil.getNetServices();
        switch (view.getId()){
            case R.id.acti_phonenumber_sendcheckcode:
                mPhoneNumber = mActiPhonenumberPhonenumber.getText().toString().trim();
                if (mPhoneNumber.length()==11){
                    Call<String> call = netServices.getSmsResult(mPhoneNumber);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String body = response.body();
                            Log.d("PhoneActivity", "onResponse:" + body);
                            try {
                                JSONObject jsonObject = new JSONObject(body);
                                mState = jsonObject.getString("state");
                                mVerify = jsonObject.getString("verify");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }else {
                    Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.acti_phonenumber_commit:
                String checkCode = mActiPhonenumberCheckcode.getText().toString().trim();
                if (checkCode.length()!=0){
                    if (mState.equals("ok")&&mVerify.length()!=0){
                        if (mVerify.equals(checkCode)){
                            CompanyInfoModel companyInfoModel = CompanyInfoModel.getNewInstance();
                            companyInfoModel.setPhoneNumber(mPhoneNumber);
                            EventBus.getDefault().post(companyInfoModel);
                            /**
                             * 上传绑定手机号到服务器
                             */
                            String id = CompanyInfoModel.getNewInstance().getCompanyId();
                            Call<String> call = netServices.changePhoneNumber(id,mPhoneNumber);
                            call.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    //上传成功
                                    Log.d("PhoneNumber", "onResponse:" +response.body());
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {

                                }
                            });

                            finish();
                        }else {
                            Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(this, "验证码接收失败", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
