package com.nimko.hilt_firebase_example.fragments

interface OnClickItem <T>{
    fun onClick(item:T)
    fun delete(item: Any)
}