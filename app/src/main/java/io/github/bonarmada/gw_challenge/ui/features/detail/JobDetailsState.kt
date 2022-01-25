package io.github.bonarmada.gw_challenge.ui.features.detail

import io.github.bonarmada.gw_challenge.data.model.Company
import io.github.bonarmada.gw_challenge.data.model.Job

sealed class JobDetailsState {

    object ShowLoading : JobDetailsState()

    object HideLoading : JobDetailsState()

    data class UpdateJobDetails(val job: Job) : JobDetailsState()

    data class UpdateCompanyDetails(val company: Company) : JobDetailsState()
}
