package com.saher.dynamiccontentexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

val namesList: ArrayList<String> = arrayListOf(
    "John",
    "Andrew",
    "Dana",
    "Michael",
    "Georgia"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

/**
 * Using Dynamic functions [for] to insert data.
 */
@Composable
fun MainScreen(viewModel: MainViewModel= MainViewModel()) {
    /**
     * Defining a state for the composable, and lifting it into the [MainScreen]
     * to make it a single source of truth.
     * Therefore, [MainScreen] [Composable] is the main entry point of state [greetingListState]
     * and state update
     */
//    val greetingListState = remember {
//        mutableStateListOf<String>("John", "Amanda")
//    } it was disabled for the view model.

    //Creating a text field with a TextField
    val newNameStateContent =
        // remember {
        //mutableStateOf("")
        // }
        viewModel.textFieldState.observeAsState("") //taking State from the view model

    Column(
        modifier =
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingMessage(
//            greetingListState,
//            { greetingListState.add(newNameStateContent.value) }, //updating the Greeting list from TextField Value
            newNameStateContent.value,
        {newName -> viewModel.onTextChange(newName) }) //updating the text field value inside view model.
    }
}

@Composable
fun GreetingMessage(//namesList: List<String>,
                 //buttonClick: () -> Unit,
                 textFieldValue: String,
                 textFieldUpdate: (newName: String) -> Unit) {

//    for (name in namesList) {
//        Greeting(name = name)
//    }


    TextField(value = textFieldValue, onValueChange = textFieldUpdate)

    Button(onClick ={ /*buttonClick*/}) {
        Text(
            text = textFieldValue
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        style = MaterialTheme.typography.h4
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}

