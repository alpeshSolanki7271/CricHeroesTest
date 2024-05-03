package com.cricheroestest.data.network

import com.cricheroestest.data.network.response.CommentsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("get-feed-comment-reply")
    suspend fun getComments(@Query("pageno") pageNo: Int): Response<CommentsResponse>
}