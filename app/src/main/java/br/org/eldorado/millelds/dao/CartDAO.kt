package br.org.eldorado.millelds.dao

import br.org.eldorado.millelds.model.CartItem
import br.org.eldorado.millelds.model.Product
import java.util.*

class CartDAO {
    companion object {
        private val cartItems = mutableListOf<CartItem>()
    }

    fun add(product: Product) = cartItems.add(CartItem(product))

    fun add(item: CartItem) = cartItems.add(item)

    fun get(position: Int) = cartItems[position]

    fun getAll() = cartItems.toList()

    fun remove(index: Int) = cartItems.removeAt(index)

    fun swap(inicial: Int, final: Int) = Collections.swap(cartItems, inicial, final)
}