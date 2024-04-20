package com.example.androidprojecttemplate.core.data_store.module

import android.content.Context
import com.example.androidprojecttemplate.core.data_store.implementation.SharedPrefsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferenceModule {

    @Provides
    @Singleton
    fun provideSharedPrefsService(@ApplicationContext context: Context) : SharedPrefsService {
        return  SharedPrefsService(context);
    }
}