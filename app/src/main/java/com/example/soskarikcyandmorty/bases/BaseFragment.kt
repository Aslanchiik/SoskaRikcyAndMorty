package com.example.soskarikcyandmorty.bases

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.viewbinding.ViewBinding
import com.example.soskarikcyandmorty.presentation.state.UIState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

abstract class BaseFragment<VB : ViewBinding>(@LayoutRes layoutId: Int) :
    Fragment(layoutId) {

    protected abstract val binding: VB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupViews()
        setupListener()
        setupRequest()
        setupObserves()

    }

    protected open fun initialize() {}

    protected open fun setupViews() {}

    protected open fun setupListener() {}

    protected open fun setupRequest() {}

    protected open fun setupObserves() {}

    protected open fun <T> StateFlow<UIState<T>>.subscribe(
        state: Lifecycle.State = Lifecycle.State.STARTED,
        action: (UIState<T>) -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(state) {
                this@subscribe.collect {
                    action(it)
                }
            }
        }
    }

    protected open fun <T : Any> StateFlow<PagingData<T>>.subscribePaging(
        state: Lifecycle.State = Lifecycle.State.STARTED,
        action: suspend (PagingData<T>) -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(state) {
                this@subscribePaging.collect {
                    action(it)
                }
            }
        }
    }
}