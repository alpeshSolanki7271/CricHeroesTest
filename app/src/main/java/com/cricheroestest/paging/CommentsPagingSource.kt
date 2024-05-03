package com.cricheroestest.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cricheroestest.data.network.ApiInterface
import com.cricheroestest.data.network.response.CommentsList
import com.cricheroestest.util.MyLiveData
import java.lang.Exception

class CommentsPagingSource(
    private val apiInterface: ApiInterface
) : PagingSource<Int, CommentsList>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommentsList> {
        return try {
            val position = params.key ?: 1
            val response = apiInterface.getComments(position)
            val commentsResponse = response.body()
            if (commentsResponse != null) {
                commentsResponse.page?.let { MyLiveData.setMyData(data = it) }
            }
            LoadResult.Page(
                data = commentsResponse?.data!!,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == extractPageNumberFromUrl(commentsResponse.page?.next.toString())) null else position + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CommentsList>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    private fun extractPageNumberFromUrl(url: String): Int {
        val uri = Uri.parse(url)
        return uri.getQueryParameter("pageno")?.toIntOrNull() ?: 1
    }
}




