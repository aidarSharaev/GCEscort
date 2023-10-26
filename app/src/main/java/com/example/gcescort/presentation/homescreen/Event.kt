package com.example.gcescort.presentation.homescreen

import com.example.gcescort.domain.model.User


sealed class Event {

  data class OnAddClick(val user: User) : Event()

  data class OnDeleteClick(val index: Int? = null) : Event()

  object OnPagingData : Event()

}
