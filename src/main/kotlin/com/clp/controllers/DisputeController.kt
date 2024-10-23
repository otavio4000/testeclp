package com.clp.controllers

import com.clp.services.DisputeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/disputes")
class DisputeController(private val disputeService: DisputeService) {

    @PostMapping("/create")
    fun createDispute(
        @RequestParam creditCardId: Long,
        @RequestParam transactionId: Long,
        @RequestParam reason: String
    ) = disputeService.createDispute(creditCardId, transactionId, reason)

    @GetMapping("/{creditCardId}")
    fun getDisputes(@PathVariable creditCardId: Long) =
        disputeService.getDisputesForCard(creditCardId)

    @PutMapping("/{disputeId}/resolve")
    fun resolveDispute(
        @PathVariable disputeId: Long,
        @RequestParam status: String
    ) = disputeService.resolveDispute(disputeId, status)
}
