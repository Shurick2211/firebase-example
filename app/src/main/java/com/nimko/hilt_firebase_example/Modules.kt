package com.nimko.hilt_firebase_example

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nimko.hilt_firebase_example.fragments.ListProvidersFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Modules {


    @Provides
    @Singleton
    fun provideFirebaseDb():FirebaseDatabase{
        return Firebase.database
    }



}