package br.org.eldorado.millelds.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.org.eldorado.millelds.database.dao.ProductDAO
import br.org.eldorado.millelds.model.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class MainDataBase : RoomDatabase() {

    abstract fun getProductDao(): ProductDAO

    companion object {
        val DATABASE_NAME = "main.db"
    }
}