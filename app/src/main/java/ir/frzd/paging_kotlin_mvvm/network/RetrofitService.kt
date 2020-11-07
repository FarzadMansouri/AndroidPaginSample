package ir.frzd.paging_kotlin_mvvm.network

import ir.frzd.paging_kotlin_mvvm.model.MovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("discover/movie")
    fun getAnswers(
        @Query("api_key") Api_Key: String, @Query("language") language: String,
        @Query("include_adult") include_adult: Boolean,
        @Query("include_video") include_video: Boolean,
        @Query("page") page: Int,
    ): Call<MovieModel>


}