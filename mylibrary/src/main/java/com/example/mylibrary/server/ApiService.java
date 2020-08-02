package com.example.mylibrary.server;

import com.google.gson.JsonElement;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiService {
    @GET
    Observable<JsonElement> get(@Url String url, @QueryMap Map<String,Object> parmas, @HeaderMap Map<String,Object> headers);

    //上传表单
    @POST
    @FormUrlEncoded
    Observable<JsonElement> post(@Url String url, @FieldMap Map<String,Object> params, @HeaderMap Map<String,Object> headers);

    //上传json
    @POST
    Observable<JsonElement> postjson(@Url String url, RequestBody requestBody, @HeaderMap Map<String,Object> headers);

    @DELETE
    Observable<JsonElement> delete(@Url String url, @QueryMap Map<String,Object> parmas, @HeaderMap Map<String,Object> headers);

    @PUT
    @FormUrlEncoded
    Observable<JsonElement> put(@Url String url, @FieldMap Map<String,Object> params, @HeaderMap Map<String,Object> headers);

    //上传文件
    @Multipart
    @POST
    Observable<JsonElement> upload(@Url String url, @PartMap Map<String,Object> parmas, List<MultipartBody.Part>filelist);

    //下载文件
    @Streaming
    Observable<ResponseBody> download(@Url String url, @QueryMap Map<String,Object> parmas, @HeaderMap Map<String,Object> headers);

    @POST
    Observable<JsonElement> postTest(@Url String string);
}
