package com.sakura.yuhengyinshua.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.sakura.yuhengyinshua.Base.BaseActivity;
import com.sakura.yuhengyinshua.Bean.IsBuDaBean;
import com.sakura.yuhengyinshua.R;
import com.sakura.yuhengyinshua.Utils.EasyToast;
import com.sakura.yuhengyinshua.Utils.SpUtil;
import com.sakura.yuhengyinshua.Utils.UrlUtils;
import com.sakura.yuhengyinshua.Utils.Utils;
import com.sakura.yuhengyinshua.Volley.VolleyInterface;
import com.sakura.yuhengyinshua.Volley.VolleyRequest;
import com.xuexiang.xqrcode.XQRCode;
import com.xuexiang.xqrcode.ui.CaptureActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    @BindView(R.id.fl_yonghu)
    FrameLayout flYonghu;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.fl_name)
    FrameLayout flName;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.fl_phone)
    FrameLayout flPhone;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.rl2)
    RelativeLayout rl2;
    @BindView(R.id.rl3)
    RelativeLayout rl3;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.rl4)
    RelativeLayout rl4;
    @BindView(R.id.btn_check)
    Button btnCheck;
    private Dialog dialog;

    @Override
    protected int setthislayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initview() {
        etAccount.setText(String.valueOf(SpUtil.get(context, "username", "")));
        etName.setText(String.valueOf(SpUtil.get(context, "name", "")));
        etPhone.setText(String.valueOf(SpUtil.get(context, "mobile", "")));
    }

    @Override
    protected void initListener() {
    }


    @Override
    protected void initData() {
        dialog = Utils.showLoadingDialog(context);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startScan();
            }
        });


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etNum.getText().toString().trim())) {
                    isBuda(etNum.getText().toString().trim());
                } else {
                    Toast.makeText(context, "请输入订单号", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * 开启二维码扫描
     */
    private void startScan() {
        Intent intent = new Intent(context, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //处理二维码扫描结果
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            //处理扫描结果（在界面上显示）
            handleScanResult(data);
        }
    }

    /**
     * 处理二维码扫描结果
     *
     * @param data
     */
    private void handleScanResult(Intent data) {
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                if (bundle.getInt(XQRCode.RESULT_TYPE) == XQRCode.RESULT_SUCCESS) {
                    String result = bundle.getString(XQRCode.RESULT_DATA);
                    dialog.show();
                    isBuda(result);
                } else if (bundle.getInt(XQRCode.RESULT_TYPE) == XQRCode.RESULT_FAILED) {
                    EasyToast.showShort(context, "解析结果：失败");
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    /**
     * 订单获取
     */
    private void isBuda(String result) {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("orderid", result);
        Log.e("MainActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "api/isbuda", "api/isbuda", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                try {
                    dialog.dismiss();
                    Log.e("MainActivity", result);
                    IsBuDaBean isBuDaBean = new Gson().fromJson(result, IsBuDaBean.class);
                    if (1 == isBuDaBean.getStatus()) {
                        startActivity(new Intent(context, OderActivity.class).putExtra("isbuda", result));
                    } else {
                        Toast.makeText(MainActivity.this, isBuDaBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
