package me.darthsid.core

import java.util.*

class TransactionGenerator constructor(
	private val indices: ComponentIndices,
	private val currency: Currency,
	private val account: Account
) {
	@Throws(InvalidCurrency::class) constructor(indices: ComponentIndices, currencyCode: String, account: Account)
	: this(
		indices,
		try { Currency.getInstance(currencyCode) } catch (e: Exception) { throw InvalidCurrency(currencyCode, e) },
		account
	)

	fun createTransaction(components: List<String>): Transaction {
		return Transaction(
			amount = MoneyMapper.mapComponent(components[indices.amountIndex]),
			currency = currency,
			account = account,
			timestamp = Date(),
			counterparty = components.getOrNull(indices.counterpartyIndex),
			dateOfTransaction = components.getOrNull(indices.dateOfTransactionIndex),
			remainingBalance = components.getOrNull(indices.remainingBalanceIndex)?.let(MoneyMapper::mapComponent)
		)
	}

	private fun List<String>.getOrNull(index: Int?) =
		if (index != null) this[index] else null

}

class InvalidCurrency(currencyCode: String, cause: Throwable?)
	: Throwable("$currencyCode is not a valid currency code!", cause)