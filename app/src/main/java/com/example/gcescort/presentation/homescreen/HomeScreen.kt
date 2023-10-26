package com.example.gcescort.presentation.homescreen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gcescort.domain.model.User
import com.example.gcescort.presentation.navgraph.Route
import com.example.gcescort.presentation.navgraph.navigateToDetails

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  users: SnapshotStateList<User>,
  onEvent: (Event) -> Unit,
  navController: NavController
) {

  val lazyListState = LazyListState()

  Scaffold(
    topBar = {
      TopBar(navController = navController)
    },
  ) { contentPadding ->

    LazyColumn(
      Modifier
        .fillMaxHeight()
        .padding(contentPadding),
      state = lazyListState
    ) {

      items(users.size) { index ->
        UserItems(
          user = users[index],
          index = index,
          onEvent = onEvent,
          navController = navController
        )
      }
    }
  }
}

@Composable
fun UserItems(
  user: User,
  index: Int,
  onEvent: (Event) -> Unit,
  navController: NavController
) {
  Card(
    elevation = CardDefaults.cardElevation(
      defaultElevation = 10.dp
    ),
    shape = RoundedCornerShape(20.dp),
    modifier = Modifier
      .padding(15.dp)
      .fillMaxWidth()
      .height(150.dp)
      .clickable {
        navigateToDetails(navController = navController, user = user)
      },
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.primaryContainer
    )
  ) {

    Row(
      modifier = Modifier.fillMaxSize(),
      horizontalArrangement = Arrangement.SpaceAround
    ) {

      Column(modifier = Modifier.fillMaxWidth(0.8f)) {
        Text(
          text = "name " + user.name.first + " " + user.name.last,
          modifier = Modifier.padding(top = 40.dp, start = 20.dp),
          style = MaterialTheme.typography.bodyLarge
        )
        Text(
          text = "email: ${user.email}",
          modifier = Modifier.padding(top = 10.dp, start = 20.dp),
          style = MaterialTheme.typography.bodyMedium
        )
      }
      IconButton(modifier = Modifier.padding(all = 10.dp),
        onClick = {
          onEvent(Event.OnDeleteClick(index))
        }, content = {
          Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null
          )
        }
      )
    }
  }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {
  TopAppBar(
    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Cyan),
    actions = {
      IconButton(onClick = { navController.navigate(Route.NewUserScreen.route) }) {
        Icon(
          imageVector = Icons.Default.Add,
          contentDescription = null
        )
      }
      IconButton(onClick = { navController.navigate(Route.SearchScreen.route) }) {
        Icon(
          imageVector = Icons.Default.Search,
          contentDescription = null
        )
      }
    },
    title = {
      Text(text = "GKEscort")
    }
  )
}
