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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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

    fun deleteProvider(provider: ServiceProvider){
        providers.value?.get(provider)?.let { providersRef.child(it).removeValue() }
    }

    companion object{
        private const val PROVIDERS = "providers"
        private const val TAG = "VWM"
    }
}