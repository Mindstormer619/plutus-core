package me.darthsid.core

import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class MoneyMapperTest {
	@Test
	fun `passing in a string with valid money amount maps it to a Money object correctly`() {
		assertEquals(BigDecimal("180.25"), MoneyMapper.mapComponent("180.25").toBigDecimal())
	}

	@Test
	fun `passing in a string with something that isn't money causes the mapper to throw an exception`() {
		val exception = assertFailsWith<MoneyMapper.MoneyMapperException> { MoneyMapper.mapComponent("hello") }
		println("""Expected exception message:
			|${exception.message}
		""".trimMargin())
	}
}