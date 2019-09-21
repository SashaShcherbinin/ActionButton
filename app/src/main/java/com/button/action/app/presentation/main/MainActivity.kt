package com.button.action.app.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.button.action.app.R
import com.button.action.app.databinding.ActivityMainBinding
import com.button.action.app.presentation.App
import com.button.action.app.presentation.common.BaseActivity
import com.button.action.app.presentation.common.ViewModelFactory
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.getComponent(this).inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)
        binding.vm = viewModel

        observeEvents()
        observeState()
    }

    private fun observeState() {
        viewModel.uploading.observe(this, Observer {
            showUploading(it)
        })
    }

    private fun observeEvents() {
        viewModel.animationEvent.observe(this, Observer {
            // TODO add animation action logic
            Toast.makeText(this, "ANIMATION ACTION", Toast.LENGTH_LONG).show()
        })
        viewModel.callEvent.observe(this, Observer {
            // TODO add call action logic
            Toast.makeText(this, "CALL ACTION", Toast.LENGTH_LONG).show()
        })
        viewModel.toastEvent.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
        viewModel.notificationEvent.observe(this, Observer {
            // TODO add notification action logic
            Toast.makeText(this, "NOTIFICATION ACTION", Toast.LENGTH_LONG).show()
        })
        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }
}
