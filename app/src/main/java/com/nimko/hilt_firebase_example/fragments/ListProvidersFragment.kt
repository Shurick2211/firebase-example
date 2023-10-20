package com.nimko.hilt_firebase_example.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.nimko.hilt_firebase_example.R
import com.nimko.hilt_firebase_example.databinding.FragmentListBinding
import com.nimko.hilt_firebase_example.vievmodel.MainViewModel
import javax.inject.Inject

class ListProvidersFragment<T : Any> @Inject constructor(): Fragment(), OnClickItem<T> {

    lateinit var binding:FragmentListBinding
    val viewModel by activityViewModels<MainViewModel>()

    val recyclerViewAdapter = ListProvidersRecyclerViewAdapter<T>(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)

        binding.recyclerView.adapter = recyclerViewAdapter

        viewModel.providers.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "observe")
            recyclerViewAdapter.addCollection(it.keys as MutableSet<T>)
        })

        binding.fab.setOnClickListener {
            navigator().launchEditProvider()
        }
        viewModel.getRepoData()
        actionBar().changeActionBar(resources.getString(R.string.app_name),false)
        return binding.root
    }




    companion object {
        const val TAG = "LPF"
    }

    override fun onClick(item: T) {
        navigator().listProviderDetails(item)
    }

    override fun delete(item: T) {
        viewModel.deleteProvider(item)
    }

    override fun edit(item: T) {
        navigator().launchEditProvider(item)
    }


}