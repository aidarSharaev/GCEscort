package com.example.gcescort

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.gcescort.presentation.homescreen.HomeState
import com.example.gcescort.presentation.homescreen.HomeViewModel
import com.example.gcescort.presentation.navgraph.NavGraph
import com.example.gcescort.presentation.navgraph.Route
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  private val viewModel: HomeViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.state.collect { state ->
          setContent {
            val navController = rememberNavController()
            NavGraph(
              users = viewModel.usersState,
              onEvent = viewModel::onEvent,
              navController = navController
            )
            when(state) {
              is HomeState.Success -> {
                navController.navigate(route = Route.HomeScreen.route)
              }
              is HomeState.Failure -> {
                navController.navigate(route = Route.EmptyScreen.route)
              }
              is HomeState.Loading -> {
                navController.navigate(route = Route.LoadingScreen.route)
              }
              else -> {}
            }
          }
        }
      }
    }
  }
}







