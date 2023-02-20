package com.davidsantiagoiriarte.domain.usecases

interface UseCase<P, R> {
    fun execute(p: P): R
}
