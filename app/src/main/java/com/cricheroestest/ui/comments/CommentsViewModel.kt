package com.cricheroestest.ui.comments


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.cricheroestest.repositories.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {
    val commentsList = appRepository.getComments().cachedIn(viewModelScope)
}