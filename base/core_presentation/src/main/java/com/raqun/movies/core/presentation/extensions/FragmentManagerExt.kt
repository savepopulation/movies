package com.raqun.movies.core.presentation.extensions

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.transact(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}