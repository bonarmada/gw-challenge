package io.github.bonarmada.gw_challenge.ui.features.detail

import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bonarmada.gw_challenge.base.BaseViewModel
import io.github.bonarmada.gw_challenge.data.repository.JobsRepository
import io.github.bonarmada.gw_challenge.ui.features.home.JobUIRepresentation
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class JobDetailsViewModel @Inject constructor(
    private val jobsRepository: JobsRepository
) : BaseViewModel() {

    private val _state by lazy {
        PublishSubject.create<JobDetailsState>()
    }

    val state: Observable<JobDetailsState> = _state

    private var isFavorite = false

    fun setJobData(job: JobUIRepresentation) {
        _state.onNext(
            JobDetailsState.UpdateJobDetails(job)
        )

        setupFavouriteObserver(job.id)
        setupCompanyObserver(job.companyId)
        getCompany(job.companyId)
    }

    /**
     * @subscribe to  a Flowable that emits everytime the data source, in this case the room db is updated
     */
    private fun setupCompanyObserver(companyId: Int) {
        jobsRepository.getCompany(companyId)
            .map {
                CompanyUIRepresentation.fromCompany(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    _state.onNext(
                        JobDetailsState.UpdateCompanyDetails(it)
                    )
                },
                onError = {
                    Timber.e(it)
                }
            ).addTo(disposables)
    }

    /**
     * @subscribe to a Flowable that emits everytime the data source, in this case the room db is updated
     */
    private fun setupFavouriteObserver(jobId: Int) {
        jobsRepository.getFavoriteJob(jobId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    isFavorite = true
                    _state.onNext(
                        JobDetailsState.UpdateFavoriteIcon(true)
                    )
                },
                onError = {
                    Timber.e(it)
                    isFavorite = false
                    JobDetailsState.UpdateFavoriteIcon(false)
                }
            ).addTo(disposables)
    }

    /**
     * Fetches company data from remote api, then saves the data to room db
     */
    fun getCompany(id: Int) {
        jobsRepository.getCompanyFromRemote(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _state.onNext(JobDetailsState.ShowLoading) }
            .doOnComplete { _state.onNext(JobDetailsState.HideLoading) }
            .doOnError { _state.onNext(JobDetailsState.HideLoading) }
            .subscribeBy(
                onComplete = {
                    Timber.e("onComplete")
                },
                onError = {
                    Timber.e(it)
                }
            ).addTo(disposables)
    }

    /**
     * Add job to favourites
     */
    private fun saveToFavourites(job: JobUIRepresentation) {
        jobsRepository.addJobToFavorites(JobUIRepresentation.toJob(job))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    isFavorite = true
                    _state.onNext(
                        JobDetailsState.UpdateFavoriteIcon(true)
                    )
                },
                onError = {
                    Timber.e(it)
                }
            ).addTo(disposables)
    }

    /**
     * Add job to favourites
     */
    private fun removeFromFavourites(job: JobUIRepresentation) {
        jobsRepository.removeJobFromFavourites(JobUIRepresentation.toJob(job))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    isFavorite = false
                    _state.onNext(
                        JobDetailsState.UpdateFavoriteIcon(false)
                    )
                },
                onError = {
                    Timber.e(it)
                }
            ).addTo(disposables)
    }

    fun onFavoritesClick(job: JobUIRepresentation) {
        if (isFavorite) {
            removeFromFavourites(job)
        } else {
            saveToFavourites(job)
        }
    }


}