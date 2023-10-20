package com.nimko.hilt_firebase_example.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.nimko.hilt_firebase_example.R
import com.nimko.hilt_firebase_example.databinding.FragmentEditProviderBinding
import com.nimko.hilt_firebase_example.model.ServiceProvider
import com.nimko.hilt_firebase_example.vievmodel.MainViewModel
import javax.inject.Inject

class EditProviderFragment @Inject constructor(): Fragment() {

    lateinit var binding: FragmentEditProviderBinding
    val viewModel by activityViewModels<MainViewModel>()
    var editProvider: ServiceProvider? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            if (it != null) {
                editProvider = it.getSerializable(PROVIDER,ServiceProvider::class.java)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProviderBinding.inflate(inflater, container, false)
        binding.apply {
            editProvider?.let {
                name.setText(it.name)
                title.setText(it.title)
            }
            saveBottom.setOnClickListener {
                val provider = ServiceProvider(
                    name.text.toString(),
                    title.text.toString()
                )
                if (editProvider == null){
                    viewModel.addProvider(provider)
                } else {
                    viewModel.editProvider(editProvider!!, provider)
                }
                name.setText("")
                title.setText("")
                navigator().goBack()
            }
        }
        if (editProvider == null)
            actionBar().changeActionBar(getString(R.string.new_provider), true)
        else
            actionBar().changeActionBar(getString(R.string.edit_provider), true)
        return binding.root
    }



    companion object {
        const val PROVIDER = "provider"
        @JvmStatic
        fun newInstance(provider: ServiceProvider) = EditProviderFragment().apply {
            arguments = Bundle().apply {
                this.putSerializable(PROVIDER, provider)
            }
        }

    }

}

