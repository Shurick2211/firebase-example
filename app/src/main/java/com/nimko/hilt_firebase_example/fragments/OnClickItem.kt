package com.nimko.hilt_firebase_example.fragments

import com.nimko.hilt_firebase_example.model.ServiceProvider

interface OnClickItem<T> {
    fun onClick(item: T)
    fun delete(item: T)
    fun edit(item: T)
}