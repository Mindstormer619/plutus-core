package me.darthsid.core

import java.math.BigDecimal
import java.math.RoundingMode

class Money(value: String) {
	private val underlyingDecimal: BigDecimal = BigDecimal(value).setScale(2, RoundingMode.DOWN)

	fun toBigDecimal() : BigDecimal {
		return underlyingDecimal
	}

	override fun toString(): String {
		return "Money(value=$underlyingDecimal)"
	}
}