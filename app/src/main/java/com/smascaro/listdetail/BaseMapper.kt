package com.smascaro.listdetail

interface BaseMapper<I, O> {
    fun map(input: I): O
}