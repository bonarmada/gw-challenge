package io.github.bonarmada.gw_challenge.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class ItemCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.toString() == newItem.toString()

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}
