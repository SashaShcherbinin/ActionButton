package com.button.action.app.presentation

import android.app.Application
import android.content.Context
import com.button.action.app.di.AppComponent

/**
 * User: Sasha Shcherbinin
 * Date : 2019-09-21
 */
class App : Application() {

    companion object {
        fun getComponent(context: Context): AppComponent {
            return (context.applicationContext as App).appComponent
        }
    }

    private val appComponent: AppComponent by lazy {
        AppComponent.Initializer
            .init(this)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent
    }
}
