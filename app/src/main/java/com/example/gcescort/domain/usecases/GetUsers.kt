package com.example.gcescort.domain.usecases

import com.example.gcescort.remote.repository.UserRepository
import com.example.gcescort.domain.model.UserResponse
import kotlinx.coroutines.flow.Flow

class GetUsers(
  private val userRepository: UserRepository
) {

  operator fun invoke(results: Int): Flow<UserResponse> {
    return userRepository.getUsers(results)
  }

}