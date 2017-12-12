package ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.custom.cainiaowo.R;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Utf8;
import retrofit2.http.POST;
import ui.BaseFragment;

/**
 * Created by xuchichi on 2017/9/29.
 */
public class SortFragment extends BaseFragment {
    @InjectView(R.id.et)
    EditText et;

    @Override
    public int setLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    public void initView() {
        initHint();
    }
    public void initHint(){
        String hintStr="  请输入运单号";
        String imgStr="[smile]";
        SpannableString spannableString=new SpannableString(imgStr+hintStr);
        Drawable d = getResources().getDrawable(R.drawable.delete);
        d.setBounds(0, 0,40, 40);
        //创建ImageSpan
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        //用ImageSpan替换文本
        spannableString.setSpan(span,0,imgStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        et.setHint(spannableString);
        et.setHintTextColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            login();
        }
    }

    public void login(){
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody formBody=new FormBody.Builder().add("username","piaa12")
                .add("password","123123a").build();
        final Request request=new Request.Builder()
              .url("http://wallet.pigamegroup.com/user/merchantlogin")
                .post(formBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    Log.e("response",response.body().string());
                }else{
                    Log.e("error","error");
                }

            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
