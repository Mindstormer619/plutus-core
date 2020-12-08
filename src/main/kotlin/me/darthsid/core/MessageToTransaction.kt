package me.darthsid.core

class MessageToTransaction(private val messageParser: MessageParser, private val transactionGenerator: TransactionGenerator) {
	operator fun invoke(message: String) : Transaction {
		val components = messageParser.parse(message)
		return transactionGenerator.createTransaction(components)
	}
}