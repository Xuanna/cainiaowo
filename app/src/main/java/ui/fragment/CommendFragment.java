package ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.custom.cainiaowo.R;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
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
    @InjectView(R.id.et_search)
    EditText etSearch;

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
//        rxjavaMap();
//        rxjavaFlamap();
//        operatorFilter();
//        textChange();
//        RxView.clicks(btn)//多次点击
//                .throttleFirst(1,TimeUnit.SECONDS)
//        .subscribe(new Observer<Object>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(@NonNull Object o) {
//                Log.e("onNext",  "点击："+System.currentTimeMillis());
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }
    int count=10;
    public void countTime(){//倒计时
        Observable.interval(0,1,TimeUnit.SECONDS)
                .take(count+1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return count-aLong;
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        btn.setEnabled(false);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        btn.setText(aLong+"秒");
                        Log.e("aLong:", aLong+ "");

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        btn.setEnabled(true);
                    }
                });
    }
    public void textChange(){//事件监听，防止请求无用数据
        RxTextView.textChanges(etSearch).debounce(200, TimeUnit.MILLISECONDS)
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(@NonNull CharSequence charSequence) throws Exception {
                        return charSequence.toString().trim().length()>0;
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .switchMap(new Function<CharSequence, ObservableSource<List<String>>>() {
                    @Override
                    public ObservableSource<List<String>> apply(@NonNull CharSequence charSequence) throws Exception {
                      final  List<String> list=new ArrayList<String>();
                        list.add("abc");
                        list.add("ads");
                        return Observable.just(list);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) throws Exception {
                for (String str:strings) {
                    Log.e("accept", str+ "");
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("throwable", throwable.toString()+ "");
            }
        });

    }
    public void operatorFilter() {//数据过滤
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                Integer[] num = {1, 2, 3, 4, 5};
                for (int nums : num) {
                    e.onNext(nums);
                }
            }
        }).filter(new Predicate<Integer>() {
            @Override
            public boolean test(@NonNull Integer integer) throws Exception {
                return integer < 4;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e("accept", integer + "");
            }
        });
    }

    public void rxjavaFlamap() {//类型转换
        Observable.just("joey").flatMap(new Function<String, ObservableSource<User>>() {
            @Override
            public ObservableSource<User> apply(@NonNull String s) throws Exception {
                final User user = new User(s, "123456");
                return new ObservableSource<User>() {
                    @Override
                    public void subscribe(@NonNull Observer<? super User> observer) {
                        user.age = 10;
                        observer.onNext(user);
                    }
                };
            }
        }).subscribe(new Observer<User>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e("onSubscribe", "onSubscribe");
            }

            @Override
            public void onNext(@NonNull User user) {
                btn.setText(user.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("onError", "onError");
            }

            @Override
            public void onComplete() {
                Log.e("onComplete", "onComplete");
            }
        });
    }

    public void rxjavaMap() {
        Observable.just(10).map(new Function<Integer, User>() {
            @Override
            public User apply(@NonNull Integer integer) throws Exception {
                return new User(integer, "梨花", "123456");
            }
        }).subscribe(new Consumer<User>() {
            @Override
            public void accept(User user) throws Exception {
                btn.setText(user.toString());
            }
        });
    }

    public void getData() {
        Observable.create(new ObservableOnSubscribe<UserInfo>() {//在主线程
            @Override
            public void subscribe(@NonNull final ObservableEmitter<UserInfo> e) throws Exception {
                //请求网络
                ApiSevice apiSevice = retrofit.create(ApiSevice.class);
                Call<UserInfo> call = apiSevice.login("piaa21", "123123a");
                call.enqueue(new Callback<UserInfo>() {
                    @Override
                    public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                        e.onNext(response.body());
                    }

                    @Override
                    public void onFailure(Call<UserInfo> call, Throwable t) {

                    }
                });
            }
        }).subscribeOn(Schedulers.io())//开启线程池在子线程中调用
                .observeOn(AndroidSchedulers.mainThread())//new NewThreadScheduler()
                .subscribe(new Observer<UserInfo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UserInfo user) {
                        Log.e("onNext", "user:" + user);
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

    public interface ApiSevice {
        @FormUrlEncoded
        @POST("user/merchantlogin")
        Call<UserInfo> login(@Field("username") String username, @Field("password") String pwd);
    }
    private static long lastClickTime;
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ( 0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
    @OnClick(R.id.btn)
    public void onClick() {
        countTime();
//        getData();
    }
}
