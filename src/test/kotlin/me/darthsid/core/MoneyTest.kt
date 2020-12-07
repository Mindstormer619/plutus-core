package me.darthsid.core

import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class MoneyTest {
	@Test
	fun `decimal amounts with 5 dec places get truncated to 2 places`() {
		val money = Money("180.25789")

		assertEquals(BigDecimal("180.25"), money.toBigDecimal())
	}

	@Test
	fun `negative decimals with 5 dec places get truncated to 2 places`() {
		val money = Money("-180.25789")

		assertEquals(BigDecimal("-180.25"), money.toBigDecimal())
	}

	@Test
	fun `given initial amount as "100", Money object stores with 2 decimals`() {
		val money = Money("100")

		assertEquals("Money(value=100.00)", money.toString())
	}

	@Test
	fun `verify that equals() works`() {
		val money1 = Money("200.34")
		val money2 = Money("200.34")

		assert(money1 == money2)
	}
}