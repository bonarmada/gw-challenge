package io.github.bonarmada.gw_challenge.ui.features.detail
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bonarmada.gw_challenge.base.BaseViewModel
import io.github.bonarmada.gw_challenge.data.repository.JobsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class JobDetailsViewModel @Inject constructor(
    private val jobsRepository: JobsRepository
) : BaseViewModel() {

    fun getCompany() {
        jobsRepository.getCompany(11913)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    Timber.e(it.toString())
                },
                onError = {
                    Timber.e(it)
                }
            ).addTo(disposables)
    }
}