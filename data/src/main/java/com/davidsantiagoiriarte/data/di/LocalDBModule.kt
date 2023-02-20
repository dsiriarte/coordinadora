package com.davidsantiagoiriarte.data.di

import android.content.Context
import androidx.room.Room
import com.davidsantiagoiriarte.data.localstorage.LocalDB
import com.davidsantiagoiriarte.data.localstorage.daos.GuiasDao
import com.davidsantiagoiriarte.data.localstorage.daos.UnidadesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalDBModule {
    @Provides
    @Singleton
    fun createDatabase(@ApplicationContext applicationContext: Context): LocalDB {
        return Room.databaseBuilder(
            applicationContext,
            LocalDB::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideGuiasDao(localDB: LocalDB): GuiasDao {
        return localDB.guiasDao()
    }

    @Provides
    @Singleton
    fun provideUnidadesDao(localDB: LocalDB): UnidadesDao {
        return localDB.unidadesDao()
    }
}

const val DATABASE_NAME = "coordinadora"
