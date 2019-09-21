package com.button.action.app.di.module

import com.button.action.app.BuildConfig
import com.button.action.app.data.RxErrorHandlingCallAdapterFactory
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun createRetrofit(): Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl("https://localhost")
            .addConverterFactory(GsonConverterFactory.create(createGson()))
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())

        builder.client(createOkHttpClient())

        return builder.build()
    }

    @Provides
    internal fun createGson() = GsonBuilder().create()!!

    private fun createOkHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.connectTimeout(15, TimeUnit.SECONDS)
        clientBuilder.readTimeout(15, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(15, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(interceptor)
        }
        return clientBuilder.build()
    }

}

