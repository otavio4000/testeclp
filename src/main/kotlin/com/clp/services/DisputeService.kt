package com.clp.services

import com.clp.models.PurchaseDispute
import com.clp.models.PurchaseDisputeRepository

class DisputeService(private val disputeRepository: PurchaseDisputeRepository) {

    fun createDispute(creditCardId: Long, transactionId: Long, reason: String): PurchaseDispute {
        val dispute = PurchaseDispute(
            creditCardId = creditCardId,
            transactionId = transactionId,
            reason = reason,
            status = "open"
        )
        return disputeRepository.createDispute(dispute)
    }

    fun getDisputesForCard(creditCardId: Long): List<PurchaseDispute> {
        return disputeRepository.findDisputesByCreditCardId(creditCardId)
    }

    fun resolveDispute(disputeId: Long, status: String) {
        disputeRepository.updateDisputeStatus(disputeId, status)
    }
}
