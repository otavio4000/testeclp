package com.clp.models

import java.time.LocalDateTime
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

// Definição da tabela de disputas
object PurchaseDisputesTable : IdTable<Long>("purchase_disputes") { 
    val id = long("id").autoIncrement().primaryKey() // Define a chave primária
    val creditCardId = long("credit_card_id")
    val transactionId = long("transaction_id")
    val reason = varchar("reason", 255)
    val status = varchar("status", 50).default("open")
    val createdAt = datetime("created_at") // Correção aqui: Use `datetime`
    val updatedAt = datetime("updated_at") // Correção aqui: Use `datetime`
}

// Repositório para gerenciar disputas
class PurchaseDisputeRepository {

    // Método para criar uma nova disputa
    fun createDispute(dispute: PurchaseDispute): PurchaseDispute {
        var insertedId: Long? = null
        transaction {
            insertedId = PurchaseDisputesTable.insertAndGetId {
                it[creditCardId] = dispute.creditCardId
                it[transactionId] = dispute.transactionId
                it[reason] = dispute.reason
                it[status] = dispute.status
                it[createdAt] = LocalDateTime.now() // Adiciona a data de criação
                it[updatedAt] = LocalDateTime.now() // Adiciona a data de atualização
            }.value
        }
        return dispute.copy(id = insertedId) // Retorna a disputa com o ID inserido
    }

    // Método para encontrar disputas por ID do cartão de crédito
    fun findDisputesByCreditCardId(creditCardId: Long): List<PurchaseDispute> {
        return transaction {
            PurchaseDisputesTable.select { PurchaseDisputesTable.creditCardId eq creditCardId }
                .map { rowToDispute(it) }
        }
    }

    // Método para atualizar o status de uma disputa
    fun updateDisputeStatus(id: Long, newStatus: String) {
        transaction {
            PurchaseDisputesTable.update({ PurchaseDisputesTable.id eq id }) {
                it[status] = newStatus
                it[updatedAt] = LocalDateTime.now() // Atualiza a data de modificação
            }
        }
    }

    // Método para converter uma linha do banco de dados em um objeto PurchaseDispute
    private fun rowToDispute(row: ResultRow): PurchaseDispute {
        return PurchaseDispute(
            id = row[PurchaseDisputesTable.id],
            creditCardId = row[PurchaseDisputesTable.creditCardId],
            transactionId = row[PurchaseDisputesTable.transactionId],
            reason = row[PurchaseDisputesTable.reason],
            status = row[PurchaseDisputesTable.status],
            createdAt = row[PurchaseDisputesTable.createdAt],
            updatedAt = row[PurchaseDisputesTable.updatedAt]
        )
    }
}
