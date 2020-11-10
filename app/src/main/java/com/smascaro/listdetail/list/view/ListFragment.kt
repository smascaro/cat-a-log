package com.smascaro.listdetail.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.smascaro.listdetail.BaseApplication
import com.smascaro.listdetail.R
import com.smascaro.listdetail.databinding.FragmentListBinding
import com.smascaro.listdetail.list.viewmodel.ListViewModel
import javax.inject.Inject

class ListFragment : Fragment() {
    companion object {
        fun newInstance() = ListFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ListViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var adapter: BreedsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as BaseApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val binding = DataBindingUtil.setContentView(
            requireActivity(),
            R.layout.fragment_list
        ) as FragmentListBinding
        binding.vm = viewModel
        binding.lifecycleOwner = this
        adapter = BreedsAdapter()
        binding.fragmentListRecyclerItems.adapter = adapter
    }

}