package br.com.alanwakeapp.di

import br.com.alanwakeapp.data.GameRepositoryImpl
import br.com.alanwakeapp.data.LocalDataSource
import br.com.alanwakeapp.domain.repository.GameRepository
import br.com.alanwakeapp.domain.usecase.GetGameInfoUseCase
import br.com.alanwakeapp.presentation.viewmodel.AlanWakeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { AlanWakeViewModel(get()) }
}

val domainModule = module {
    factory { GetGameInfoUseCase(get()) }
}

val dataModule = module {
    single<GameRepository> { GameRepositoryImpl(get()) }
    single { LocalDataSource(androidContext().assets) }
}