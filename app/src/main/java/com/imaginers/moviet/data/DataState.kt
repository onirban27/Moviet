package com.imaginers.moviet.data

/**
 * Created by Shafi on 7/7/2020.
 */
data class DataState constructor(
    val data: Any? = null
) {
    companion object {

        fun success(data: Any?): DataState {
            return DataState(data)
        }
    }
}