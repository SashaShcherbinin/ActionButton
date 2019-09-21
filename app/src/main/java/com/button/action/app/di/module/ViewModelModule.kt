package com.button.action.app.di.module

import androidx.lifecycle.ViewModel
import com.button.action.app.di.ViewModelKey
import com.button.action.app.presentation.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindDetailViewModel(viewModel: MainViewModel): ViewModel
}