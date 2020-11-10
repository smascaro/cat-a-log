package com.smascaro.listdetail.list.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.smascaro.listdetail.R
import com.smascaro.listdetail.databinding.CatBreedItemBinding
import com.smascaro.listdetail.list.model.Breed

class BreedsAdapter : RecyclerView.Adapter<BreedsAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: CatBreedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Breed?) {
            binding.apply {
                breed = item
                executePendingBindings()
                root.setOnClickListener {
                    //TODO implement click
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = DataBindingUtil.inflate<CatBreedItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.cat_breed_item,
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    var items = listOf<Breed>()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        Log.d("Catalog", "Bound view holder at position $position: $holder")
    }

    override fun getItemCount(): Int {
        return items.size
    }
}