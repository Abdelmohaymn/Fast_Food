package com.example.fastfood.domain.mapper

abstract class BaseMapper<I,O> {
    abstract fun map(input:I):O
}