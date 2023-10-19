package com.nimko.hilt_firebase_example.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.nimko.hilt_firebase_example.databinding.FragmentEditProviderBinding
import com.nimko.hilt_firebase_example.model.ServiceProvider
import com.nimko.hilt_firebase_example.vievmodel.MainViewModel
import javax.inject.Inject

class EditProviderFragment @Inject constructor(): Fragment() {

    lateinit var binding: FragmentEditProviderBinding
    val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProviderBinding.inflate(inflater, container, false)
        binding.apply {
            saveBottom.setOnClickListener {
                val provider = ServiceProvider(
                    name.text.toString(),
                    title.text.toString()
                )
                viewModel.addProvider(provider)
                name.setText("")
                title.setText("")
                navigator().goBack()
            }
        }
        actionBar().changeActionBar("New Provider", true)
        return binding.root
    }



    companion object {

    }

}

