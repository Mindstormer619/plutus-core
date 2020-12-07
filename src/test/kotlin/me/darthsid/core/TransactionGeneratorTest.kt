package me.darthsid.core

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class TransactionGeneratorTest {
	private lateinit var transactionGenerator: TransactionGenerator

	private val components = listOf("180.25", "SELLER", "27-Nov-2020", "1234.56")

	@MockK
	private lateinit var mockAccount: Account
	private val currencyCode = "INR"

	@Before
	fun setUp() {
		MockKAnnotations.init(this)
		every { mockAccount.name } returns "Test Account Name"

		val orderedIndices = ComponentIndices(0, 1, 2, 3)
		transactionGenerator = TransactionGenerator(orderedIndices, currencyCode, mockAccount)
	}

	@Test
	fun `given a component list, createTransaction works with expected amount in the transaction`() {
		val transaction = transactionGenerator.createTransaction(components)

		assertEquals(BigDecimal("180.25"), transaction.amount.toBigDecimal())
	}

	@Test
	fun `different orderings of the components work when pre-specified in the transaction generator`() {
		val componentsOrder2 = listOf("SELLER", "180.25", "27-Nov-2020", "1234.56")
		val outOfOrderIndices = ComponentIndices(1, 0, 2, 3)
		val transactionGeneratorOrder2 = TransactionGenerator(outOfOrderIndices, currencyCode, mockAccount)

		val transaction = transactionGeneratorOrder2.createTransaction(componentsOrder2)

		assertEquals(BigDecimal("180.25"), transaction.amount.toBigDecimal())
	}

	@Test
	fun `createTransaction inserts the counterparty correctly`() {
		val transaction = transactionGenerator.createTransaction(components)

		assertEquals("SELLER", transaction.counterparty)
	}

	@Test
	fun `createTransaction inserts the date of transaction correctly`() {
		val transaction = transactionGenerator.createTransaction(components)

		assertEquals("27-Nov-2020", transaction.dateOfTransaction)
	}

	@Test
	fun `createTransaction inserts the remainingBalance amount correctly`() {
		val transaction = transactionGenerator.createTransaction(components)

		assertEquals(BigDecimal("1234.56"), transaction.remainingBalance?.toBigDecimal())
	}

	@Test
	fun `createTransaction can skip all the non-essential arguments, except amount`() {
		val listWithOnlyAmount = listOf("180.25")
		val transactionGeneratorOnlyAmount = TransactionGenerator(ComponentIndices(0), currencyCode, mockAccount)

		val transaction = transactionGeneratorOnlyAmount.createTransaction(listWithOnlyAmount)

		assertEquals(BigDecimal("180.25"), transaction.amount.toBigDecimal())
		assertEquals("", transaction.counterparty)
		assertEquals("", transaction.dateOfTransaction)
		assertEquals(null, transaction.remainingBalance)
	}

	@Test
	fun `constructor for TransactionGenerator fails when currency code is not a real currency`() {
		assertFailsWith<InvalidCurrency> { TransactionGenerator(ComponentIndices(0), "DDD", mockAccount) }
	}

	@Test
	fun `given predefined currency, check that primary constructor works`() {
		val totallyWorkingCurrency = Currency.getInstance("USD")

		assertNotNull(TransactionGenerator(ComponentIndices(0), totallyWorkingCurrency, mockAccount))
	}
}