package br.org.eldorado.millelds.database.dao

import androidx.room.*
import br.org.eldorado.millelds.model.Product
import java.util.*

@Dao
interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(vararg product: Product)

    @Query("SELECT * FROM products WHERE id = :id")
    fun get(id: Int): Product

    @Query("SELECT * FROM products")
    fun getAll(): List<Product>

    @Delete
    fun delete(vararg product: Product)

    @Update
    fun update(vararg product: Product)
}