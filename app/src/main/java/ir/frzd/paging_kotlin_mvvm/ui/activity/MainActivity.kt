package ir.frzd.paging_kotlin_mvvm.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ir.frzd.paging_kotlin_mvvm.R
import ir.frzd.paging_kotlin_mvvm.ui.adapter.ItemAdapter
import ir.frzd.paging_kotlin_mvvm.databinding.ActivityMainBinding
import ir.frzd.paging_kotlin_mvvm.view_model.ItemViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val itemAdapter= ItemAdapter()
    private lateinit var viewModel: ItemViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)

        Log.e("TAG", "onCreate: "+viewModel.itemPageList.hasActiveObservers() )
        viewModel.itemPageList.observe(this, {
            binding.prgLoad.visibility = View.VISIBLE
              Log.e("TAG", "onCreate: "+it.size )

              itemAdapter.submitList(it)
              binding.prgLoad.visibility = View.GONE

        })

        initializeRecycler()


    }


    fun initializeRecycler() {
        binding.recyclerView.adapter = itemAdapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }


}