package br.org.eldorado.millelds.dao

import br.org.eldorado.millelds.model.Product

class ProductDAO {
    companion object {
        private val products = mutableListOf(
            Product(
                name = "Cesta de frutas 1",
                description = "Cesta básica padrão",
                price = "19.00".toBigDecimal()
            ),
            Product(
                name = "Cesta de frutas 2",
                price = "39.00".toBigDecimal()
            ),
            Product(
                name = "Cesta de frutas 3",
                price = "49.00".toBigDecimal()
            ),
            Product(
                name = "Mega Cesta de frutas",
                price = "69.90".toBigDecimal()
            ),
        )
    }

    fun add(product: Product) = products.add(product)

    fun getAll() = products.toList()
}