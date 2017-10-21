package ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.custom.cainiaowo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.IoScheduler;
import io.reactivex.internal.schedulers.NewThreadScheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ui.BaseFragment;
import ui.entiity.User;
import ui.entiity.UserInfo;

/**
 * Created by xuchichi on 2017/9/29.
 */
public class CommendFragment extends BaseFragment {
    @InjectView(R.id.btn)
    Button btn;
    Retrofit retrofit;
    @Override
    public int setLayout() {
        return R.layout.fragment_comment;
    }
    @Override
    public void initView() {
         retrofit = new Retrofit.Builder()
                .baseUrl("http://wallet.pigamegroup.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    //
        Observable.create(new ObservableOnSubscribe<UserInfo>() {//在主线程
            @Override
            public void subscribe(@NonNull ObservableEmitter<UserInfo> e) throws Exception {
            //请求网络
                UserInfo userinfo = retrofit.create(ApiSevice.class)
                        .login("piaa21","123123a").execute().body();
                e.onNext(userinfo);
            }
        }).subscribeOn(Schedulers.io())//开启线程池在子线程中调用
                .observeOn(AndroidSchedulers.mainThread())//new NewThreadScheduler()
                .subscribe(new Observer<UserInfo>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull UserInfo user) {
                Log.e("onNext","user:"+user);
                btn.setText(user.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void initRetroifit() {

//        Call<RequestBody> call = apiSevice.login("piaa12", "123123a");
//        call.enqueue(new Callback<RequestBody>() {
//            @Override
//            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
//                Toast.makeText(getContext(), "Success", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<RequestBody> call, Throwable t) {
//                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_LONG).show();
//            }
//        });

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

    public interface ApiSevice{
        @FormUrlEncoded
        @POST("user/merchantlogin")
        Call<UserInfo> login(@Field("username") String username,@Field("password") String pwd);
    }

    @OnClick(R.id.btn)
    public void onClick() {
        initRetroifit();
    }
}
