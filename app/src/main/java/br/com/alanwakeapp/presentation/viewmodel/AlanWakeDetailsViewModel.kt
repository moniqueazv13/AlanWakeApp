package br.com.alanwakeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AlanWakeDetailsViewModel() : ViewModel() {

    private val _uiAction = MutableSharedFlow<UiAction>()
    val uiAction: SharedFlow<UiAction> = _uiAction.asSharedFlow()

     fun goBack() {
         viewModelScope.launch {
             _uiAction.emit(UiAction.GoBack)
         }
     }
}