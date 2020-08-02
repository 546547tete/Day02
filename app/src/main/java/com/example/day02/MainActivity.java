package com.example.day02;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import android.os.Bundle;
import android.util.Log;

import com.example.day02.app.Demo;
import com.example.day02.app.PostJson;
import com.example.day02.app.Response;
import com.example.mylibrary.HttpManager;
import com.example.mylibrary.server.ApiService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainActivity extends RxAppCompatActivity {

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiService apiService = HttpManager.getInstance()
                .getRetrofit("http://api.t.ergedd.com/", 3, TimeUnit.SECONDS)
                .create(ApiService.class);
  //      Observable<JsonElement> post = apiService.post("getUpgrade", new HashMap<String, Object>(), new HashMap<String, Object>());
        RequestBody body = RequestBody.create(MediaType.parse(""), new File(""));
        HashMap<String, Object> map = new HashMap<>();
        map.put("Content-Type","application/x-www-form-urlencoded");
        Observable<JsonElement> post = apiService.postTest("getUpgrade");
        post.delay(3,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends JsonElement>>() {
                    @Override
                    public ObservableSource<? extends JsonElement> apply(Throwable throwable) throws Exception {
                        return Observable.error(new Exception("网络错误"));
                    }
                })
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e("TRG", "取消订阅 !!!" );
                    }
                })
                .map(new Function<JsonElement, PostJson>() {
                    @Override
                    public PostJson apply(JsonElement jsonElement) throws Exception {
                        PostJson postJson = new Gson().fromJson(jsonElement, PostJson.class);
                        return postJson;
                    }
                })
                .subscribe(new Observer<PostJson>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable=d;
                    }

                    @Override
                    public void onNext(PostJson postJson) {
                        Log.e("TRG",postJson.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TRG",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable!=null&&!disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
