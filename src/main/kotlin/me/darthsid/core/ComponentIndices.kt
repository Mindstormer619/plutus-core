package me.darthsid.core

data class ComponentIndices (
	val amountIndex: Int,
	val counterpartyIndex: Int? = null,
	val dateOfTransactionIndex: Int? = null,
	val remainingBalanceIndex: Int? = null
)