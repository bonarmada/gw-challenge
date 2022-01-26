package io.github.bonarmada.gw_challenge.ui.features.detail

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding4.view.clicks
import dagger.hilt.android.AndroidEntryPoint
import io.github.bonarmada.gw_challenge.R
import io.github.bonarmada.gw_challenge.base.BaseFragment
import io.github.bonarmada.gw_challenge.databinding.FragmentJobDetailsBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber

@AndroidEntryPoint
class JobDetailsFragment : BaseFragment<FragmentJobDetailsBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_job_details

    private val viewModel: JobDetailsViewModel by viewModels()

    private val args by navArgs<JobDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar(
            toolbar = binding.includeToolbar.toolbar
        )

        setupViews()
        setupVmObservers()

        viewModel.setJobData(args.job)
    }

    private fun setupViews() {
        binding.includeToolbar.toolbarButton.clicks().subscribe {
            viewModel.onFavoritesClick(args.job)
        }.addTo(disposables)

        binding.applyNowButton.clicks().subscribe {
            openPageOnExternalBrowser(args.job.jobLandingPageUrl)
        }.addTo(disposables)
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
                binding.progressBar.visibility = View.VISIBLE
            }
            is JobDetailsState.HideLoading -> {
                binding.progressBar.visibility = View.GONE
            }
            is JobDetailsState.UpdateFavoriteIcon -> {
                when (state.isFavorite) {
                    true -> binding.includeToolbar.toolbarButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                    false -> binding.includeToolbar.toolbarButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }
            is JobDetailsState.UpdateJobDetails -> {
                with(binding) {
                    titleTextView.text = state.job.jobName
                    companyNameTextView.text = state.job.companyName
                    contentTextView.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Html.fromHtml(state.job.contents, Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        Html.fromHtml(state.job.contents)
                    }

                }
            }
            is JobDetailsState.UpdateCompanyDetails -> {
                with(binding) {
                    Glide.with(binding.root.context)
                        .load(state.company.logoUrl)
                        .into(companyLogoImageView)
                    locationTextView.text = state.company.location
                }
            }
        }
    }

    private fun openPageOnExternalBrowser(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}