package utils;

import com.custom.cainiaowo.BaseCallback;
import com.custom.cainiaowo.Myapplication;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
public class MyOkhttpHelper {
    private OkHttpClient okHttpClient  = Myapplication.okHttpClient;
    private Gson gson;

    public MyOkhttpHelper() {
        gson=new Gson();
    }
    public static MyOkhttpHelper getInstance(){
        MyOkhttpHelper myOkhttpHelper=null;
        if(myOkhttpHelper==null){
            myOkhttpHelper=new MyOkhttpHelper();
        }
        return myOkhttpHelper;
    }
    public void get(String url,BaseCallback baseCallback){
        Request request=buildRequest(url,null,OkhttpMethodType.GET);
        doRequest(request,baseCallback);
    }

    public void post(String url, Map<String,String> params,BaseCallback baseCallback){
        Request request=buildRequest(url,params,OkhttpMethodType.POST);
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
            if (response.isSuccessful()){
                String string=response.body().string();
                if(callback.classOfT==String.class){
                    callback.onResponseSuccess(response,string);
                }else{
                       try {
                           Object object= gson.fromJson(response.body().string(),callback.classOfT);
                           callback.onResponseSuccess(response,object);
                          }catch (JsonParseException ex){
                            ex.printStackTrace();
                           callback.onResponseError(response,response.code(),ex);
                         }

                }

            }else{
                callback.onResponseError(response,response.code(),null);
            }
            }
        });
    }
    private  Request buildRequest(String url,Map<String,String> params,OkhttpMethodType type){
        Request.Builder builder=new Request.Builder();
        builder.url(url);
        if (type==OkhttpMethodType.GET){
            builder.get();
        }else if(type==OkhttpMethodType.POST){
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

     enum OkhttpMethodType {

        GET, POST

    }
}
