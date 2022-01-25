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

    fun setJobData(job: JobUIRepresentation) {
        _state.onNext(
            JobDetailsState.UpdateJobDetails(job)
        )

        getCompany(job.companyId)
    }

    fun getCompany(companyId: Int) {
        jobsRepository.getCompany(companyId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _state.onNext(JobDetailsState.ShowLoading) }
            .doOnSuccess { _state.onNext(JobDetailsState.HideLoading) }
            .doOnError { _state.onNext(JobDetailsState.HideLoading) }
            .subscribeBy(
                onSuccess = {
                    _state.onNext(
                        JobDetailsState.UpdateCompanyDetails(it)
                    )
                },
                onError = {
                    Timber.e(it)
                }
            ).addTo(disposables)
    }
}