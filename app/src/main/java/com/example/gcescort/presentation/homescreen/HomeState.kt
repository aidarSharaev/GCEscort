package com.example.gcescort.presentation.homescreen

import com.example.gcescort.domain.model.UserResponse


sealed class HomeState() {
  object Loading : HomeState()

  class Failure(val msg: Throwable) : HomeState()

  class Success(val users: UserResponse) : HomeState()

  object Empty : HomeState()
}