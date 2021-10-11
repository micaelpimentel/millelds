package br.org.eldorado.millelds.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class Product (
    val name: String,
    val description: String? = null,
    val price: BigDecimal,
    val imageUrl: String? = null
) : Parcelable