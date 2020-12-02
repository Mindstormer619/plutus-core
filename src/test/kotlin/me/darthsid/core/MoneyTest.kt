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

	@Test
	fun given100Money_constructor_keeps2DecimalPlaces() {
		val money = Money("100")

		assertEquals("Money(value=100.00)", money.toString())
	}

	@Test
	fun givenTwoMoneyInstancesWithSameAmount_equals_isTrue() {
		val money1 = Money("200.34")
		val money2 = Money("200.34")

		assert(money1 == money2)
	}
}