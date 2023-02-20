package com.davidsantiagoiriarte.domain.usecases

interface SuspendUseCase<P, R> {
    suspend fun execute(p: P): R
}
