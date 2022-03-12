package br.org.eldorado.millelds.application

import android.app.Application
import br.org.eldorado.millelds.database.dao.ProductDAO
import br.org.eldorado.millelds.di.appModule
import br.org.eldorado.millelds.model.Product
import org.koin.android.BuildConfig
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MilleldsApplication : Application() {

    val productDao: ProductDAO by inject()

    override fun onCreate() {
        super.onCreate()
        setupKoin()

        productDao.add(
            Product(
                id = 10,
                name = "Cesta de frutas 1",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Donec ultrices tincidunt arcu non sodales neque. Pulvinar elementum integer enim neque volutpat ac. Vitae tempus quam pellentesque nec nam. Habitant morbi tristique senectus et netus et malesuada. Semper quis lectus nulla at. Sed enim ut sem viverra. Sed lectus vestibulum mattis ullamcorper velit sed ullamcorper morbi tincidunt. Faucibus vitae aliquet nec ullamcorper sit amet risus nullam. Rhoncus urna neque viverra justo nec ultrices dui. Amet massa vitae tortor condimentum lacinia quis vel eros donec. Diam quis enim lobortis scelerisque fermentum dui faucibus in ornare. Eget velit aliquet sagittis id consectetur. Neque vitae tempus quam pellentesque nec nam aliquam. Scelerisque fermentum dui faucibus in ornare quam viverra. Pellentesque dignissim enim sit amet venenatis urna cursus eget nunc. Mattis nunc sed blandit libero volutpat sed cras ornare arcu. Est ante in nibh mauris. Morbi enim nunc faucibus a. Ut faucibus pulvinar elementum integer enim",
                priceString = "19.00"
            ),
            Product(
                id = 11,
                name = "Tucumã",
                description = "Porção de tucumã com casca.\n" +
                        "Aproximadamente 500 gramas",
                priceString = "16.00",
                imageUrl = "https://cdn.emtempo.com.br/img/inline/200000/680/468320848712f349ea0fez_00208579_8_202105141127.webp"
            ),
            Product(
                id = 12,
                name = "Frutas vermelhas",
                description = "Proção de frutas vermelhas selecionadas. Excelentes para preparar aquela vitaminada ou receita especial incrível.",
                priceString = "39.00",
                imageUrl = "https://conteudo.imguol.com.br/c/entretenimento/80/2017/10/26/frutas-vermelhas-1509051642985_v2_600x600.jpg"
            ),
            Product(
                id = 13,
                name = "Frutas cítricas",
                description = "Cesta com 3 varieadades de frutas cítircas. Ajudam na desintoxicação do organismo e estimulam o sistema imunológico",
                priceString = "12.90",
                imageUrl = "https://static1.conquistesuavida.com.br/articles//2/72/22/@/23017-as-frutas-citricas-sao-caracterizadas-pe-article_gallery-3.jpg"
            )
        )
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MilleldsApplication)
            modules(appModule)
        }
    }
}