package com.sakura.yuhengyinshua.Bean;

import java.util.List;

/**
 * com.sakura.yuhengyinshua.Bean
 *
 * @author 赵磊
 * @date 2018/7/11
 * 功能描述：
 */
public class IsBuDaBean {

    /**
     * msg : 请求成功
     * info : [{"orderid":"201807301026_1","is_sao":"1"},{"orderid":"201807301026_2","is_sao":"1"},{"orderid":"201807301026_3","is_sao":"0"},{"orderid":"201807301026_4","is_sao":"0"},{"orderid":"201807301026_5","is_sao":"0"},{"orderid":"201807301026_6","is_sao":"1"},{"orderid":"201807301026_7","is_sao":"0"}]
     * status : 1
     * hj_bianhao : 1
     * address : 浙江省 杭州市 桐庐县 城南街道瑶琳路396号 311500
     * tel : 18838260871
     * name : 测试
     */

    private String msg;
    private int status;
    private String hj_bianhao;
    private String address;
    private String tel;
    private String name;
    private List<InfoBean> info;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHj_bianhao() {
        return hj_bianhao;
    }

    public void setHj_bianhao(String hj_bianhao) {
        this.hj_bianhao = hj_bianhao;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * orderid : 201807301026_1
         * is_sao : 1
         */

        private String orderid;
        private String is_sao;

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getIs_sao() {
            return is_sao;
        }

        public void setIs_sao(String is_sao) {
            this.is_sao = is_sao;
        }
    }
}
