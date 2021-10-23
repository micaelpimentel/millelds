package br.org.eldorado.millelds.model

data class Order(
    val items: List<CartItem>,
    val orderDate: Long
) {
    val orderTotal
        get() = items.sumOf { it.getSubTotal() }
}