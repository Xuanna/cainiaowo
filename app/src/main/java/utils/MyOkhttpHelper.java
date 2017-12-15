package utils;

import android.os.Handler;
import android.os.Looper;

import com.custom.cainiaowo.BaseCallback;
import com.custom.cainiaowo.Myapplication;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by xuchichi on 2017/12/13.
 */
public class MyOkHttpHelper {
    private OkHttpClient okHttpClient  = Myapplication.okHttpClient;
    private Gson gson;
    Handler handler;

    public MyOkHttpHelper() {
        gson=new Gson();
        handler=new Handler(Looper.getMainLooper());
    }
    public static MyOkHttpHelper getInstance(){
        return new MyOkHttpHelper();
    }
    public void get(String url,BaseCallback baseCallback){
        Request request=buildRequest(url,null,OkHttpMethodType.GET);
        doRequest(request,baseCallback);
    }

    public void post(String url, Map<String,String> params,BaseCallback baseCallback){
        Request request=buildRequest(url,params,OkHttpMethodType.POST);
        doRequest(request,baseCallback);
    }
    public void doRequest( final Request request,final BaseCallback callback){
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onResponse(response);
            if (response.isSuccessful()){
                String string=response.body().string();
                if(callback.mType==String.class){
                    callback.onResponseSuccess(response,string);
                }else{
                       try {
                           Object object= gson.fromJson(string,callback.mType);
                           callBackSuccess(callback,response,object);
//                           callback.onResponseSuccess(response,object);
                          }catch (JsonParseException ex){
                            ex.printStackTrace();
                           callBackError(callback,response,ex);
//                           callback.onResponseError(response,response.code(),ex);
                         }

                }

            }else{
                callback.onResponseError(response,response.code(),null);
            }
            }
        });
    }
    private void callBackSuccess(final BaseCallback callback, final Response response, final Object object){
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onResponseSuccess(response,object);
            }
        });

    }
    private void callBackError(final BaseCallback callback, final Response response,final Exception e){
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onResponseError(response,response.code(),e);
            }
        });

    }
    private  Request buildRequest(String url,Map<String,String> params,OkHttpMethodType type){
        Request.Builder builder=new Request.Builder();
        builder.url(url);
        if (type==OkHttpMethodType.GET){
            builder.get();
        }else if(type==OkHttpMethodType.POST){
            RequestBody requestBody=  buildFormData(params);
            builder.post(requestBody);
        }
        return  builder.build();
    }

    public RequestBody buildFormData(Map<String,String> params){

        FormBody.Builder builder=new FormBody.Builder();
        if(params!=null){
            for (Map.Entry<String,String> a:params.entrySet()) {
                builder.add(a.getKey(),a.getValue());
            }
        }
        return builder.build();
    }

     enum OkHttpMethodType {

        GET, POST

    }
}
