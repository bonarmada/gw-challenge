package io.github.bonarmada.gw_challenge.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bonarmada.gw_challenge.base.BaseViewModel
import io.github.bonarmada.gw_challenge.data.model.CategoriesEnum
import io.github.bonarmada.gw_challenge.data.repository.JobsRepository
import io.github.bonarmada.gw_challenge.features.home.paging.JobsPagingSource
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val jobsRepository: JobsRepository
) : BaseViewModel() {

    private val _categories by lazy {
        MutableLiveData<List<CategoriesEnum>>()
    }

    val categories: LiveData<List<CategoriesEnum>> = _categories

    fun setCategories(categories: List<CategoriesEnum>){
        _categories.value = categories
    }

    val selectedCategories: MutableList<String> = mutableListOf()

    private val pager by lazy {
        Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                JobsPagingSource(
                    jobsRepository,
                    selectedCategories
                )
            }
        )
    }

    val jobResults =
        pager
            .liveData
            .cachedIn(viewModelScope)
}