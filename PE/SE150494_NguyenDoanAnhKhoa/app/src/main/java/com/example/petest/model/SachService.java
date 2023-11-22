package com.example.petest.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SachService {
    String Sach = "Sach";
    @GET(Sach)
    Call<List<Sach>> getAllSachs();

    @GET(Sach + "/{id}")
    Call<Sach> getSachById(@Path("id") Object id);

    @GET(Sach + "/{tacgiaId}")
    Call<List<Sach>> getSachByTacgiaId(@Path("tacgiaId") Object tacgiaId);

    @POST(Sach)
    Call<Sach> createSach(@Body Sach sach);

    @PUT(Sach + "/{id}")
    Call<Sach> updateSach(@Path("id") Object id, @Body Sach sach);

    @DELETE(Sach + "/{id}")
    Call<Sach> deleteSach(@Path("id") Object id);
}
