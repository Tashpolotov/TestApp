package com.example.testapp.presenter.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.databinding.ItemCategoryBinding
import com.example.testapp.databinding.ItemFoodBinding
import com.example.testapp.domain.model.Category
import com.example.testapp.domain.model.CategoryList
import okhttp3.internal.wait

class FoodAdapter(val onClick:(Category) -> Unit) : ListAdapter<Category, FoodAdapter.ViewHolder>(Diff()) {

    private var selected: Int = 0

    inner class ViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Category) {
            binding.tvCategory.text = model.strCategory
            Log.d("FoodAdapter", "CategoryList: ${model.strCategory}")

            val isSelectedPosition = adapterPosition == selected
            val texColor = if(isSelectedPosition) {
                ContextCompat.getColor(binding.root.context, R.color.selected_icon_color)
            } else {
                ContextCompat.getColor(binding.root.context, R.color.black)
            }
            binding.tvCategory.setTextColor(texColor)

            itemView.setOnClickListener {
                selected = adapterPosition
                notifyDataSetChanged()
                onClick(model)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class Diff : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.idCategory == newItem.idCategory
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}
