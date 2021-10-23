package br.org.eldorado.millelds.dao

import br.org.eldorado.millelds.model.CartItem
import br.org.eldorado.millelds.model.Order

class OrderDAO {
    companion object {
        private val orders = mutableListOf<Order>()
    }

    fun add(order: Order) = orders.add(order)

    fun add(cart: List<CartItem>, date: Long) = add(Order(cart, date))

    fun get(position: Int) = orders[position]

    fun getAll() = orders.toList()
}