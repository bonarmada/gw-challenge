package io.github.bonarmada.gw_challenge.base

import androidx.databinding.ViewDataBinding
import io.github.bonarmada.gw_challenge.BR
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BasePagingAdapter<T : Any, VH : BasePagingAdapter.BaseViewHolder<T>>(
    diffUtil: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, VH>(diffUtil) {

    open class BaseViewHolder<T>(open val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        open fun bind(item: T) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}
