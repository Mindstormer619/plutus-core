package me.darthsid.core

import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class TransactionRulesetTest {
	lateinit var transactionRuleset: TransactionRuleset
	lateinit var transactionRulesetOrder2: TransactionRuleset

	private val components = listOf("180.25", "SELLER", "27-Nov-2020", "1234.56")
	private val componentsOrder2 = listOf("SELLER", "180.25", "27-Nov-2020", "1234.56")

	private val mockAccount = object: Account {
		override val name: String
			get() = "Mock Account"

	}
	private val currencyCode = "INR"

	@Before
	fun setUp() {
		transactionRuleset = TransactionRuleset(0, 1)
		transactionRulesetOrder2 = TransactionRuleset(1, 0)
	}

	@Test
	fun givenComponentList_createTransaction_createsATransactionWithValidAmount() {
		val transaction = transactionRuleset.createTransaction(components, currencyCode, mockAccount)

		assertEquals(BigDecimal("180.25"), transaction.amount.toBigDecimal())
	}

	@Test
	fun givenComponentListInDifferentOrder_createTransaction_createsATransactionWithValidAmount() {
		val transaction = transactionRulesetOrder2.createTransaction(componentsOrder2, currencyCode, mockAccount)

		assertEquals(BigDecimal("180.25"), transaction.amount.toBigDecimal())
	}

	@Test
	fun givenComponentList_createTransaction_createsATransactionWithValidCounterparty() {
		val transaction = transactionRuleset.createTransaction(components, currencyCode, mockAccount)

		assertEquals("SELLER", transaction.counterparty)
	}

	@Test
	fun testOtherComponents_placeholder() {
		TODO("Not yet implemented")
	}
}