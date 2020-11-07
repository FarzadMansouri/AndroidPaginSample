package ir.frzd.paging_kotlin_mvvm.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    private val BASE_URL = "https://api.themoviedb.org/3/"
     val LOAD_IMG = "https://image.tmdb.org/t/p/w200"

    private val client = OkHttpClient.Builder()
        .connectTimeout(2, TimeUnit.SECONDS)
        .writeTimeout(2, TimeUnit.SECONDS)
        .readTimeout(2, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    private val gson = GsonBuilder().setLenient().create()


    private  fun retroftiBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))

    }
    val makeServiceApi:RetrofitService by lazy {
        retroftiBuilder().build().create(RetrofitService::class.java)
    }
}