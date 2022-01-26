package io.github.bonarmada.gw_challenge.ui.features.detail

import io.github.bonarmada.gw_challenge.ui.features.home.JobUIRepresentation

sealed class JobDetailsState {

    object ShowLoading : JobDetailsState()

    object HideLoading : JobDetailsState()

    data class UpdateJobDetails(val job: JobUIRepresentation) : JobDetailsState()

    data class UpdateCompanyDetails(val company: CompanyUIRepresentation) : JobDetailsState()
}
