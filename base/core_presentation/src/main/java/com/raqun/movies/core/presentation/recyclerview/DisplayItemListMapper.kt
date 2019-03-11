package com.raqun.movies.core.presentation.recyclerview

interface DisplayItemListMapper<T> {
    fun map(items: List<T>): List<DisplayItem>
}