package com.example.project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val selectedItem = MutableLiveData<ListFragment.ItemModel>()
    var logedin = false
    var username = ""
    var total_cost = 0.0
    var i = 0
}