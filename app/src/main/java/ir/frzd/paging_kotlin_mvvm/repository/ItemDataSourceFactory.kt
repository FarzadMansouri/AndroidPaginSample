package ir.frzd.paging_kotlin_mvvm.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import ir.frzd.paging_kotlin_mvvm.model.ResultModel
import ir.frzd.paging_kotlin_mvvm.utils.ItemDataSource

class ItemDataSourceFactory : DataSource.Factory<Int, ResultModel>() {

    val itemLiveDataSoure = MutableLiveData<PageKeyedDataSource<Int, ResultModel>>()

    override fun create(): DataSource<Int, ResultModel> {
        val itemDataSource = ItemDataSource()

        itemLiveDataSoure.postValue(itemDataSource)
        itemDataSource?.let {
            Log.e("TAG", "create itemDataSource: ")
        }
        return itemDataSource
    }
}