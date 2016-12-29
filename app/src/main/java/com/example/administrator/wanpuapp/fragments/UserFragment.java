package com.example.administrator.wanpuapp.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wanpuapp.R;
import com.example.administrator.wanpuapp.acitivity.CompanyDesActivity;
import com.example.administrator.wanpuapp.acitivity.CompanyNameActivity;
import com.example.administrator.wanpuapp.acitivity.PhoneNumberActivity;
import com.example.administrator.wanpuapp.acitivity.VipActivity;
import com.example.administrator.wanpuapp.model.CompanyInfoModel;
import com.example.administrator.wanpuapp.net.NetService;
import com.example.administrator.wanpuapp.utils.NetUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {


    @BindView(R.id.frag_user_toolbar)
    Toolbar mFragUserToolbar;
    @BindView(R.id.frag_user_setcompanyname)
    TextView mFragUserSetcompanyname;
    @BindView(R.id.frag_user_companyid)
    TextView mFragUserCompanyid;
    @BindView(R.id.frag_user_phonenumber)
    TextView mFragUserPhonenumber;
    @BindView(R.id.frag_user_companyname)
    TextView mFragUserCompanyname;
    @BindView(R.id.frag_user_head)
    ImageView mFragUserHead;
    private CompanyInfoModel mCompanyInfoModel;
    protected static Uri tempUri;
    public static final int CHOOSE_PICTURE = 0;
    public static final int TAKE_PICTURE = 1;
    private File mTargetFile;
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ret = null;
        ret = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, ret);
        EventBus.getDefault().register(this);
        mCompanyInfoModel = CompanyInfoModel.getNewInstance();
        return ret;
    }

    @OnClick({R.id.frag_user_changecompanyname, R.id.frag_user_changephonenumber, R.id.frag_user_openvip, R.id.frag_user_companydes, R.id.frag_user_head})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.frag_user_changecompanyname:
                intent = new Intent(getContext(), CompanyNameActivity.class);
                intent.putExtra("id", mFragUserCompanyid.getText());
                startActivity(intent);
                break;
            case R.id.frag_user_changephonenumber:
                intent = new Intent(getContext(), PhoneNumberActivity.class);
                intent.putExtra("id", mFragUserCompanyid.getText());
                startActivity(intent);
                break;
            case R.id.frag_user_openvip:
                intent = new Intent(getContext(), VipActivity.class);
                startActivity(intent);
                break;
            case R.id.frag_user_companydes:
                intent = new Intent(getContext(), CompanyDesActivity.class);
                intent.putExtra("id", mFragUserCompanyid.getText());
                intent.putExtra("des", mCompanyInfoModel.getCompanyDes());
                startActivity(intent);
                break;
            case R.id.frag_user_head:
                showChoosePicDialog();
                break;
        }
    }

    /**
     * 选择头像
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("设置头像");
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(intent, 999);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent intent_1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        // 使用 EXTRA_OUTPUT 指定 Uri 位置，可以把大尺寸原图保存到 Uri 指定的位置

                        String state = Environment.getExternalStorageState();
                        File dir = getActivity().getFilesDir();
                        if (state.equals(Environment.MEDIA_MOUNTED)) {
                            dir = Environment.getExternalStorageDirectory();
                        }

                        if (!dir.exists()) {
                            dir.mkdirs();
                        }

                        mTargetFile = new File(dir, "img-" + System.currentTimeMillis() + ".jpg");
                        Uri uri = Uri.fromFile(mTargetFile);
                        intent_1.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        startActivityForResult(intent_1,998);
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Log.d("UserFragment", "onActivityResult:" + data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 998) {
                //拍照
                // Bitmap bitmap = BitmapFactory.decodeFile(mTargetFile.getAbsolutePath());
                //Log.d("MianActivity", "onActivityResult:" + mTargetFile.getAbsoluteFile().toString());
                Uri uri = Uri.fromFile(mTargetFile);
                //Log.d("UserFragment", "onActivityResult:"+uri.getPath());
                imageUpLoad(uri.getPath());
                startPhotoZoom(uri);
            } else if (requestCode == 999) {
                //本地
                 //Log.d("UserFragment", "onActivityResult:"+data.getData().getPath());
                //uri上传服务器的地址
                imageUpLoad(data.getData().getPath());
                startPhotoZoom(data.getData());
            } else if (requestCode == 1000) {
                setImageToView(data);
            }
        }
    }

    /**
     * 上传图片到服务器
     * @param path
     */
    private void imageUpLoad(String path) {
        File file = new File(path);
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("image", "icon.png", photoRequestBody);
        NetService netServices = NetUtil.getNetServices();
        Call<String> call = netServices.upLoadPic(mFragUserCompanyid.getText().toString(),photo);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("UserFragment", "onResponse:"+"成功");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("UserFragment", "onResponse:"+"失败");
                Log.d("UserFragment", "onResponse:"+t.toString());
            }
        });
    }

//    private MultipartBody.Part prepareFilePart(String partName, String path) {
//        File file = new File(path);
//        // 为file建立RequestBody实例
//        RequestBody requestFile =
//                RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);
//        // MultipartBody.Part借助文件名完成最终的上传
//        return MultipartBody.Part.createFormData(partName,file.getName(), requestFile);
//    }

    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            mFragUserHead.setImageBitmap(photo);
        }
    }

    protected void startPhotoZoom(Uri uri) {
        Log.d("UUUU", "startPhotoZoom:" + uri);
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 1000);
    }

    /**
     * 获取公司名
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCompanyName(CompanyInfoModel companyInfoModel) {
        mFragUserSetcompanyname.setText(companyInfoModel.getCompanyName().trim());
        mFragUserCompanyname.setText(companyInfoModel.getCompanyName().trim());
        mFragUserPhonenumber.setText(companyInfoModel.getPhoneNumber().trim());
    }

}
