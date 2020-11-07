package ir.frzd.paging_kotlin_mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.frzd.paging_kotlin_mvvm.databinding.ItemUserRecyclerBinding
import ir.frzd.paging_kotlin_mvvm.model.ResultModel
import ir.frzd.paging_kotlin_mvvm.network.RetrofitFactory

class ItemAdapter : PagedListAdapter<ResultModel, ItemAdapter.ItemViewHolder>(DiffUtilCallBack()) {

    private lateinit var itemUserRecyclerBinding: ItemUserRecyclerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
        itemUserRecyclerBinding =
            ItemUserRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(itemUserRecyclerBinding)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<ResultModel>() {
        override fun areItemsTheSame(oldItem: ResultModel, newItem: ResultModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultModel, newItem: ResultModel): Boolean {
            return oldItem.equals(newItem)
        }
    }

    class ItemViewHolder(view: ItemUserRecyclerBinding) : RecyclerView.ViewHolder(view.root) {

        private var textName = view.textViewName
        private var imgProf = view.imageView


        fun onBind(item: ResultModel) {
            textName.text = item.title
            Glide.with(itemView.context)
                .load(RetrofitFactory.LOAD_IMG+item.poster_path)
                .into(imgProf)
        }
    }

}