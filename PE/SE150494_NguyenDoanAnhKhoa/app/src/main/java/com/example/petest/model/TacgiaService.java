package com.example.petest.model;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TacgiaService {
    String Tacgia = "Tacgia";
    @GET(Tacgia)
    Call<List<Tacgia>> getAllTacgias();

    @GET(Tacgia + "/{id}")
    Call<Tacgia> getTacgiaById(@Path("id") Object id);

    @POST(Tacgia)
    Call<Tacgia> createTacgia(@Body Tacgia tacgia);

    @PUT(Tacgia + "/{id}")
    Call<Tacgia> updateTacgia(@Path("id") Object id, @Body Tacgia tacgia);

    @DELETE(Tacgia + "/{id}")
    Call<Tacgia> deleteTacgia(@Path("id") Object id);
}
