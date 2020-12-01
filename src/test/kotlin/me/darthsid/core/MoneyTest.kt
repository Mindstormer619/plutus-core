package me.darthsid.core

import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class MoneyTest {
	@Test
	fun givenDecimalAmountWith5Places_constructor_truncatesTo2Places() {
		val money = Money("180.25789")

		assertEquals(BigDecimal("180.25"), money.toBigDecimal())
	}

	@Test
	fun givenNegativeUpscaleDecimal_constructor_truncatesTo2Places() {
		val money = Money("-180.25789")

		assertEquals(BigDecimal("-180.25"), money.toBigDecimal())
	}
}