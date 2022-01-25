package io.github.bonarmada.gw_challenge.ui.features.home.adapter

import androidx.recyclerview.widget.DiffUtil
import io.github.bonarmada.gw_challenge.ui.features.home.JobUIRepresentation

class JobUIRepresentationItemCallback : DiffUtil.ItemCallback<JobUIRepresentation>() {

    override fun areItemsTheSame(oldItem: JobUIRepresentation, newItem: JobUIRepresentation): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: JobUIRepresentation, newItem: JobUIRepresentation): Boolean {
        return oldItem.contents == newItem.contents &&
                oldItem.companyName == newItem.companyName &&
                oldItem.jobName == newItem.jobName &&
                oldItem.location == newItem.location
    }
}
