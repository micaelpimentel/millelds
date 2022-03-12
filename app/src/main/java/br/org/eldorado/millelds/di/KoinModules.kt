package br.org.eldorado.millelds.di

import androidx.room.Room
import br.org.eldorado.millelds.database.MainDataBase
import br.org.eldorado.millelds.database.dao.CartDAO
import br.org.eldorado.millelds.database.dao.OrderDAO
import br.org.eldorado.millelds.database.dao.ProductDAO
import org.koin.dsl.module

val appModule = module {
    single<MainDataBase> {
        Room.databaseBuilder(get(), MainDataBase::class.java, MainDataBase.DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    single<ProductDAO> {
        get<MainDataBase>().getProductDao()
    }
    single<CartDAO> {
        CartDAO()
    }
    single<OrderDAO> {
        OrderDAO()
    }
}