package com.example.gcescort.presentation.otherscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.gcescort.domain.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardClickScreen(
  user: User,
  navController: NavHostController
) {
  Scaffold(
    topBar = {
      BackTopBar(text = "Details", navController = navController)
    }
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(it),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = "firstname: ${user.name.first}\n" +
          "lastname: ${user.name.last}\n" +
          "title: ${user.name.title}\n" +
          "age: ${user.dob.age}\n" +
          "dob: ${user.dob.date}\n" +
          "email: ${user.email}",
        style = MaterialTheme.typography.bodyMedium
      )
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackTopBar(text: String, navController: NavController) {
  TopAppBar(
    title = { Text(text = text) },
    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Cyan),
    navigationIcon = {
      IconButton(onClick = {if (navController.previousBackStackEntry != null) navController.navigateUp()}) {
        Icon(
          imageVector = Icons.Default.ArrowBack,
          contentDescription = null
        )
      }
    }
  )
}
