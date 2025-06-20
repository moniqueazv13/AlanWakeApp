package br.com.alanwakeapp.presentation.viewmodel

sealed class UiAction {
    data object GoBack : UiAction()
}