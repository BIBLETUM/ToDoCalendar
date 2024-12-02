package com.example.domain.use_cases

internal class SelectDateUseCase(
    private val selectedDateInnerFlow: SelectedDateInnerFlow
) : ISelectDateUseCase {

    override fun invoke(date: Long) {
        selectedDateInnerFlow.selectDate(date)
    }

}

interface ISelectDateUseCase {
    operator fun invoke(date: Long)
}