package com.example.gcescort.remote.dto

import com.example.gcescort.domain.model.UserResponse

class UserApiImpl(
  private val usersApi: UsersApi
) {

  suspend fun getUsers(results: Int): UserResponse {
    return usersApi.getUsers(results)
  }

}