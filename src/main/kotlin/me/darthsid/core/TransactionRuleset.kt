package me.darthsid.core

import java.util.*

/**
 * Defines a set of rules to convert a list of components (extracted from the message)
 * into a [Transaction].
 */
class TransactionRuleset (private val amountIndex: Int, private val counterpartyIndex: Int) {

	fun createTransaction(components: List<String>, currencyCode: String, account: Account): Transaction {
		return Transaction(
			amount = MoneyMapper.mapComponent(components[amountIndex]),
			currency = Currency.getInstance(currencyCode),
			account = account,
			timestamp = Date(),
			counterparty = components[counterpartyIndex]
		)
	}

}