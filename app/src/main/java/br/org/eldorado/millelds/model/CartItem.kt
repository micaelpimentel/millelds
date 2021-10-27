package br.org.eldorado.millelds.model

import java.math.BigDecimal

data class CartItem(
    val product: Product,
    var amount: Int = 1
) {
    fun getSubTotal(): BigDecimal = product.price * amount.toBigDecimal()
}