package io.github.bonarmada.gw_challenge.data.model

import androidx.recyclerview.widget.DiffUtil
import timber.log.Timber

class CategoriesEnumItemCallback : DiffUtil.ItemCallback<CategoriesEnum>() {

    override fun areItemsTheSame(oldItem: CategoriesEnum, newItem: CategoriesEnum): Boolean {
        val x = oldItem.name == newItem.name
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CategoriesEnum, newItem: CategoriesEnum): Boolean {
        return oldItem.stringValue == newItem.stringValue &&
                oldItem.isSelected == newItem.isSelected
    }
}
