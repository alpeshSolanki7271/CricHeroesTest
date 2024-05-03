package com.cricheroestest.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.cricheroestest.data.network.ApiInterface
import com.cricheroestest.paging.CommentsPagingSource
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {
    fun getComments() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { CommentsPagingSource(apiInterface) }
    ).liveData
}