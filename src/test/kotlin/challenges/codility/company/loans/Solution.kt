package challenges.codility.company.loans

import java.time.OffsetDateTime

data class Loan(val amount: Int, val dateTime: OffsetDateTime)

class LoanRepository(private val store: MutableList<Loan>) {

    private fun OffsetDateTime.minus60Minutes() = minusMinutes(60)

    private fun removeLoans(now: OffsetDateTime): MutableList<Loan> =
        store.also {
            store.removeIf { item -> item.dateTime.isBefore(now.minus60Minutes()) }
        }

    fun processLoan(loan: Loan) {
        removeLoans(OffsetDateTime.now()).add(loan)
    }

    fun getLoanVolume(now: OffsetDateTime): Int =
        removeLoans(now).fold(0) { acc: Int, loan: Loan -> acc + loan.amount }
}

class ProcessLoanService(private val loanRepository: LoanRepository) {
    fun execute(loan: Loan): Unit = loanRepository.processLoan(loan)
}

class GetLoanVolumeService(private val loanRepository: LoanRepository) {
    fun execute(now: OffsetDateTime): Int = loanRepository.getLoanVolume(now)
}

const val PROCESS_LOAN_SERVICE = "processLoanService"
const val GET_LOAN_VOLUMEN_SERVICE = "getLoanVolumeService"

typealias GetLoanVolumeServiceExec = (OffsetDateTime) -> Int
typealias ProcessLoanServiceExec = (Loan) -> Unit

class Solution {

    private fun bootstrapping(): Map<String, Any> {
        val syncList = java.util.Collections.synchronizedList(mutableListOf<Loan>())
        val repository = LoanRepository(syncList)
        val getLoanVolumeService = GetLoanVolumeService(repository)
        val processLoanService = ProcessLoanService(repository)
        val getLoanVolumeServiceExecute: GetLoanVolumeServiceExec =
            { now: OffsetDateTime -> getLoanVolumeService.execute(now) }
        val processLoanServiceExecute: ProcessLoanServiceExec = { loan: Loan -> processLoanService.execute(loan) }
        return mapOf(
            PROCESS_LOAN_SERVICE to processLoanServiceExecute,
            GET_LOAN_VOLUMEN_SERVICE to getLoanVolumeServiceExecute
        )
    }

    fun solution(arr: Array<Pair<Int, Long>>): Int {
        val commandBus = bootstrapping()
        val now = OffsetDateTime.now()
        val processLoanService = commandBus[PROCESS_LOAN_SERVICE] as ProcessLoanServiceExec
        val getLoanVolumeService = commandBus[GET_LOAN_VOLUMEN_SERVICE] as GetLoanVolumeServiceExec
        arr.asSequence()
            .map { pair -> Loan(pair.first, now.minusMinutes(pair.second)) }
            .forEach { loan -> processLoanService(loan) }
        return getLoanVolumeService(now)
    }
}