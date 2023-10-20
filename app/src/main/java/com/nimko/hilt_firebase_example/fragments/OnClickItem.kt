package com.nimko.hilt_firebase_example.fragments

import com.nimko.hilt_firebase_example.model.ServiceProvider

interface OnClickItem {
    fun onClick(item:Any)
    fun delete(item: Any)
    fun edit(item:Any)
}