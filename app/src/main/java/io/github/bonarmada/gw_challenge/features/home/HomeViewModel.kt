package io.github.bonarmada.gw_challenge.features.home

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bonarmada.gw_challenge.base.BaseViewModel
import io.github.bonarmada.gw_challenge.data.repository.JobsRepository
import io.github.bonarmada.gw_challenge.features.home.paging.JobsPagingSource
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val jobsRepository: JobsRepository
) : BaseViewModel() {

    private val pager by lazy {
        Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                JobsPagingSource(
                    jobsRepository
                )
            }
        )
    }

    val jobResults =
        pager
            .liveData
            .cachedIn(viewModelScope)
}