package com.nimko.hilt_firebase_example

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nimko.hilt_firebase_example.databinding.ActivityMainBinding
import com.nimko.hilt_firebase_example.fragments.ActionBarChange
import com.nimko.hilt_firebase_example.fragments.DetailProviderFragment
import com.nimko.hilt_firebase_example.fragments.EditProviderFragment
import com.nimko.hilt_firebase_example.fragments.ListProvidersFragment
import com.nimko.hilt_firebase_example.fragments.NavigationFrag
import com.nimko.hilt_firebase_example.model.ServiceProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationFrag, ActionBarChange {

    private lateinit var binding: ActivityMainBinding
   // private val viewModel:MainViewModel by viewModels()
   @Inject lateinit var listFragment:ListProvidersFragment
   @Inject lateinit var editFragment:EditProviderFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView,listFragment)
                .commit()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> goBack()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun launch(fragment:Fragment){
        Log.d(TAG,"Launch!")
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainerView,fragment)
            .commit()
    }



    override fun launchEditProvider(provider: ServiceProvider?) {
        if (provider == null) launch(editFragment)
        else {
            val fragment = EditProviderFragment.newInstance(provider)
            launch(fragment)
        }
    }

    override fun listProviderDetails(item: ServiceProvider) {
        val fragment = DetailProviderFragment.newInstance(item)
        launch(fragment)
    }

    override fun goBack() {
        onBackPressedDispatcher.onBackPressed()
    }

    companion object{
        const val TAG = "MAIN_ACT"
    }

    override fun changeActionBar(title: String, hasBackArrow: Boolean) {
        supportActionBar?.apply {
            setTitle(title)
            setDisplayHomeAsUpEnabled(hasBackArrow)
        }
    }
}