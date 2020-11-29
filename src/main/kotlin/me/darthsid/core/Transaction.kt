package me.darthsid.core

import java.math.BigDecimal
import java.util.*

data class Transaction (
	var amount: BigDecimal, //TODO use an actual Money class
	var currency: Currency,
	var account: String, //TODO use an actual Account class
	var remainingBalance: BigDecimal?,
	var counterparty: String?,
	var dateOfTransaction: String?,
	var timestamp: Date
)