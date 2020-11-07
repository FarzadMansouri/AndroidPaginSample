package ir.frzd.paging_kotlin_mvvm.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import ir.frzd.paging_kotlin_mvvm.model.ResultModel
import ir.frzd.paging_kotlin_mvvm.repository.ItemDataSourceFactory

class ItemViewModel : ViewModel() {

     var itemPageList: LiveData<PagedList<ResultModel>>
    private var liveDataSource: LiveData<PageKeyedDataSource<Int, ResultModel>>

    init {
        var itemDataSourceFactory = ItemDataSourceFactory()

        liveDataSource = itemDataSourceFactory.itemLiveDataSoure

        var pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .build()
        itemPageList = LivePagedListBuilder(itemDataSourceFactory,pagedListConfig).build()

    }


}

