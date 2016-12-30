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
import android.widget.Toast;

import com.example.administrator.wanpuapp.R;
import com.example.administrator.wanpuapp.net.NetService;
import com.example.administrator.wanpuapp.utils.Md5Util;
import com.example.administrator.wanpuapp.utils.NetUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordActivity extends AppCompatActivity {

    @BindView(R.id.password_toolbar)
    Toolbar mPasswordToolbar;
    @BindView(R.id.acti_setpassword)
    EditText mActiSetpassword;
    @BindView(R.id.acti_setpasswordagain)
    EditText mActiSetpasswordagain;
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
        setContentView(R.layout.activity_password);
        ButterKnife.bind(this);
        if (mPasswordToolbar != null) {
            setSupportActionBar(mPasswordToolbar);
            ActionBar bar = getSupportActionBar();
            bar.setDisplayShowTitleEnabled(false);
            bar.setDisplayHomeAsUpEnabled(true);
        }
        //获取手机号
        Intent intent = getIntent();
        mPhonenumber = intent.getStringExtra("phonenumber");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "返回上一层", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return true;
    }

    @OnClick(R.id.acti_setpassword_complete)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.acti_setpassword_complete:
                String fPassword = mActiSetpassword.getText().toString().trim();
                String sPassword = mActiSetpasswordagain.getText().toString().trim();
                if (!fPassword.equals("")) {
                    if (fPassword.equals(sPassword)) {
                        if (!mPhonenumber.equals("")) {
                            String md5 = Md5Util.MD5(fPassword);
                            NetService netServices = NetUtil.getNetServices();
                            Call<String> call = netServices.registerUser(mPhonenumber, md5);
                            call.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    String json = response.body();
                                    try {
                                        JSONObject jsonObject = new JSONObject(json);
                                        String state = jsonObject.getString("state");
                                        if (state!=null){
                                            if (state.equals("0")){
                                                Toast.makeText(PasswordActivity.this, "注册成功", Toast.LENGTH_SHORT).show();

                                            }else if (state.equals("1")){
                                                Toast.makeText(PasswordActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {

                                }
                            });
                        }
                    }else {
                        Toast.makeText(this, "两次密码不一致，请检查", Toast.LENGTH_SHORT).show();
                        break;
                    }

                }else {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    break;
                }
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}