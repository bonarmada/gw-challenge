package io.github.bonarmada.gw_challenge.ui.features.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.bonarmada.gw_challenge.R
import io.github.bonarmada.gw_challenge.THROTTLE_TIME_IN_MS
import io.github.bonarmada.gw_challenge.base.BaseFragment
import io.github.bonarmada.gw_challenge.base.SimpleListAdapter
import io.github.bonarmada.gw_challenge.data.model.CategoriesEnum
import io.github.bonarmada.gw_challenge.data.model.CategoriesEnumItemCallback
import io.github.bonarmada.gw_challenge.data.model.Job
import io.github.bonarmada.gw_challenge.databinding.FragmentHomeBinding
import io.github.bonarmada.gw_challenge.databinding.ItemFilterBinding
import io.github.bonarmada.gw_challenge.ui.features.home.adapter.JobsAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_home

    private val viewModel: HomeViewModel by viewModels()

    private var jobsAdapter: JobsAdapter? = null
    private var filtersAdapter: SimpleListAdapter<CategoriesEnum, ItemFilterBinding>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupVmObservers()

        viewModel.setCategories(CategoriesEnum.values().map { it })
    }

    private fun setupViews() {
        with(binding) {
            filtersRecyclerView.apply {
                filtersAdapter = SimpleListAdapter(
                    CategoriesEnumItemCallback(), R.layout.item_filter
                ) {
                    viewModel.onFilterSelected(it)
                    jobsAdapter?.refresh()
                }

                adapter = filtersAdapter
            }

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

    private fun handleItemClick(job: JobUIRepresentation) {
        findNavController().navigate(
            HomeFragmentDirections
                .actionHomeFragmentToJobDetailsFragment(job)
        )
    }

    private fun setupVmObservers() {
        viewModel
            .jobResults
            .observe(viewLifecycleOwner) { jobs ->
                jobsAdapter!!.submitData(lifecycle, jobs)
            }

        viewModel
            .categories.observe(viewLifecycleOwner) {
                filtersAdapter?.updateItems(it)

                // Update selected categories once when list is updating
                viewModel.selectedCategories = it.filter { category ->
                    category.isSelected
                }.map { category ->
                    category.stringValue
                }
            }
    }

    override fun onDestroyView() {
        jobsAdapter = null

        super.onDestroyView()
    }

}