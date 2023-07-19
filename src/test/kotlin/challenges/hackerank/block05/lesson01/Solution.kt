package challenges.hackerank.block05.lesson01

import java.time.LocalDateTime

data class Loan(val amount: Int, val dateTime: LocalDateTime)

class Solution {
    fun solution(input: Array<String>) = emptyArray<String>()
}

class LoanProvider(private val store: MutableList<Loan>) {

    private fun LocalDateTime.less60() = minusMinutes(60)

    private fun removeLoans(now: LocalDateTime): MutableList<Loan> =
        store.also {
            store.removeIf { item -> item.dateTime.isBefore(now.less60()) }
        }

    fun processLoan(loan: Loan): LoanProvider = this.also { removeLoans(LocalDateTime.now()).add(loan) }

    fun getLoanVolume(now: LocalDateTime): Int =
        removeLoans(now).fold(0) { acc: Int, loan: Loan -> acc + loan.amount }
}

fun main(args: Array<String>) {
    val now = LocalDateTime.now()
    val loan1 = Loan(5, now.minusMinutes(10))
    val loan2 = Loan(10, now.minusMinutes(5))
    val loan3 = Loan(20, now.minusMinutes(65))
    val loan4 = Loan(30, now.minusMinutes(65))

    val syncList = java.util.Collections.synchronizedList(mutableListOf<Loan>())
    val provider = LoanProvider(syncList)

    provider.processLoan(loan1)
        .processLoan(loan2)
        .processLoan(loan3)
        .processLoan(loan4)

    println(provider.getLoanVolume(now))
}