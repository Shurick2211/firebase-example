package com.nimko.hilt_firebase_example.fragments


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nimko.hilt_firebase_example.databinding.FragmentItemBinding
import com.nimko.hilt_firebase_example.model.ServiceProvider

class ListProvidersRecyclerViewAdapter<T>(val onClickItem: OnClickItem<T>
) : RecyclerView.Adapter<ListProvidersRecyclerViewAdapter<T>.ViewHolder>() {

    private var values: MutableList<T> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickItem = onClickItem
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val binding: FragmentItemBinding, val onClickItem: OnClickItem<T>)
        : RecyclerView.ViewHolder(binding.root) {

       fun bind(item:T){
           Log.d(TAG, item.toString())
           if(item is ServiceProvider) {
              bindServiceProvider(item)
           }
           binding.card.setOnClickListener {
               onClickItem.onClick(item)
           }
       }

        private fun bindServiceProvider(item: ServiceProvider){
            binding.apply {
                itemName.text = item.name
                content.text = item.title
                delButton.setOnClickListener {
                    onClickItem.delete(item)
                }
            }
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(item:T){
        values.add(item)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCollection(list:Iterable<T>){
        values = list.toMutableList()
        notifyDataSetChanged()
    }



    companion object{
        const val TAG = "RV_Adapter"
    }
}