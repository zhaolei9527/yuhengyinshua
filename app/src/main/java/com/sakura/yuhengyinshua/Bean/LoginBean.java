package com.sakura.yuhengyinshua.Bean;

/**
 * com.sakura.yuhengyinshua.Bean
 *
 * @author 赵磊
 * @date 2018/7/11
 * 功能描述：
 */
public class LoginBean {


    /**
     * status : 1
     * msg : 登录成功
     * info : {"id":"3","username":"666666","mobile":"18888888888","rand":"7078","password":"7e4bff4a500381fe0f79c0ad3666f5ff","sex":"0","qid":null,"content":null,"email":"18838260871@qq.com","is_show":"1","addtime":"1525777537","name":"测试","type":"2"}
     */

    private int status;
    private String msg;
    private InfoBean info;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * id : 3
         * username : 666666
         * mobile : 18888888888
         * rand : 7078
         * password : 7e4bff4a500381fe0f79c0ad3666f5ff
         * sex : 0
         * qid : null
         * content : null
         * email : 18838260871@qq.com
         * is_show : 1
         * addtime : 1525777537
         * name : 测试
         * type : 2
         */

        private String id;
        private String username;
        private String mobile;
        private String rand;
        private String password;
        private String sex;
        private Object qid;
        private Object content;
        private String email;
        private String is_show;
        private String addtime;
        private String name;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRand() {
            return rand;
        }

        public void setRand(String rand) {
            this.rand = rand;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Object getQid() {
            return qid;
        }

        public void setQid(Object qid) {
            this.qid = qid;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIs_show() {
            return is_show;
        }

        public void setIs_show(String is_show) {
            this.is_show = is_show;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
