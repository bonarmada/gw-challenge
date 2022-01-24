package io.github.bonarmada.gw_challenge.features.home.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.github.bonarmada.gw_challenge.data.model.Job
import io.github.bonarmada.gw_challenge.data.repository.JobsRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class JobsPagingSource constructor(
    private val jobsRepository: JobsRepository,
) : RxPagingSource<Int, Job>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Job>> {
        val page = params.key ?: 1

        return jobsRepository
            .getJobs(
                page
            )
            .subscribeOn(Schedulers.io())
            .map {
                toLoadResult(it.list, it.nextPage)
            }
            .onErrorReturn {
                Timber.e(it)
                LoadResult.Error(it)
            }
    }

    private fun toLoadResult(data: List<Job>, nextKey: Int?): LoadResult<Int, Job> {
        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = nextKey
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Job>): Int? {
        return state.anchorPosition ?: 0
    }
}