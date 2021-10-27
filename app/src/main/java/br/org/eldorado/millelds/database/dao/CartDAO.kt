package br.org.eldorado.millelds.database.dao

import br.org.eldorado.millelds.model.CartItem
import br.org.eldorado.millelds.model.Product
import java.util.*

class CartDAO {
    companion object {
        private val cartItems = mutableListOf<CartItem>()
    }

    fun add(product: Product) {
        val foundIndex = cartItems.indexOf(product)
        if (foundIndex != -1) {
            cartItems[foundIndex].amount += 1
        } else
            cartItems.add(CartItem(product))
    }

    private fun MutableList<CartItem>.indexOf(product: Product) =
        this.indexOfFirst {
            it.product == product
        }

    fun add(item: CartItem) {
        val foundIndex = cartItems.indexOf(item.product)
        if (foundIndex != -1) {
            cartItems[foundIndex].amount += 1
        } else
            cartItems.add(item)
    }

    fun get(position: Int) = cartItems[position]

    fun getAll() = cartItems.toList()

    fun remove(index: Int) = cartItems.removeAt(index)

    fun removeAll() = cartItems.clear()

    fun swap(inicial: Int, final: Int) = Collections.swap(cartItems, inicial, final)
}