package br.org.eldorado.millelds.model

import java.math.BigDecimal

data class Product(
    val name: String,
    val description: String? = null,
    val price: BigDecimal
)