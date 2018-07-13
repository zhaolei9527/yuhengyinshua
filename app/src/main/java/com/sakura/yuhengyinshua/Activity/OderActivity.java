package com.sakura.yuhengyinshua.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.sakura.yuhengyinshua.Adapter.OrederListAdapter;
import com.sakura.yuhengyinshua.Base.BaseActivity;
import com.sakura.yuhengyinshua.Bean.CodeBean;
import com.sakura.yuhengyinshua.Bean.IsBuDaBean;
import com.sakura.yuhengyinshua.R;
import com.sakura.yuhengyinshua.Utils.EasyToast;
import com.sakura.yuhengyinshua.Utils.SpUtil;
import com.sakura.yuhengyinshua.Utils.UrlUtils;
import com.sakura.yuhengyinshua.Utils.Utils;
import com.sakura.yuhengyinshua.View.CommomDialog;
import com.sakura.yuhengyinshua.View.ProgressView;
import com.sakura.yuhengyinshua.View.SakuraLinearLayoutManager;
import com.sakura.yuhengyinshua.View.WenguoyiRecycleView;
import com.sakura.yuhengyinshua.Volley.VolleyInterface;
import com.sakura.yuhengyinshua.Volley.VolleyRequest;
import com.xuexiang.xqrcode.XQRCode;
import com.xuexiang.xqrcode.ui.CaptureActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.sakura.yuhengyinshua.Activity.MainActivity.REQUEST_CODE;

/**
 * com.sakura.yuhengyinshua.Activity
 *
 * @author 赵磊
 * @date 2018/7/11
 * 功能描述：
 */
public class OderActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.rv_shop_list)
    WenguoyiRecycleView rvShopList;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.img_dizhi)
    ImageView imgDizhi;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    @BindView(R.id.rl_change_dizhi)
    RelativeLayout rlChangeDizhi;
    @BindView(R.id.btn_qiangzhi)
    Button btnQiangzhi;
    @BindView(R.id.btn_queren)
    Button btnQueren;
    private OrederListAdapter adapter;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected int setthislayout() {
        return R.layout.oederlist_activity_layout;
    }

    @Override
    protected void initview() {
        SakuraLinearLayoutManager sakuraLinearLayoutManager = new SakuraLinearLayoutManager(this);
        sakuraLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvShopList.setLayoutManager(sakuraLinearLayoutManager);
        rvShopList.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        rvShopList.setFootLoadingView(progressView);
    }

    @Override
    protected void initListener() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnQueren.setOnClickListener(this);
        btnQiangzhi.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        try {
            dialog = Utils.showLoadingDialog(context);
            String isbuda = getIntent().getStringExtra("isbuda");
            IsBuDaBean isBuDaBean = new Gson().fromJson(isbuda, IsBuDaBean.class);
            tvDizhi.setText(isBuDaBean.getAddress());
            tvName.setText(isBuDaBean.getName());
            tvPhone.setText(isBuDaBean.getTel());
            adapter = new OrederListAdapter(OderActivity.this, isBuDaBean.getInfo());
            rvShopList.setAdapter(adapter);

            for (int i = 0; i < adapter.getDatas().size(); i++) {
                if (0 == adapter.getDatas().get(i).getIs_sao()) {
                    new CommomDialog(context, R.style.dialog, "继续扫描", new CommomDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, final boolean confirm) {
                            if (confirm) {
                                dialog.dismiss();
                            } else {
                                dialog.dismiss();
                                startScan();
                            }
                        }
                    }).setTitle("提示").show();
                    return;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_queren:
                for (int i = 0; i < adapter.getDatas().size(); i++) {
                    if (0 == adapter.getDatas().get(i).getIs_sao()) {
                        new CommomDialog(context, R.style.dialog, "请确认全部订单后进行打印！", new CommomDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, final boolean confirm) {
                                if (confirm) {
                                    dialog.dismiss();
                                } else {
                                    dialog.dismiss();
                                }
                            }
                        }).setTitle("提示").show();
                        return;
                    } else if (3 == adapter.getDatas().get(i).getIs_sao()) {
                        new CommomDialog(context, R.style.dialog, "请待上次打印完成！", new CommomDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, final boolean confirm) {
                                if (confirm) {
                                    dialog.dismiss();
                                } else {
                                    dialog.dismiss();
                                }
                            }
                        }).setTitle("提示").show();
                        return;
                    } else if (4 == adapter.getDatas().get(i).getIs_sao()) {
                        new CommomDialog(context, R.style.dialog, "确认进行补打！", new CommomDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, final boolean confirm) {
                                if (confirm) {
                                    dialog.dismiss();
                                } else {
                                    dialog.dismiss();
                                    order("1");
                                }
                            }
                        }).setTitle("提示").show();
                        return;
                    }
                }
                order("1");
                break;
            case R.id.btn_qiangzhi:
                new CommomDialog(context, R.style.dialog, "确定进行强制打印", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, final boolean confirm) {
                        if (confirm) {
                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                            order("2");
                        }
                    }
                }).setTitle("提示").show();
                break;
            default:
                break;

        }
    }

    /**
     * 订单获取
     */
    private void order(String type) {
        if (!Utils.isConnected(context)) {
            EasyToast.showShort(context, R.string.Networkexception);
            return;
        }
        dialog.show();
        HashMap<String, String> params = new HashMap<>(1);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("orderid", adapter.getDatas().get(0).getOrderid());
        params.put("type", type);
        Log.e("MainActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "api/order", "api/order", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                try {
                    Log.e("MainActivity", result);
                    dialog.dismiss();
                    CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
                    if (1 == codeBean.getStatus()) {
                        EasyToast.showShort(context, "打印成功");
                        finish();
                    } else {
                        EasyToast.showShort(context, codeBean.getMsg());
                    }
                } catch (Exception e) {
                    dialog.dismiss();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
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
                        adapter = new OrederListAdapter(OderActivity.this, isBuDaBean.getInfo());
                        rvShopList.setAdapter(adapter);
                    } else {
                        Toast.makeText(context, isBuDaBean.getMsg(), Toast.LENGTH_SHORT).show();
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
