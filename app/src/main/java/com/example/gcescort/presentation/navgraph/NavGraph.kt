package com.example.gcescort.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gcescort.domain.model.User
import com.example.gcescort.presentation.homescreen.Event
import com.example.gcescort.presentation.homescreen.HomeScreen
import com.example.gcescort.presentation.otherscreen.CardClickScreen
import com.example.gcescort.presentation.otherscreen.EmptyHomeScreen
import com.example.gcescort.presentation.otherscreen.LoadingScreen
import com.example.gcescort.presentation.otherscreen.NewUserScreen
import com.example.gcescort.presentation.otherscreen.SearchScreen

@Composable
fun NavGraph(
  users: SnapshotStateList<User>,
  onEvent: (Event) -> Unit,
  navController: NavHostController
) {

  NavHost(
    navController = navController,
    startDestination = Route.HomeScreen.route
  ) {

    composable(route = Route.HomeScreen.route) {
      HomeScreen(users = users, onEvent = onEvent, navController = navController)
    }
    composable(route = Route.DetailScreen.route) {
      navController.previousBackStackEntry?.savedStateHandle?.get<User>("user")
        ?.let { user ->
          CardClickScreen(navController = navController, user = user)
        }
    }
    composable(route = Route.SearchScreen.route) {
      SearchScreen(navController = navController, users = users, onEvent = onEvent)
    }
    composable(route = Route.NewUserScreen.route) {
      NewUserScreen(onEvent = onEvent, navController = navController)
    }
    composable(route = Route.LoadingScreen.route) {
      LoadingScreen()
    }
    composable(route = Route.EmptyScreen.route) {
      EmptyHomeScreen()
    }
  }

}

fun navigateToDetails(navController: NavController, user: User) {
  navController.currentBackStackEntry?.savedStateHandle?.set("user", user)
  navController.navigate(
    route = Route.DetailScreen.route
  )
}