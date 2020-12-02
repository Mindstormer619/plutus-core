package me.darthsid.core

import java.util.*

data class Transaction (
	var amount: Money,
	var currency: Currency,
	val isCredit: Boolean = false,
	var account: Account,
	var remainingBalance: Money? = null,
	var counterparty: String? = null,
	var dateOfTransaction: String? = null,
	var timestamp: Date = Date()
)