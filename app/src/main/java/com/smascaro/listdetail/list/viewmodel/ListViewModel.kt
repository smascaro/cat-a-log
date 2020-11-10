package com.smascaro.listdetail.list.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.smascaro.listdetail.data.repository.BreedsRepository
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val repository: BreedsRepository
) : ViewModel(),
    LifecycleObserver {
    val breeds = liveData {
        isLoadingData.postValue(true)
        didLoadFail.postValue(false)
        try {
            val items = repository.getBreeds()
            emit(items)
            isLoadingData.postValue(false)
        } catch (e: Exception) {
            isLoadingData.postValue(false)
            didLoadFail.postValue(true)
            failReason.postValue(e.localizedMessage)
        }
    }

    val isLoadingData = MutableLiveData<Boolean>()

    val didLoadFail = MutableLiveData<Boolean>(false)

    val failReason = MutableLiveData<String>("")
}