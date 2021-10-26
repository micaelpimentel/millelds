package br.org.eldorado.millelds.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.org.eldorado.millelds.database.dao.ProductDAO
import br.org.eldorado.millelds.model.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class MainDataBase : RoomDatabase() {

    abstract fun getProductDao(): ProductDAO

    companion object {
        private val DATABASE_NAME = "main.db"

        private var INSTANCE: MainDataBase? = null

        fun getInstance(context: Context): MainDataBase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(context, MainDataBase::class.java, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as MainDataBase
        }
    }
}