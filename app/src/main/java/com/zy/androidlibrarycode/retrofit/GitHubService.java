package com.zy.androidlibrarycode.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.2.0
 * 描    述：
 * ================================================
 */
public interface GitHubService {
    @GET("users/{user}/repos")
    Call<ResponseBody> listRepos(@Path("user") String user);
}
