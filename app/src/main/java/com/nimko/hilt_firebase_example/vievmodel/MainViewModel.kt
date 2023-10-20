package com.nimko.hilt_firebase_example.vievmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nimko.hilt_firebase_example.model.ServiceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor():ViewModel() {

    private val _providers = MutableLiveData<MutableMap<ServiceProvider, String>>()
    val providers: LiveData<MutableMap<ServiceProvider, String>> = _providers

    lateinit var providersRef:DatabaseReference


    @Inject
    lateinit var database: FirebaseDatabase

    fun getRepoData(){
        providersRef = database.getReference(PROVIDERS)

        providersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataProviders =
                 snapshot.children.associate {
                     Pair(it.getValue(ServiceProvider::class.java)!!, it.key!!)
                 }.toMutableMap()
                _providers.postValue(dataProviders)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, error.toString())
            }
        })

    }

    fun addProvider(provider: ServiceProvider){
        providersRef.push().setValue(provider)

    }

    fun deleteProvider(provider: Any){
        providers.value?.get(provider)?.let { providersRef.child(it).removeValue() }
    }

    fun editProvider(itemOld: ServiceProvider, itemNew:ServiceProvider){
        providers.value?.get(itemOld)?.let {
            providersRef.child(it).setValue(itemNew)
        }
    }



    companion object{
        private const val PROVIDERS = "providers"
        private const val TAG = "VWM"
    }
}