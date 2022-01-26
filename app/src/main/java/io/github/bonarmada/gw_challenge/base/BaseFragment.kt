package io.github.bonarmada.gw_challenge.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.CallSuper
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.github.bonarmada.gw_challenge.R
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

    fun setupToolbar(
        toolbar: Toolbar,
        title: String = "",
        showBackButton: Boolean = true
    ) {
        (requireActivity() as AppCompatActivity).apply {
            setHasOptionsMenu(true)
            setSupportActionBar(toolbar)
            if (showBackButton) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setHomeButtonEnabled(true)
            }
            binding.root.findViewById<TextView>(R.id.toolbarTextView)?.let {
                supportActionBar?.title = ""
                it.text = title
            }
        }
    }
}
