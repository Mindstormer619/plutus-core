package me.darthsid.core

import java.util.*

data class Transaction (
	val amount: Money,
	val currency: Currency,
	val isCredit: Boolean = false,
	val account: Account,
	val remainingBalance: Money? = null,
	val counterparty: String = "",
	val dateOfTransaction: String = "",
	val timestamp: Date = Date(),
	val comment: String = "",
	val category: Category? = null
)