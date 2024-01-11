package com.test.weatheapp.domain.usecase


//Base class for use cases
interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(params: Parameter): Result
}
