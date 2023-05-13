package com.nagl.test_cats_task.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nagl.test_cats_task.data.model.domain.Cat
import com.nagl.test_cats_task.data.repository.CatsRepositoryImpl
import com.nagl.test_cats_task.utils.Result
import kotlinx.coroutines.launch


class CatsViewModelFactory(private val repository: CatsRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatsViewModel::class.java)) {
            return CatsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

// TODO: UI states: loading, error, etc.
class CatsViewModel(private val repository: CatsRepositoryImpl) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val adapterCatList = mutableListOf<Cat>()

    private val _catsList = MutableLiveData<List<Cat>?>()
    val catsList: LiveData<List<Cat>?> = _catsList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadCats(page: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            when (val result = repository.getCatsImage(page)) {
                is Result.Success -> {
                    _isLoading.value = false
                    _catsList.value = result.data
//                    if (result.data != null) {
//                        _catsList.value = result.data
//                    } else {
//                        // TODO: make toast
//                    }
                }
                else -> {} // TODO
            }
        }
    }

}