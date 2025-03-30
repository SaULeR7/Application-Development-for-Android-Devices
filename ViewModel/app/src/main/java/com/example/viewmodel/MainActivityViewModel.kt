package com.example.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(startNumber:Int) : ViewModel() {
    private var number = MutableLiveData <Int>()

    init {
        number.value = startNumber
    }

    fun addNumber(){
        number.value = (number.value)?.plus(1   )
        //number++
    }

    fun getNumber(): MutableLiveData<Int>{
        return number

    }

}
