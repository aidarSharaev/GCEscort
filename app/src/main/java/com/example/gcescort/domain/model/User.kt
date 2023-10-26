package com.example.gcescort.domain.model

import android.os.Parcelable
import com.example.gcescort.domain.model.Dob
import com.example.gcescort.domain.model.Name
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
  val dob: Dob,
  val email: String,
  val name: Name,
): Parcelable