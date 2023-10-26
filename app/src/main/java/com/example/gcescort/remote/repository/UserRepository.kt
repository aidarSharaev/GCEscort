package com.example.gcescort.remote.repository

import com.example.gcescort.domain.model.UserResponse
import com.example.gcescort.remote.dto.UserApiImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository(
  private val userApiImpl: UserApiImpl
) {

  fun getUsers(results: Int): Flow<UserResponse> = flow {
    emit(userApiImpl.getUsers(results))
  }

}