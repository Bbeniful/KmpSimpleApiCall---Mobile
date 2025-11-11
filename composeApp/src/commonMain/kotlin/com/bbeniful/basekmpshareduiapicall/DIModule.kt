package com.bbeniful.basekmpshareduiapicall

import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module


val modules = module {
    single<HttpClient> {
        createClient()
    }

    singleOf(::DatasourceImpl) bind Datasource::class
    singleOf(::RepositoryImpl) bind Repository::class

    viewModelOf(::TodosViewModel)
}

fun initKoin(config: KoinAppDeclaration? = null) = startKoin {
    config?.invoke(this)
    modules(modules)
}