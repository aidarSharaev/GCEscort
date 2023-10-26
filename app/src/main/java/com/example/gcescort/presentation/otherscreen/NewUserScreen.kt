package com.example.gcescort.presentation.otherscreen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gcescort.domain.model.Dob
import com.example.gcescort.domain.model.Name
import com.example.gcescort.domain.model.User
import com.example.gcescort.presentation.homescreen.Event


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewUserScreen(
  onEvent: (Event) -> Unit,
  navController: NavHostController
) {
  val focusManager = LocalFocusManager.current

  var email = remember {
    mutableStateOf("")
  }

  var title = remember {
    mutableStateOf("")
  }

  var first = remember {
    mutableStateOf("")
  }

  var last = remember {
    mutableStateOf("")
  }

  val valid by remember {
    derivedStateOf {
      email.value.isNotEmpty() && first.value.isNotEmpty() && title.value.isNotEmpty() && last.value.isNotEmpty()
    }
  }

  //random dob
  val a = (10..55).random()
  val dob = Dob(a, "${(0..30).random()}.${(0..12).random()}.${2023 - a}")

  Scaffold(
    topBar = {
      BackTopBar(text = "Insert", navController = navController)
    }
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(it), contentAlignment = Alignment.Center
    ) {

      Column() {
        OutlinedText(field = first, text = "firstname", focusManager = focusManager)
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedText(field = last, text = "lastname", focusManager = focusManager)
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedText(field = title, text = "title", focusManager = focusManager)
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedText(field = email, text = "email", focusManager = focusManager, isName = false)
        Spacer(modifier = Modifier.height(30.dp))
        val context = LocalContext.current
        Button(onClick = {
          if(valid) {
            onEvent(
              Event.OnAddClick(
                User(
                  dob = dob,
                  email = email.value,
                  name = Name(first.value, last.value, title.value)
                )
              )
            )
            Toast.makeText(context, "data inserted", Toast.LENGTH_SHORT).show()

          } else {
            Toast.makeText(context, "fill in all the data", Toast.LENGTH_SHORT).show()
          }
        }
        ) {
          Text(text = "insert")
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun OutlinedText(
  field: MutableState<String>,
  text: String,
  isName: Boolean = true,
  focusManager: FocusManager
) {
  val keyboardController = LocalSoftwareKeyboardController.current
  val keyboardType = if(isName) KeyboardType.Text else KeyboardType.Email
  val imeAction = if(isName) ImeAction.Next else ImeAction.Done
  val keyboardActions = if(isName) {
    KeyboardActions(
      onNext = {
        focusManager.moveFocus(FocusDirection.Down)
      }
    )
  } else {
    KeyboardActions(
      onDone = { keyboardController?.hide() }
    )
  }
  OutlinedTextField(
    value = field.value,
    onValueChange = { field.value = it },
    colors = TextFieldDefaults.outlinedTextFieldColors(
      placeholderColor = Color.LightGray
    ),
    placeholder = { Text(text = "enter your $text") },
    singleLine = true,
    keyboardOptions = KeyboardOptions(
      keyboardType = keyboardType,
      imeAction = imeAction
    ),
    keyboardActions = keyboardActions
  )
}



