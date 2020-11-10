package com.smascaro.listdetail.di.app

import android.content.Context
import com.smascaro.listdetail.list.view.ListFragment
import com.smascaro.listdetail.list.viewmodel.ListViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, ViewModelModule::class])
@Singleton
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(listFragment: ListFragment)
    fun inject(listViewModel: ListViewModel)
}