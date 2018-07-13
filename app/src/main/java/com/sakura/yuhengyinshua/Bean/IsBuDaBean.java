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
     * info : [{"is_sao":1,"orderid":"201805247489"}]
     * status : 1
     * address : 河南省 郑州市 金水区 经三路金城国际B座906室
     * tel : 18838260871
     * name : 七七
     */

    private String msg;
    private int status;
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
         * is_sao : 1
         * orderid : 201805247489
         */

        private int is_sao;
        private String orderid;

        public int getIs_sao() {
            return is_sao;
        }

        public void setIs_sao(int is_sao) {
            this.is_sao = is_sao;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }
    }
}
