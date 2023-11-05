package com.example.testapp.presenter.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapp.domain.model.Recommendation
import com.example.testapp.databinding.ItemVp2Binding

class VpAdapter :
    ListAdapter<Recommendation, VpAdapter.RecommendationViewHolder>(RecommendationDiffCallback()) {



    inner class RecommendationViewHolder(val binding: ItemVp2Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Recommendation) {
            Glide.with(binding.root)
                .load(model.imageResId)
                .into(binding.imgVp2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        return RecommendationViewHolder(ItemVp2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class RecommendationDiffCallback:DiffUtil.ItemCallback<Recommendation>() {
    override fun areItemsTheSame(oldItem: Recommendation, newItem: Recommendation): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Recommendation, newItem: Recommendation): Boolean {
        return oldItem == newItem
    }

}
