package io.github.bonarmada.gw_challenge.features.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.bonarmada.gw_challenge.R
import io.github.bonarmada.gw_challenge.THROTTLE_TIME_IN_MS
import io.github.bonarmada.gw_challenge.base.BaseFragment
import io.github.bonarmada.gw_challenge.data.model.Job
import io.github.bonarmada.gw_challenge.databinding.FragmentHomeBinding
import io.github.bonarmada.gw_challenge.features.home.adapter.JobsAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_home

    private val viewModel: HomeViewModel by viewModels()

    private var jobsAdapter: JobsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupVmObservers()
    }

    private fun setupViews() {
        with(binding) {
            jobsRecyclerView.apply {
                jobsAdapter = JobsAdapter()

                jobsAdapter!!
                    .itemClickListener
                    .throttleFirst(THROTTLE_TIME_IN_MS, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onNext = ::handleItemClick
                    )
                    .addTo(disposables)

                adapter = jobsAdapter
            }
        }
    }

    private fun handleItemClick(job: Job) {
        // Todo: handle click
        Timber.d(job.toString())
    }

    private fun setupVmObservers() {
        viewModel
            .jobResults
            .observe(viewLifecycleOwner) { articles ->
                jobsAdapter!!.submitData(lifecycle, articles)
            }
    }

    override fun onDestroyView() {
        jobsAdapter = null

        super.onDestroyView()
    }

}