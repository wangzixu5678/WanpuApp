package com.example.administrator.wanpuapp.acitivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.wanpuapp.MainActivity;
import com.example.administrator.wanpuapp.R;
import com.example.administrator.wanpuapp.net.NetService;
import com.example.administrator.wanpuapp.utils.Md5Util;
import com.example.administrator.wanpuapp.utils.NetUtil;
import com.example.administrator.wanpuapp.utils.SharedPreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.acti_login_userphone)
    EditText mActiLoginUserphone;
    @BindView(R.id.acti_login_userpass)
    EditText mActiLoginUserpass;
    private Context mContext = this;
    private SharedPreferencesUtil mSharedPreferencesUtil;

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
        setContentView(R.layout.activity_login);
        mContext = this;
        ButterKnife.bind(this);
        mSharedPreferencesUtil = SharedPreferencesUtil.getInstance();

    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * 初始化用户手机 密码
         */
        String userphone = mSharedPreferencesUtil.getString("userphone");
        String userpass = mSharedPreferencesUtil.getString("userpass");
        mActiLoginUserphone.setText(userphone);
        mActiLoginUserpass.setText(userpass);

    }

    @OnClick({R.id.acti_login_laungh, R.id.acti_login_register})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.acti_login_laungh:
                final String userPhone = mActiLoginUserphone.getText().toString().trim();
                final String userPass = mActiLoginUserpass.getText().toString().trim();
                if (userPhone.length()==11){
                    if (!userPass.equals("")){
                        NetService netServices = NetUtil.getNetServices();
                        String md5 = Md5Util.MD5(userPass);
                        Call<String> call = netServices.loginUser(userPhone, md5);
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String body = response.body();
                                try {
                                    JSONObject jsonObject = new JSONObject(body);
                                    String state = jsonObject.optString("state");
                                    if (state.equals("0")){
                                          Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                        /**
                                         * 存入sharePreference
                                         */
                                        mSharedPreferencesUtil.putString("userphone",userPhone);
                                        mSharedPreferencesUtil.putString("userpass",userPass);
                                        /**
                                         * 跳转
                                         */
                                          Intent mainIntent  = new Intent(mContext,MainActivity.class);
                                          mainIntent.putExtra("companyinfo",body);
                                          startActivity(mainIntent);
                                    }else if (state.equals("1")){
                                        Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });

                    }else {
                        Toast.makeText(this, "请输入用户密码", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "请输入正确手机号码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.acti_login_register:
                Log.d("LoginActivity", "onClick:+++++ ");
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
