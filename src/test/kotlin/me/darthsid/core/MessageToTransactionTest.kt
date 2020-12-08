package me.darthsid.core

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class MessageToTransactionTest {
	private lateinit var messageToTransaction: MessageToTransaction

	@MockK lateinit var messageParser: MessageParser
	@MockK lateinit var transactionGenerator: TransactionGenerator
	@MockK lateinit var transaction: Transaction

	private val components = listOf("180.25", "SELLER", "Nov-27", "200.55")

	@Before
	fun setUp() {
		MockKAnnotations.init(this)
		every { messageParser.parse(any()) } returns components
		every { transactionGenerator.createTransaction(any()) } returns transaction
		every { transaction.amount } returns Money("180.25")
		messageToTransaction = MessageToTransaction(messageParser, transactionGenerator)
	}

	@Test
	fun `given a message, invocation results in a transaction`() {
		val message = "Transaction of INR 180.25 done at SELLER on Nov-27. Remaining balance is 200.55."
		val transaction: Transaction = messageToTransaction(message)
		assertEquals(Money("180.25"), transaction.amount)
		verify { transactionGenerator.createTransaction(components) }
	}
}