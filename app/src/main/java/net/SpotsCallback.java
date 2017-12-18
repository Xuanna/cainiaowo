package net;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xuchichi on 2017/12/15.
 */
public abstract class SpotsCallback<T> extends BaseCallback<T> {
    Context context;
    SpotsDialog spotsDialog;
    public SpotsCallback(Context context) {
        this.context = context;
        spotsDialog=new SpotsDialog(context);
    }
    public void showDialog(){
        spotsDialog.show();
    }
    public void dismissDialog(){
        if (spotsDialog!=null){
            spotsDialog.dismiss();
        }

    }

    @Override
    public void onRequestBefore(Request request) {
       showDialog();
        Log.e("onRequestBefore","onRequestBefore");
    }

    @Override
    public void onFailure(IOException e) {
        dismissDialog();
    }

    @Override
    public void onResponse(Response response) {
        dismissDialog();
    }
}
