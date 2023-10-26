package com.example.gcescort.presentation.otherscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gcescort.domain.model.User
import com.example.gcescort.presentation.homescreen.Event
import com.example.gcescort.presentation.homescreen.UserItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
  navController: NavHostController,
  users: SnapshotStateList<User>,
  onEvent: (Event) -> Unit
) {

  val userSearch = remember {
    mutableStateOf("")
  }

  Scaffold(
    topBar = {
      BackTopBar(text = "Insert", navController = navController)
    }
  ) {
    Box(
      modifier = Modifier
        .padding(it)
        .fillMaxSize()
    ) {
      Box(modifier = Modifier
        .fillMaxHeight(0.1f)
        .padding(top = 10.dp)) {
        OutlinedTextField(
          modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
            .height(60.dp),
          value = userSearch.value,
          onValueChange = { value->userSearch.value = value },
          placeholder = { Text(text = "enter firstname") },
          singleLine = true,
          textStyle = MaterialTheme.typography.titleMedium
        )
      }
      LazyColumn(
        modifier = Modifier
          .fillMaxSize()
          .padding(top = 70.dp)
      ) {
        if(userSearch.value.isNotEmpty()) {
          items(
            users.filter { user ->
              user.name.first.lowercase().contains(userSearch.value)
            }.sortedBy { user -> user.name.first }
          ) { user ->
            UserItems(
              user = user,
              index = users.indexOf(user),
              onEvent = onEvent,
              navController = navController
            )
          }
        } else {
          items(
            users.sortedBy { user -> user.name.first }
          ) { user ->
            UserItems(
              user = user,
              index = users.indexOf(user),
              onEvent = onEvent,
              navController = navController
            )
          }
        }
      }
    }
  }
}