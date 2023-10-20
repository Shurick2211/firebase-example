package com.nimko.hilt_firebase_example.fragments

import androidx.fragment.app.Fragment
import com.nimko.hilt_firebase_example.model.ServiceProvider

interface NavigationFrag {
    fun launchEditProvider(provider: Any? = null)
    fun listProviderDetails(item:Any)
    fun goBack()
}

fun Fragment.navigator() = requireActivity() as NavigationFrag

fun Fragment.actionBar() = requireActivity() as ActionBarChange

interface ActionBarChange{
   fun changeActionBar(title:String, hasBackArrow:Boolean)
}