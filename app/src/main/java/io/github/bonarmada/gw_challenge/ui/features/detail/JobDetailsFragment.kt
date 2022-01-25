package io.github.bonarmada.gw_challenge.ui.features.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import io.github.bonarmada.gw_challenge.R
import io.github.bonarmada.gw_challenge.THROTTLE_TIME_IN_MS
import io.github.bonarmada.gw_challenge.base.BaseFragment
import io.github.bonarmada.gw_challenge.base.SimpleListAdapter
import io.github.bonarmada.gw_challenge.data.model.CategoriesEnum
import io.github.bonarmada.gw_challenge.data.model.CategoriesEnumItemCallback
import io.github.bonarmada.gw_challenge.data.model.Job
import io.github.bonarmada.gw_challenge.databinding.FragmentHomeBinding
import io.github.bonarmada.gw_challenge.databinding.FragmentJobDetailsBinding
import io.github.bonarmada.gw_challenge.databinding.ItemFilterBinding
import io.github.bonarmada.gw_challenge.ui.features.home.adapter.JobsAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class JobDetailsFragment : BaseFragment<FragmentJobDetailsBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_job_details

    private val viewModel: JobDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupVmObservers()

        viewModel.getCompany()
    }

    private fun setupViews() {
    }


    private fun setupVmObservers() {
        viewModel.state
            .toFlowable(BackpressureStrategy.BUFFER)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = ::handleState,
                onError = {
                    Timber.e(it)
                }
            )
            .apply { disposables.add(this) }
    }

    private fun handleState(state: JobDetailsState) {
        when (state) {
            is JobDetailsState.ShowLoading -> {

            }
            is JobDetailsState.HideLoading -> {

            }
            is JobDetailsState.UpdateJobDetails -> {

            }
            is JobDetailsState.UpdateCompanyDetails -> {
                with(binding) {
                    Glide.with(binding.root.context)
                        .load(state.company.refs?.logoImage)
                        .into(companyLogoImageView)
                    locationTextView.text = state.company.displayLocation
                }
            }
        }
    }
}