package com.clp.models

import java.time.LocalDateTime

data class PurchaseDispute(
    val id: Long? = null, // ID da disputa, pode ser nulo se ainda não foi inserido no banco
    val creditCardId: Long, // ID do cartão de crédito relacionado
    val transactionId: Long, // ID da transação associada
    val reason: String, // Motivo da disputa
    val status: String, // Status da disputa (ex: "aberta", "fechada")
    val createdAt: LocalDateTime = LocalDateTime.now(), // Data de criação, padrão é agora
    val updatedAt: LocalDateTime = LocalDateTime.now() // Data de atualização, padrão é agora
)
