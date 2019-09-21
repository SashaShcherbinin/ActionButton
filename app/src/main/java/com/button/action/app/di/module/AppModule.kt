package com.button.action.app.di.module

import android.content.Context
import com.button.action.app.presentation.common.error.DefaultErrorHandler

import dagger.Module
import dagger.Provides
import org.xml.sax.ErrorHandler

@Module
class AppModule(private val context: Context) {

    @Provides
    internal fun provideContext(): Context = context

}
