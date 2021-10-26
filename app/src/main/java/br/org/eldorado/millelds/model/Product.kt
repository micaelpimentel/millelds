package br.org.eldorado.millelds.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "price") var priceString: String,
    @ColumnInfo(name = "img_url") val imageUrl: String? = null
) : Parcelable {
    @IgnoredOnParcel
    @Ignore
    val price: BigDecimal = priceString.toBigDecimal()
}