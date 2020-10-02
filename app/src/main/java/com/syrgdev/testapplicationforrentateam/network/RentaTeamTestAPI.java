package com.syrgdev.testapplicationforrentateam.network;


import io.reactivex.Single;
import retrofit2.http.GET;

public interface RentaTeamTestAPI {

    @GET("api/users")
    Single<ResponseSchema> getUsers();

}
