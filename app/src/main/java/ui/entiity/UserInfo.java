package ui.entiity;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * @author: zhangxin
 * @time: 2016/7/15 14:40
 * @description: 用户信息
 */
public class UserInfo extends BaseEntity implements Serializable {


    public DataBean data;
    public boolean success;

    public static class DataBean implements Serializable {

        public AccountBean account;
        public String appLoginToken;
        public int ipArea;
        public int netType;// 就用这个字段 0 未开通  1:原有网路 2:派誉之星网络
        public UserBean user;

        public static class AccountBean implements Serializable {
            public String address;
            public long createAt;
            public int id;
            public double piBalance;
            public double piBalanceEntrustFreeze;
            public double piBalanceFreeze;
            public double piBalancePackageFreeze;
            public long updateAt;
            public double usdBalance;
            public double usdBalanceEntrustFreeze;
            public double usdBalanceFreeze;
            public double usdBalancePackageFreeze;
        }

        public static class UserBean implements Serializable {
            public int loginFlag;//0表示第一次登录
            public String address;
            public String email;
            public boolean iOS;
            public long id;
            public String loginPassword;
            public String mobile;
            public int openMobileCode;
            public int openTradePassword;
            public String tradePassword;
            public String username;
            public String phoheCode;
            public int isboss;
            public int openGoogleCode;
        }
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
