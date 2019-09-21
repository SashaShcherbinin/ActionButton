package com.button.action.app.di

import com.button.action.app.di.module.AppModule
import com.button.action.app.di.module.NetworkModule
import com.button.action.app.di.module.ViewModelFactoryModule
import com.button.action.app.di.module.ViewModelModule
import com.button.action.app.presentation.App
import com.button.action.app.presentation.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class
    ]
)
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    class Initializer private constructor() {
        companion object {

            fun init(app: App): AppComponent {

                return DaggerAppComponent.builder()
                    .appModule(AppModule(app))
                    .build()
            }
        }
    }
}