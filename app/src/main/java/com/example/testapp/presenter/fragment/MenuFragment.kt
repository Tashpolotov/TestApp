package com.example.testapp.presenter.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.testapp.R
import com.example.testapp.databinding.FragmentMenuBinding
import com.example.testapp.domain.model.Category
import com.example.testapp.presenter.App
import com.example.testapp.presenter.fragment.adapter.MealsAdapter
import com.example.testapp.presenter.fragment.adapter.VpAdapter
import com.example.testapp.presenter.viewmodel.FoodViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private val viewModel by viewModels<FoodViewModel>()
    private val vpAdapter = VpAdapter()
    private val foodAdapter = FoodAdapter(this::onClick)
    private val mealsAdapter = MealsAdapter()
    private var selectedCategory: String = "Beef"

    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = binding.vp2
        initAdapter()
    }

    private fun initAdapter() {
        binding.categoriesRv.adapter = foodAdapter
        binding.vp2.adapter = vpAdapter
        binding.mealsRv.adapter = mealsAdapter

        Log.d("MenuFragment", "Initializing adapters")

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.food.collect {
                // Обновляем данные в адаптерах RecyclerView
                vpAdapter.submitList(it.recommendation)
                foodAdapter.submitList(it.food)
                val selected = it.meals.filter { selectedCategory == it.strCategory }
                mealsAdapter.submitList(selected)
                it.food.forEach { category ->
                    viewModel.insertCategory(category)
                }

                Log.d("MenuFragment", "Received data from ViewModel: $it")
                if (isNetworkAvailable()) {
                    viewModel.getRecommendation()
                    viewModel.getFood()
                    viewModel.getMeals()
                } else {
                    Log.d("MenuFragment", "No internet connection, fetching data from the database")
                    val list = viewModel.getDataFromDatabase()
                    mealsAdapter.submitList(list)

                }
            }
        }
    }

    private fun onClick(model: Category) {
        binding.mealsRv.adapter = mealsAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.food.collect {
                val name = it.meals.filter { it.strCategory == model.strCategory }
                mealsAdapter.submitList(name)
                Log.d("aza", "food = ${it.meals}")
                it.food.forEach { category ->
                    viewModel.insertCategory(category)
                }

                // Уведомляем адаптер о необходимости обновления данных
                mealsAdapter.notifyDataSetChanged()
            }
        }
        viewModel.getMeals()
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}