package com.example.gcescort.di

import com.example.gcescort.domain.usecases.GetUsers
import com.example.gcescort.domain.usecases.UsersUseCases
import com.example.gcescort.remote.dto.UserApiImpl
import com.example.gcescort.utils.Constants.BASE_URL
import com.example.gcescort.remote.dto.UsersApi
import com.example.gcescort.remote.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

  @Provides
  @Singleton
  fun provideRetrofit(): UsersApi {
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(UsersApi::class.java)
  }

  @Provides
  @Singleton
  fun provideUserApiImpl(
    usersApi: UsersApi
  ): UserApiImpl {
    return UserApiImpl(usersApi)
  }

  @Provides
  @Singleton
  fun provideUserRepository(
    userApiImpl: UserApiImpl
  ): UserRepository {
    return UserRepository(userApiImpl)
  }

  @Provides
  @Singleton
  fun provideGetUsers(
    userRepository: UserRepository
  ): GetUsers {
    return GetUsers(userRepository)
  }

  @Provides
  @Singleton
  fun provideUsersUseCases(
    getUsers: GetUsers
  ): UsersUseCases {
    return UsersUseCases(getUsers)
  }

}