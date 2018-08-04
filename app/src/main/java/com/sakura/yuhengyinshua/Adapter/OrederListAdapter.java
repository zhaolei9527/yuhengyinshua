package com.sakura.yuhengyinshua.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sakura.yuhengyinshua.Bean.IsBuDaBean;
import com.sakura.yuhengyinshua.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.wenguoyi.Adapter
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class OrederListAdapter extends RecyclerView.Adapter<OrederListAdapter.ViewHolder> {

    private Activity mContext;
    private ArrayList<IsBuDaBean.InfoBean> datas = new ArrayList();

    public ArrayList<IsBuDaBean.InfoBean> getDatas() {
        return datas;
    }

    public OrederListAdapter(Activity context, List<IsBuDaBean.InfoBean> msgBeanList) {
        this.mContext = context;
        this.datas.addAll(msgBeanList);
    }

    public void setDatas(List<IsBuDaBean.InfoBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_list_item_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (datas.get(position).getIs_sao().equals("0")) {
            holder.imgIscheck.setVisibility(View.GONE);
        } else {
            holder.imgIscheck.setVisibility(View.VISIBLE);
        }
        holder.tvOrderid.setText("订单号：" + datas.get(position).getOrderid());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        @BindView(R.id.tv_orderid)
        TextView tvOrderid;
        @BindView(R.id.img_ischeck)
        ImageView imgIscheck;

        public ViewHolder(View view) {
            super(view);
            this.rootView = view;
            ButterKnife.bind(this, view);
        }
    }
}
