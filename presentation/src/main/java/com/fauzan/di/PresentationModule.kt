package com.fauzan.di

import org.koin.dsl.module

val presentationModule = module {
    includes(viewModelModule)
}