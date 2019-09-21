package com.button.action.app.di.module

import androidx.lifecycle.ViewModelProvider
import com.button.action.app.presentation.common.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * User: Sasha Shcherbinin
 * Date : 4/3/19
 */
@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory)
            : ViewModelProvider.Factory
}