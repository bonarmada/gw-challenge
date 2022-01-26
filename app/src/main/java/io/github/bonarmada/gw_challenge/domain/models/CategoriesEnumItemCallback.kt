package io.github.bonarmada.gw_challenge.domain.models

import androidx.recyclerview.widget.DiffUtil

class CategoriesEnumItemCallback : DiffUtil.ItemCallback<CategoriesEnum>() {

    override fun areItemsTheSame(oldItem: CategoriesEnum, newItem: CategoriesEnum): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CategoriesEnum, newItem: CategoriesEnum): Boolean {
        return oldItem.stringValue == newItem.stringValue &&
                oldItem.isSelected == newItem.isSelected
    }
}
