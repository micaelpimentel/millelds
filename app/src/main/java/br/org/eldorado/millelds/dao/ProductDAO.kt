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
                name = "Tucumã",
                description = "Porção de tucumã com casca.\n" +
                        "Aproximadamente 500 gramas",
                price = "16.00".toBigDecimal(),
                imageUrl = "https://cdn.emtempo.com.br/img/inline/200000/680/468320848712f349ea0fez_00208579_8_202105141127.webp"
            ),
            Product(
                name = "Frutas vermelhas",
                description = "Proção de frutas vermelhas selecionadas. Excelentes para preparar aquela vitaminada ou receita especial incrível.",
                price = "39.00".toBigDecimal(),
                imageUrl = "https://conteudo.imguol.com.br/c/entretenimento/80/2017/10/26/frutas-vermelhas-1509051642985_v2_600x600.jpg"
            ),
            Product(
                name = "Frutas cítricas",
                description = "Cesta com 3 varieadades de frutas cítircas. Ajudam na desintoxicação do organismo e estimulam o sistema imunológico",
                price = "12.90".toBigDecimal(),
                imageUrl = "https://static1.conquistesuavida.com.br/articles//2/72/22/@/23017-as-frutas-citricas-sao-caracterizadas-pe-article_gallery-3.jpg"
            ),
        )
    }

    fun add(product: Product) = products.add(product)

    fun get(position: Int) = products[position]

    fun getAll() = products.toList()
}