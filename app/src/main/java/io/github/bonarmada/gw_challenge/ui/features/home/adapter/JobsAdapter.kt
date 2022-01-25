package io.github.bonarmada.gw_challenge.ui.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.bonarmada.gw_challenge.R
import io.github.bonarmada.gw_challenge.base.BasePagingAdapter
import io.github.bonarmada.gw_challenge.data.model.Job
import io.github.bonarmada.gw_challenge.databinding.ItemJobBinding
import io.reactivex.rxjava3.subjects.PublishSubject

class JobsAdapter :
    BasePagingAdapter<Job, BasePagingAdapter.BaseViewHolder<Job>>(JobsItemCallback()) {

    val itemClickListener = PublishSubject.create<Job>()

    override fun onBindViewHolder(holder: BaseViewHolder<Job>, position: Int) {
        holder.bind(getItem(holder.bindingAdapterPosition)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Job> {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.item_job,
                    parent,
                    false
                )

        val binding = ItemJobBinding.bind(view)
        return JobViewHolder(binding)
    }

    inner class JobViewHolder(override val binding: ItemJobBinding) :
        BaseViewHolder<Job>(binding) {
        override fun bind(item: Job) {
            super.bind(item)

            binding
                .root
                .setOnClickListener {
                    itemClickListener.onNext(item)
                }
        }
    }
}
