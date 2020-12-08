package me.darthsid.core

import java.util.*

data class Transaction (
	val amount: Money,
	val currency: Currency,
	val isCredit: Boolean,
	val account: Account,
	val remainingBalance: Money?,
	val counterparty: String,
	val dateOfTransaction: String,
	val timestamp: Date,
	val comment: String,
	val category: Category?
)