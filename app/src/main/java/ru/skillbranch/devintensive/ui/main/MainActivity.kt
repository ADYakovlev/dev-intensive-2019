package ru.skillbranch.devintensive.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolBar()
        initViews()
        initViewModel()

    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private fun initViews() {

    }

    private fun initToolBar() {

    }
}
