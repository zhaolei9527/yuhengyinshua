package com.sakura.yuhengyinshua.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.sakura.yuhengyinshua.Base.BaseActivity;
import com.sakura.yuhengyinshua.Bean.LoginBean;
import com.sakura.yuhengyinshua.R;
import com.sakura.yuhengyinshua.Utils.EasyToast;
import com.sakura.yuhengyinshua.Utils.SpUtil;
import com.sakura.yuhengyinshua.Utils.UrlUtils;
import com.sakura.yuhengyinshua.Utils.Utils;
import com.sakura.yuhengyinshua.Volley.VolleyInterface;
import com.sakura.yuhengyinshua.Volley.VolleyRequest;

import java.util.HashMap;

/**
 * Created by 赵磊 on 2017/7/13.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private Dialog dialog;
    private EditText et_account;
    private EditText et_password;
    private Button btn_login;
    private int pswminlen = 6;
    private String account;
    private String password;

    @Override
    protected void ready() {
        super.ready();
        fullScreen(this);
    }

    @Override
    protected int setthislayout() {
        return R.layout.activcity_login;
    }

    @Override
    protected void initview() {
        initView();
    }

    @Override
    protected void initListener() {
        btn_login.setOnClickListener(this);
        dialog = Utils.showLoadingDialog(context);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent();
        intent.setAction("LoginActivityIsStart");
        sendBroadcast(intent);
    }


    private void gotoMain() {
        startActivity(new Intent(context, MainActivity.class));
        finish();
    }

    private String mesg;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                submit();
                break;
            default:
                break;
        }
    }


    private void initView() {
        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);

        String username = (String) SpUtil.get(context, "username", "");

        if (!TextUtils.isEmpty(username)) {
            et_account.setText(username);
        }

        String password = (String) SpUtil.get(context, "password", "");

        if (!TextUtils.isEmpty(password)) {
            et_password.setText(password);
        }

    }

    private void submit() {
        // validate
        account = et_account.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(this, "请输入帐号", Toast.LENGTH_SHORT).show();
            return;
        }

        password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < pswminlen) {
            Toast.makeText(this, "密码长度最小六位", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO validate success, do something
        dialog.show();
        getLogin(account, password);
    }

    /**
     * 登录获取
     */
    private void getLogin(final String tel, final String password) {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("username", tel);
        params.put("password", password);
        Log.e("LoginActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "api/login", "api/login", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                try {
                    dialog.dismiss();
                    String decode = result;
                    Log.e("LoginActivity", decode);
                    LoginBean loginBean = new Gson().fromJson(result, LoginBean.class);
                    if (1 == loginBean.getStatus()) {
                        EasyToast.showShort(context, "登录成功");
                        SpUtil.putAndApply(context, "username", tel);
                        SpUtil.putAndApply(context, "password", password);

                        SpUtil.putAndApply(context, "uid", loginBean.getInfo().getId());
                        SpUtil.putAndApply(context, "username", loginBean.getInfo().getUsername());
                        SpUtil.putAndApply(context, "mobile", loginBean.getInfo().getMobile());
                        SpUtil.putAndApply(context, "name", loginBean.getInfo().getName());

                        gotoMain();

                    } else {
                        Toast.makeText(LoginActivity.this, loginBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                dialog.dismiss();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        account = null;
        password = null;
        System.gc();
    }
}
