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
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ui.BaseFragment;
import ui.entiity.LoginInfo;
import ui.entiity.User;
import utils.UrlManager;

/**
 * Created by xuchichi on 2017/9/29.
 */
public class CommendFragment extends BaseFragment {
    @InjectView(R.id.btn)
    Button btn;

    @Override
    public int setLayout() {
        return R.layout.fragment_comment;
    }

    @Override
    public void initView() {

    }

    public void initRetroifit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlManager.BASIC_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiSevice apiSevice=retrofit.create(ApiSevice.class);
      Call<RequestBody> call=  apiSevice.login(new User("18679131590","1131120704a"));
        call.enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                Log.e("uesr",response.body().toString()+"");
                Toast.makeText(getContext(),"Success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {
                Toast.makeText(getContext(),t.toString(),Toast.LENGTH_LONG).show();
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

    public interface ApiSevice{
        @POST("login")
        Call<RequestBody> login(@Body User user);
    }

    @OnClick(R.id.btn)
    public void onClick() {
        initRetroifit();
    }
}
