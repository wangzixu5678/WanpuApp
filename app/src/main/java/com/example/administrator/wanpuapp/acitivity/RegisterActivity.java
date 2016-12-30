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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.wanpuapp.R;
import com.example.administrator.wanpuapp.net.NetService;
import com.example.administrator.wanpuapp.utils.NetUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_toolbar)
    Toolbar mRegisterToolbar;
    @BindView(R.id.register_phonenumber)
    EditText mRegisterPhonenumber;
    @BindView(R.id.register_checkcode)
    EditText mRegisterCheckcode;
    @BindView(R.id.rigister_checkbox)
    CheckBox mRigisterCheckbox;
    private String mState;
    private String mVerify;
    private String mPhonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 隐藏状态栏
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        /**
         * 设置Toolbar
         */
        setSupportActionBar(mRegisterToolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false);
            bar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @OnClick({R.id.register_getCheckcode, R.id.rigister_btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_getCheckcode:
                mPhonenumber = mRegisterPhonenumber.getText().toString().trim();
                Log.d("Regisiter", "onClick:"+mPhonenumber);
                if (mPhonenumber.length() == 11) {
                    getSmsCheckCode(mPhonenumber);
                } else {
                    Log.d("Regisiter", "onClick:"+"手机号码格式不正确");
                    Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rigister_btn_register:
                if (mRigisterCheckbox.isChecked()){
                    String checkcode = mRegisterCheckcode.getText().toString().trim();
                    if (checkcode.length()!=0){
                        if (mState.equals("ok")&&mVerify.length()!=0) {
                            if (mVerify.equals(checkcode)){
                                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(this,PasswordActivity.class);
                                intent.putExtra("phonenumber",mPhonenumber);
                                startActivity(intent);
                            }else {
                                Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(this, "验证码接收失败", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(this, "请阅读服务协议", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    private void getSmsCheckCode(String phonenumber) {
        NetService netServices = NetUtil.getNetServices();
        Call<String> call = netServices.getSmsResult(phonenumber);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String json = response.body();
                Log.d("Register", "onResponse: "+json);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(json);
                    mState = jsonObject.getString("state");
                    mVerify = jsonObject.getString("verify");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Register", "onFailure:" + "验证码获取失败");

            }
        });
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
