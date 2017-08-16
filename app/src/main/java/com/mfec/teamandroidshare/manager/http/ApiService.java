package com.mfec.teamandroidshare.manager.http;

import com.mfec.teamandroidshare.dao.CategoryDao;
import com.mfec.teamandroidshare.dao.LoginDao;
import com.mfec.teamandroidshare.dao.PeopleDao;
import com.mfec.teamandroidshare.dao.TitleDao;
import com.mfec.teamandroidshare.dao.WrapperDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by E5-473G on 8/4/2017.
 */

public interface ApiService {
    // call service on here
    @GET("topic/getalltopic")
    Call<List<PeopleDao>> LoadPerpeoList();

//    @GET("topic/getListTopicByCategory/{category}")
//    Call<List<TitleDao>> LoadTopicList(@Path("category") String cateName);

    @POST("topic/getListTopicByCategory")
    Call<List<TitleDao>> LoadTopicList(@Body CategoryDao categoryDao);

    @POST("topic/createTopic")
    Call<WrapperDao> AddTopic(@Body WrapperDao wrapper);

    @GET("category/getAllCategory")
    Call<List<CategoryDao>> LoadCategory();

    //    @GET("topic/getalltopic")
//    Call<List<TitleDao>> LoadTopicList();
    @POST("login/userlogin")
    Call<LoginDao>CheckLogin(@Body LoginDao loginDao);

    @POST("register/registerUser")
    Call<Boolean>CheckRegister(@Body LoginDao loginDao);

    @GET("profile/getUserProfile/{id}")
    Call<LoginDao> loadProfile(@Path("id") String id);


}
