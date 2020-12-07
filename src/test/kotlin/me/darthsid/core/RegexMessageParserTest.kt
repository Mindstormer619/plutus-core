package me.darthsid.core

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RegexMessageParserTest {
	private lateinit var regexParser: RegexMessageParser

	private val ccMessage = "A Transaction of Rs.180.25 has been done on your " +
		"Kotak Credit Card No.xx1234 at SELLER on 27-Nov-2020. The " +
		"available Credit Limit in your Card Account is Rs.1234.56."

	private val patternString = """A Transaction of Rs.([0-9\.]+) has been done on your Kotak Credit Card No.xx1234 at (.*) on ([0-9]?[0-9]-[A-Za-z]{3}-[0-9]{4}). The available Credit Limit in your Card Account is Rs.([0-9\.]+)."""
	private val expectedComponents = listOf("180.25", "SELLER", "27-Nov-2020", "1234.56")

	@Before
	fun setUp() {
		regexParser = RegexMessageParser(patternString)
	}

	@Test
	fun `given a message matching the pattern, parse() returns the selected components`() {
		val components = regexParser.parse(ccMessage)

		assertEquals(expectedComponents, components)
	}

	@Test
	fun `given a message that doesn't match the pattern, call to parse() fails`() {
		val exception = assertFailsWith<CouldNotMatchRegexPattern> { regexParser.parse("Non matching message!") }
		println("""Expected exception message:
			|${exception.message}
		""".trimMargin())
	}
}