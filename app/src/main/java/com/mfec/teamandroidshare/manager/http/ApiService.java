package com.mfec.teamandroidshare.manager.http;

import com.mfec.teamandroidshare.dao.CategoryDao;
import com.mfec.teamandroidshare.dao.LoginDao;
import com.mfec.teamandroidshare.dao.PeopleDao;
import com.mfec.teamandroidshare.dao.RankDao;
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

    @POST("topic/getListTopicByCategory/{id}")
    Call<List<TitleDao>> LoadTopicList(@Body CategoryDao categoryDao,@Path("id") String id);

    @POST("topic/getListTopicByLike/{id}")
    Call<List<TitleDao>> LoadTopicLikeList(@Body CategoryDao categoryDao,@Path("id") String id);

    @POST("topic/createTopic")
    Call<Boolean> AddTopic(@Body WrapperDao wrapper);

    @POST("topic/viewTopic")
    Call<TitleDao> AddViewTopic(@Body TitleDao titleDao);

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

    @POST("topic/likeTopic/{id}")
    Call<Boolean> likeAndUnlike(@Body TitleDao titleDao,@Path("id") String id);

    @GET("rank/rankTopic")
    Call<List<RankDao>> LoadRankList();

}
