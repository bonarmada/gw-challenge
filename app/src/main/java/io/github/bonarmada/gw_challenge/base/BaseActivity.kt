package io.github.bonarmada.gw_challenge.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: B

    @LayoutRes
    abstract fun getLayoutId(): Int

    protected val disposables: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            getLayoutId()
        )
        binding.lifecycleOwner = this
    }

    /*
     * Safe call method, just in case, if anything is messed up and
     * lifecycle Event does not gets
     * called.
     */
    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

}
