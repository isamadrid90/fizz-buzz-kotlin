package com.isamadrid90.fizzbuzz

class Main(private val translator: FizzBuzzTranslator, private val printer: Printer) {
    fun execute(first: Int, last: Int) {
        translator.execute(first, last).forEach {
            printer.execute(it)
        }
    }
}
