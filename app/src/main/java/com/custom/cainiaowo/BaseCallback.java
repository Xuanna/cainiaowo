package com.custom.cainiaowo;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by xuchichi on 2017/12/13.
 */
public abstract class BaseCallback<T>{
     public Class<T> classOfT;
     public  abstract  void onFailure( IOException e);
     public  abstract  void onResponseSuccess(Response response,T t);
     public  abstract  void  onResponseError(Response response,int code,Exception e);
}
