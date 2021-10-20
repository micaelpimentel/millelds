package br.org.eldorado.millelds.model

import br.org.eldorado.millelds.extensions.formatCurrencyToBr
import java.math.BigDecimal

data class CartItem(
    val product: Product,
    var amount: Int = 1
) {
    fun getSubTotal(): String = (product.price * amount.toBigDecimal()).formatCurrencyToBr()
}