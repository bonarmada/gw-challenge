package io.github.bonarmada.gw_challenge.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {

    lateinit var binding: B

    @LayoutRes
    abstract fun getLayoutId(): Int

    protected val disposables: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    open fun attachToParent(): Boolean = false

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            getLayoutId(),
            container,
            attachToParent()
        )
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onDestroyView() {
        disposables.clear()
        super.onDestroyView()
    }

}
