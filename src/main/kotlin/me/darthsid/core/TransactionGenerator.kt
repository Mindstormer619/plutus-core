package me.darthsid.core

import java.lang.IllegalArgumentException
import java.util.*
import kotlin.jvm.Throws

class TransactionGenerator
@Throws(InvalidCurrency::class) constructor(
	private val indices: ComponentIndices,
	currencyCode: String,
	private val account: Account
) {
	private val currency: Currency = try { Currency.getInstance(currencyCode) }
		catch (e: IllegalArgumentException) { throw InvalidCurrency(currencyCode, e) }

	fun createTransaction(components: List<String>): Transaction {
		return Transaction(
			amount = MoneyMapper.mapComponent(components[indices.amountIndex]),
			currency = currency,
			account = account,
			timestamp = Date(),
			counterparty = getComponentOrNull(components, indices.counterpartyIndex),
			dateOfTransaction = getComponentOrNull(components, indices.dateOfTransactionIndex),
			remainingBalance = getComponentOrNull(components, indices.remainingBalanceIndex)?.let(MoneyMapper::mapComponent)
		)
	}

	private fun getComponentOrNull(components: List<String>, index: Int?) =
		if (index != null) components[index] else null

}

class InvalidCurrency(currencyCode: String, cause: Throwable?)
	: Throwable("$currencyCode is not a valid currency code!", cause)