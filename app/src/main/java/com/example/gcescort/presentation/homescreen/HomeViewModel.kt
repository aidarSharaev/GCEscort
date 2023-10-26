package com.example.gcescort.presentation.homescreen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gcescort.domain.model.User
import com.example.gcescort.domain.usecases.UsersUseCases
import com.example.gcescort.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val usersUseCases: UsersUseCases
) : ViewModel() {

  fun onEvent(event: Event) {
    when(event) {

      is Event.OnAddClick -> {
        usersState.add(Constants.FIRST_INDEX, event.user)
      }

      is Event.OnDeleteClick -> {
        event.index?.let { usersState.removeAt(it) }
      }

      is Event.OnPagingData -> {
        getOrders()
      }

    }

  }

  private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Empty)
  val state: StateFlow<HomeState> = _state

  val usersState = mutableStateListOf<User>()

  init {
    viewModelScope.launch {
      getOrders()
    }
  }

  private fun getOrders() {
    viewModelScope.launch {
      _state.value = HomeState.Loading
      usersUseCases.getUsers(Constants.USERS_SIZE)
        .catch { e ->
          e.printStackTrace()
          _state.value = HomeState.Failure(e)
        }.collect {
          usersState.addAll(it.results)
          _state.value = HomeState.Success(it)
        }
    }
  }
}