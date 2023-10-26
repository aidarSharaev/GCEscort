package com.example.gcescort.remote.dto

import com.example.gcescort.domain.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {

  @GET("/api")
  suspend fun getUsers(
    @Query("results") results: Int,
    @Query("inc") inc: String = "email,name,dob",
    @Query("noinfo") noinfo: String = ""
  ): UserResponse

}
