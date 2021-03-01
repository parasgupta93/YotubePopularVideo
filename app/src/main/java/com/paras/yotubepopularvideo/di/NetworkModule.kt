package com.paras.yotubepopularvideo.di

import android.content.Context
import android.util.Log
import com.paras.yotubepopularvideo.data.service.ApiService
import com.paras.yotubepopularvideo.util.baseUrl
import com.paras.yotubepopularvideo.util.isNetworkAvailable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient?): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .client(okHttpClient)
            .build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideClient(@ApplicationContext context: Context,interceptor: Interceptor?): OkHttpClient {
        val cache = Cache(
            File(context.getCacheDir(), "http-cache"),
            10 * 1024 * 1024
        )
        return OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .cache(cache).connectionPool(ConnectionPool(3, 40, TimeUnit.SECONDS))
                .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideInterceptor(@ApplicationContext context: Context): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            if (isNetworkAvailable(context)) {
                val cacheControl = CacheControl.Builder()
                    .maxAge(5, TimeUnit.MINUTES)
                    .build()
                response.newBuilder()
                    .header("Cache-Control", cacheControl.toString())
                    .build()
            } else {
                Log.d("Paras", "Returning old")
                response
            }
        }
    }
}
