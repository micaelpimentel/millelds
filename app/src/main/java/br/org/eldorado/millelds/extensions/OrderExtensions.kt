package br.org.eldorado.millelds.extensions

import br.org.eldorado.millelds.model.Order
import java.text.SimpleDateFormat
import java.util.*

fun Order.getDateString(): String {
    val date = Date(orderDate)
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("pt","br"))
    return sdf.format(date)
}

fun Order.getItensCount(): Int  = items.sumOf { it.amount }