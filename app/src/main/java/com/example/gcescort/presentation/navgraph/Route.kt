package com.example.gcescort.presentation.navgraph


sealed class Route(val route: String) {
  object HomeScreen : Route(route = "homeScreen")
  object DetailScreen : Route(route = "detailScreen")
  object SearchScreen : Route(route = "searchScreen")
  object NewUserScreen : Route(route = "newUserScreen")
  object LoadingScreen : Route(route = "loadingScreen")
  object EmptyScreen : Route(route = "emptyScreen")
}
