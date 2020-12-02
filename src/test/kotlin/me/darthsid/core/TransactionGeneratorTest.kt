package me.darthsid.core

import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TransactionGeneratorTest {
	private lateinit var transactionGenerator: TransactionGenerator
	private lateinit var transactionGeneratorOrder2: TransactionGenerator
	private lateinit var transactionGeneratorOnlyAmount: TransactionGenerator

	private val components = listOf("180.25", "SELLER", "27-Nov-2020", "1234.56")
	private val componentsOrder2 = listOf("SELLER", "180.25", "27-Nov-2020", "1234.56")
	private val listWithOnlyAmount = listOf("180.25")

	private val mockAccount = object: Account {
		override val name: String
			get() = "Mock Account"

	}
	private val currencyCode = "INR"

	@Before
	fun setUp() {
		val orderedIndices = ComponentIndices(0, 1, 2, 3)
		transactionGenerator = TransactionGenerator(orderedIndices, currencyCode, mockAccount)
		val outOfOrderIndices = ComponentIndices(1, 0, 2, 3)
		transactionGeneratorOrder2 = TransactionGenerator(outOfOrderIndices, currencyCode, mockAccount)
		transactionGeneratorOnlyAmount = TransactionGenerator(ComponentIndices(0), currencyCode, mockAccount)
	}

	@Test
	fun givenComponentList_createTransaction_createsATransactionWithValidAmount() {
		val transaction = transactionGenerator.createTransaction(components)

		assertEquals(BigDecimal("180.25"), transaction.amount.toBigDecimal())
	}

	@Test
	fun givenComponentListInDifferentOrder_createTransaction_createsATransactionWithValidAmount() {
		val transaction = transactionGeneratorOrder2.createTransaction(componentsOrder2)

		assertEquals(BigDecimal("180.25"), transaction.amount.toBigDecimal())
	}

	@Test
	fun givenComponentList_createTransaction_createsATransactionWithValidCounterparty() {
		val transaction = transactionGenerator.createTransaction(components)

		assertEquals("SELLER", transaction.counterparty)
	}

	@Test
	fun givenComponentList_createTransaction_createsATransactionWithValidDateOfTransaction() {
		val transaction = transactionGenerator.createTransaction(components)

		assertEquals("27-Nov-2020", transaction.dateOfTransaction)
	}

	@Test
	fun givenComponentList_createTransaction_createsATransactionWithValidRemainingBalance() {
		val transaction = transactionGenerator.createTransaction(components)

		assertEquals(BigDecimal("1234.56"), transaction.remainingBalance?.toBigDecimal())
	}

	@Test
	fun givenComponentListWithOnlyAmount_createTransaction_stillWorks() {
		val transaction = transactionGeneratorOnlyAmount.createTransaction(listWithOnlyAmount)

		assertEquals(BigDecimal("180.25"), transaction.amount.toBigDecimal())
		assertEquals(null, transaction.counterparty)
	}

	@Test
	fun givenInvalidCurrencyCode_constructor_fails() {
		assertFailsWith<InvalidCurrency> { TransactionGenerator(ComponentIndices(0), "DDD", mockAccount) }
	}
}