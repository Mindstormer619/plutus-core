package me.darthsid.core

import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class MoneyMapperTest {
	@Test
	fun givenValidMoneyAmountAsComponent_getMoney_convertsCorrectly() {
		assertEquals(BigDecimal("180.25"), MoneyMapper.mapComponent("180.25").toBigDecimal())
	}

	@Test
	fun givenInvalidComponent_mapComponent_throwsException() {
		val exception = assertFailsWith<MoneyMapper.MoneyMapperException> { MoneyMapper.mapComponent("hello") }
		println("""Expected exception message:
			|${exception.message}
		""".trimMargin())
	}
}