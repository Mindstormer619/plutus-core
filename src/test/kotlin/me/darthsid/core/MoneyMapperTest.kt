package me.darthsid.core

import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class MoneyMapperTest {
	private lateinit var moneyMapper: MoneyMapper

	@Before
	fun setUp() {
		moneyMapper = MoneyMapper()
	}

	@Test
	fun givenValidMoneyAmountAsComponent_getMoney_convertsCorrectly() {
		assertEquals(BigDecimal("180.25"), moneyMapper.mapComponent("180.25"))
	}

	@Test
	fun givenInvalidComponent_mapComponent_throwsException() {
		val exception = assertFailsWith<MoneyMapper.MoneyMapperException> { moneyMapper.mapComponent("hello") }
		println("""Expected exception message:
			|${exception.message}
		""".trimMargin())
	}
}