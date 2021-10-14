package br.org.eldorado.millelds.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun BigDecimal.formatCurrencyToBr(): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatter.format(this)
}