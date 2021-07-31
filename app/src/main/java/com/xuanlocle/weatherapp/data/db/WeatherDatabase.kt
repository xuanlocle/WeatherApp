package com.xuanlocle.weatherapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.xuanlocle.weatherapp.data.db.entity.WeatherEntity
import com.xuanlocle.weatherapp.data.db.entity.WeatherSummaryEntity


@Database(version = 2,
    entities = [WeatherEntity::class, WeatherSummaryEntity::class])
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun getWeatherDao(): WeatherDao

    companion object {

        @Volatile
        private var instance: WeatherDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: createDatabase(
                        context
                    ).also { instance = it }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                WeatherDatabase::class.java, "weather.db")
                .addMigrations(migration1_2)
                .build()

        val migration1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE table_weather_list ADD COLUMN item_description VARCHAR(100) ")
            }

        }

    }

}