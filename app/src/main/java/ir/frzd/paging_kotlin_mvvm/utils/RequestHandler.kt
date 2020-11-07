package ir.frzd.paging_kotlin_mvvm.utils

import retrofit2.Response

class RequestHandler {
    fun <T> safeApi(callApi: () -> Response<T>): Result<T> {
        var api = callApi.invoke()
        return try {
            if(api.isSuccessful)
            Result.Success(api.body()!!)
            else
                Result.Error(api.errorBody().toString())
        } catch (e: Exception) {
            Result.Error(e.toString())
        }
    }


}