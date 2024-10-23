CREATE TYPE "status_enum" AS ENUM (
	'Ativo',
	'Bloqueado',
	'Cancelado'
);
CREATE TABLE "CartaoDeCredito" (
	"id" INTEGER NOT NULL UNIQUE,
	"numero_cartao" VARCHAR NOT NULL UNIQUE,
	"cvv" VARCHAR NOT NULL,
	"data_validade" DATE NOT NULL,
	"limite_disponivel" DOUBLE PRECISION NOT NULL,
	"status" STATUS_ENUM NOT NULL,
	"limite_total" DOUBLE PRECISION NOT NULL,
	"id_usuario" INTEGER NOT NULL UNIQUE,
	"id_fatura" INTEGER,
	PRIMARY KEY("id")
);


CREATE TABLE "Usuário_Temp" (
	"id" INTEGER NOT NULL UNIQUE,
	PRIMARY KEY("id")
);


CREATE TABLE "Fatura" (
	"id" INTEGER NOT NULL UNIQUE,
	"valor" DOUBLE PRECISION NOT NULL,
	"data_de_vencimento" DATE NOT NULL,
	"id_compras" INTEGER NOT NULL,
	"data_de_pagamento" DATE,
	PRIMARY KEY("id")
);


CREATE TABLE "Compras_Temp" (
	"id" INTEGER NOT NULL UNIQUE,
	"valor" DOUBLE PRECISION NOT NULL,
	"descricao" VARCHAR NOT NULL,
	"data" DATE NOT NULL,
	"disputado" BOOLEAN,
	PRIMARY KEY("id")
);


CREATE TABLE purchase_disputes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    credit_card_id BIGINT NOT NULL,
    transaction_id BIGINT NOT NULL,
    reason VARCHAR(255) NOT NULL,
    status VARCHAR(50) DEFAULT 'open',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (credit_card_id) REFERENCES credit_cards(id),
    FOREIGN KEY (transaction_id) REFERENCES transactions(id)
);


ALTER TABLE "CartaoDeCredito"
ADD FOREIGN KEY("id_usuario") REFERENCES "Usuário_Temp"("id")
ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE "Fatura"
ADD FOREIGN KEY("id_compras") REFERENCES "Compras_Temp"("id")
ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE "CartaoDeCredito"
ADD FOREIGN KEY("id_fatura") REFERENCES "Fatura"("id")
ON UPDATE NO ACTION ON DELETE NO ACTION;