package com.smascaro.listdetail

sealed class Result<out T> {
    companion object {
        fun <T> success(data: T?): Ok<T> {
            return Ok(data)
        }

        fun <T> loading(data: T?): Loading<T> {
            return Loading(data)
        }

        fun <T> error(data: T?, error: Throwable): Error<T> {
            return Result.Error(data, error)
        }
    }

    class Ok<T>(val data: T?) : Result<T>()
    class Loading<T>(val data: T?) : Result<T>()
    class Error<T>(val data: T?, val error: Throwable) : Result<T>()
}