package com.nimko.hilt_firebase_example.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.nimko.hilt_firebase_example.R
import com.nimko.hilt_firebase_example.databinding.FragmentDetailProviderBinding
import com.nimko.hilt_firebase_example.model.ServiceProvider

class DetailProviderFragment : Fragment() {

   lateinit var item:ServiceProvider
   lateinit var binding: FragmentDetailProviderBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getSerializable(ITEM, ServiceProvider::class.java)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailProviderBinding.inflate(inflater,container,false)
        binding.apply {
            name.text = item.name
            title.text = item.title
        }
        actionBar().changeActionBar(getString(R.string.details), true)
        return binding.root
    }

    companion object {
        private const val ITEM = "item"
        @JvmStatic
        fun newInstance(item: Any) =
            DetailProviderFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ITEM, item as ServiceProvider)
                }
            }
    }
}