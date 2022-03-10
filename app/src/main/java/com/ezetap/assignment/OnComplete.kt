package com.ezetap.assignment

interface OnComplete<T> {
    fun onResponse(model: T)
}