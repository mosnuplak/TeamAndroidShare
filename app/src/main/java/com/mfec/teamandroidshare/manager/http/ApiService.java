package com.mfec.teamandroidshare.manager.http;

import com.mfec.teamandroidshare.dao.PeopleDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by E5-473G on 8/4/2017.
 */

public interface ApiService {
    // call service on here
    @GET("topic/getalltopic")
    Call<List<PeopleDao>> LoadPerpeoList();



}
