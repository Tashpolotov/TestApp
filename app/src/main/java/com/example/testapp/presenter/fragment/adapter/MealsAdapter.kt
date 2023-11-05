package com.example.testapp.presenter.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapp.databinding.ItemFoodBinding
import com.example.testapp.domain.model.Category

class MealsAdapter:ListAdapter<Category, MealsAdapter.ViewHolder>(MealsDiff()) {


    inner class ViewHolder(private val binding:ItemFoodBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Category) {
                binding.tvName.text = model.strCategory
                Glide.with(binding.root)
                    .load(model.strCategoryThumb)
                    .into(binding.img)
            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MealsDiff:DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.idCategory == newItem.idCategory
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

}
