package net;

import java.util.HashMap;
import java.util.Map;

import utils.MyOkHttpHelper;

/**
 * Created by xuchichi on 2017/12/18.
 */
public class Business {
   public static MyOkHttpHelper myOkHttpHelper=MyOkHttpHelper.getInstance();

    public static void login(String username,String password,BaseCallback baseCallback){
        Map<String,String> map=new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        myOkHttpHelper.post(Url.loginUrl,map,baseCallback);
    }
}
