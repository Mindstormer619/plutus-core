package me.darthsid.core

import org.junit.Assert.*
import org.junit.Test

class AccountTest {
	@Test
	fun `given account name, constructor stores it`() {
		val accountName = "Test Account"
		assertEquals(accountName, Account(accountName).name)
	}
}