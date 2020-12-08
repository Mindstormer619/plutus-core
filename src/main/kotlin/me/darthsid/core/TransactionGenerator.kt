package me.darthsid.core

import java.util.*

class TransactionGenerator constructor(
	private val indices: ComponentIndices,
	private val currency: Currency,
	private val account: Account,
	private val isCredit: Boolean = false
) {
	@Throws(InvalidCurrency::class) constructor(indices: ComponentIndices, currencyCode: String, account: Account, isCredit: Boolean = false)
	: this(
		indices,
		try { Currency.getInstance(currencyCode) } catch (e: Exception) { throw InvalidCurrency(currencyCode, e) },
		account,
		isCredit
	)

	fun createTransaction(components: List<String>, comment: String = "", category: Category? = null): Transaction {
		return Transaction(
			amount = MoneyMapper.mapComponent(components[indices.amountIndex]),
			currency = currency,
			account = account,
			timestamp = Date(),
			counterparty = components.getOrNull(indices.counterpartyIndex) ?: "",
			dateOfTransaction = components.getOrNull(indices.dateOfTransactionIndex) ?: "",
			remainingBalance = components.getOrNull(indices.remainingBalanceIndex)?.let(MoneyMapper::mapComponent),
			comment = comment,
			isCredit = isCredit,
			category = category
		)
	}

	private fun List<String>.getOrNull(index: Int?) =
		if (index != null) this[index] else null

}

class InvalidCurrency(currencyCode: String, cause: Throwable?)
	: Throwable("$currencyCode is not a valid currency code!", cause)