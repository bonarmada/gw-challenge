package io.github.bonarmada.gw_challenge.features.home

import dagger.hilt.android.AndroidEntryPoint
import io.github.bonarmada.gw_challenge.R
import io.github.bonarmada.gw_challenge.base.BaseFragment
import io.github.bonarmada.gw_challenge.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_home

}