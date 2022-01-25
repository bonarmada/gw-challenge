package io.github.bonarmada.gw_challenge.ui.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.bonarmada.gw_challenge.R
import io.github.bonarmada.gw_challenge.base.BasePagingAdapter
import io.github.bonarmada.gw_challenge.databinding.ItemJobBinding
import io.github.bonarmada.gw_challenge.ui.features.home.JobUIRepresentation
import io.reactivex.rxjava3.subjects.PublishSubject

class JobsAdapter :
    BasePagingAdapter<JobUIRepresentation, BasePagingAdapter.BaseViewHolder<JobUIRepresentation>>(
        JobUIRepresentationItemCallback()
    ) {

    val itemClickListener = PublishSubject.create<JobUIRepresentation>()

    override fun onBindViewHolder(holder: BaseViewHolder<JobUIRepresentation>, position: Int) {
        holder.bind(getItem(holder.bindingAdapterPosition)!!)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<JobUIRepresentation> {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.item_job,
                    parent,
                    false
                )

        val binding = ItemJobBinding.bind(view)
        return JobUIRepresentationViewHolder(binding)
    }

    inner class JobUIRepresentationViewHolder(override val binding: ItemJobBinding) :
        BaseViewHolder<JobUIRepresentation>(binding) {
        override fun bind(item: JobUIRepresentation) {
            super.bind(item)

            binding
                .root
                .setOnClickListener {
                    itemClickListener.onNext(item)
                }
        }
    }
}
