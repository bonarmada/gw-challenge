package io.github.bonarmada.gw_challenge.ui.features.home.adapter

import androidx.recyclerview.widget.DiffUtil
import io.github.bonarmada.gw_challenge.data.model.Job

class JobsItemCallback : DiffUtil.ItemCallback<Job>() {

    override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
        return oldItem.contents == newItem.contents &&
                oldItem.name == newItem.name &&
                oldItem.publicationDate == newItem.publicationDate &&
                oldItem.company == newItem.company
    }
}
