package com.nimko.hilt_firebase_example.fragments

import androidx.fragment.app.Fragment
import com.nimko.hilt_firebase_example.model.ServiceProvider

interface NavigationFrag {
    fun launchEditProvider()
    fun listProviderDetails(item:ServiceProvider)
    fun goBack()
}

fun Fragment.navigator() = requireActivity() as NavigationFrag

fun Fragment.actionBar() = requireActivity() as ActionBarChange

interface ActionBarChange{
   fun changeActionBar(title:String, hasBackArrow:Boolean)
}