package ir.frzd.paging_kotlin_mvvm.utils

import android.util.Log
import androidx.paging.PageKeyedDataSource
import ir.frzd.paging_kotlin_mvvm.model.MovieModel
import ir.frzd.paging_kotlin_mvvm.model.ResultModel
import ir.frzd.paging_kotlin_mvvm.network.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemDataSource : PageKeyedDataSource<Int, ResultModel>() {


    //the size of a page that we want
//    val PAGE_SIZE = 50

    //we will start from the first page which is 1
    private val FIRST_PAGE = 1

    //we need to fetch from stackoverflow
    private val API_KEY = "YOUR API_KEY"
    private val LANGUAGE = "en-US"


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ResultModel>
    ) {

        RetrofitFactory.makeServiceApi.getAnswers(API_KEY, LANGUAGE, false, false, FIRST_PAGE)
            .enqueue(object : Callback<MovieModel> {
                override fun onResponse(
                    call: Call<MovieModel>,
                    response: Response<MovieModel>
                ) {
                    Log.e("TAG", "onResponse Initial: " + response.raw().request().url())

                    response?.let {
                        Log.e("TAG", "onResponse Initial size: " + response.body()!!.results.size)
                        callback.onResult(it.body()!!.results, null, FIRST_PAGE + 1)
                    }
                }

                override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                    getError("Initial : " + t.message)
                }

            })


    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ResultModel>) {

        RetrofitFactory.makeServiceApi.getAnswers(API_KEY, LANGUAGE, false, false, params.key)
            .enqueue(object : Callback<MovieModel> {
                override fun onResponse(
                    call: Call<MovieModel>,
                    response: Response<MovieModel>
                ) {
                    Log.e("TAG", "onResponse Before: " + response.raw().request().url())

                    response?.let {
                        Log.e("TAG", "onResponse Before size: " + response.body()!!.results.size)

                        val adjacentKey = if (params.key > 1) params.key - 1 else null
                        callback.onResult(it.body()!!.results, adjacentKey)
                    }
                }

                override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                    getError("Before : " + t.message)
                }

            })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ResultModel>) {

        RetrofitFactory.makeServiceApi.getAnswers(API_KEY, LANGUAGE, false, false, params.key)
            .enqueue(object : Callback<MovieModel> {
                override fun onResponse(
                    call: Call<MovieModel>,
                    response: Response<MovieModel>
                ) {

                    Log.e("TAG", "onResponse After: " + response.raw().request().url())

                    response?.let {
                        Log.e("TAG", "onResponse After size: " + response.body()!!.results.size)

                        val key =
                            if (it.body()!!.total_pages > it.body()!!.page) params.key + 1 else null

                        callback.onResult(it.body()!!.results, key)
                    }
                }

                override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                    getError("After : " + t.message)

                }

            })


    }

    fun getError(message: String) {
        Log.e(
            "TAG", "getError: " + message
        )
    }

}







