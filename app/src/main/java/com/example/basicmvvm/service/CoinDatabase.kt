package com.example.basicmvvm.service


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.basicmvvm.model.CoinModel


@Database(entities = arrayOf(CoinModel::class), version = 1)
abstract class CoinDatabase : RoomDatabase() {

    abstract fun coinDao(): CoinDao


    companion object {
        @Volatile private var instance: CoinDatabase? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,CoinDatabase::class.java,"coindatabase"
        ).build()


    }

}