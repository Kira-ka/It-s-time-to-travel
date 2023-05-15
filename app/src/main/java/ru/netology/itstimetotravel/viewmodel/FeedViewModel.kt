package ru.netology.itstimetotravel.viewmodel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.netology.itstimetotravel.db.AppDb
import ru.netology.itstimetotravel.dto.Plain
import ru.netology.itstimetotravel.model.FeedModelState
import ru.netology.itstimetotravel.repository.FlightRepository
import ru.netology.itstimetotravel.repository.FlightRepositoryImpl

class FeedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FlightRepository =
        FlightRepositoryImpl(AppDb.getInstance(context = application).plainDao())

    val data: LiveData<List<Plain>> = repository.data
        .asLiveData(Dispatchers.Default)

    private val _dataState = MutableLiveData<FeedModelState>()
    val dataState: LiveData<FeedModelState>
        get() = _dataState

    private val _specificPlain = MutableLiveData(Plain())
    val specificPlain: LiveData<Plain>
        get() = _specificPlain

    init {
        loadPlain()
    }

    fun loadPlain() = viewModelScope.launch {
        try {
            _dataState.value = FeedModelState(loading = true)
            repository.getAll()
            _dataState.value = FeedModelState()
        } catch (e: Exception) {
            _dataState.value = FeedModelState(error = true)
        }
    }

    fun likeById(id: Long) {
        repository.likeById(id)
    }

    fun onSpecificPlain (plain: Plain){
        _specificPlain.value = plain
    }

}
