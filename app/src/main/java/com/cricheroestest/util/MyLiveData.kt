package com.cricheroestest.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cricheroestest.data.network.response.PageX

class MyLiveData {
    companion object {
        private val _myData = MutableLiveData<PageX>()
        val myData: LiveData<PageX> = _myData

        fun setMyData(data: PageX) {
            _myData.value = data
        }
    }
}