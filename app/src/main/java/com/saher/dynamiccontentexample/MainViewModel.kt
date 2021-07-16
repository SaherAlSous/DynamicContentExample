package com.saher.dynamiccontentexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    //Creating a LiveData object to update text view from text field
    val textFieldState = MutableLiveData("")

    //Updating the state from the text field.
    fun onTextChange(newText:String){
        textFieldState.value = newText
    }

}