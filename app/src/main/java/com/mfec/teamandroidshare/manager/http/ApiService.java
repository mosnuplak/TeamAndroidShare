package com.mfec.teamandroidshare.manager.http;

import com.mfec.teamandroidshare.dao.PeopleDao;
import com.mfec.teamandroidshare.dao.TitleDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by E5-473G on 8/4/2017.
 */

public interface ApiService {
    // call service on here
    @GET("topic/getalltopic")
    Call<List<PeopleDao>> LoadPerpeoList();

    @GET("topic/getalltopic")
    Call<List<TitleDao>> LoadTopicList();




}
