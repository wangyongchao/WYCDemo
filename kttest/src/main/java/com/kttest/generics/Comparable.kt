package com.kttest.generics

interface Comparable<in T> {
    operator fun compareTo(other: T): Int

}