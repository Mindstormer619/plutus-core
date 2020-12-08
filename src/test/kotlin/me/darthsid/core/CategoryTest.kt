package me.darthsid.core

import org.junit.Test
import kotlin.test.assertEquals

class CategoryTest {
	@Test
	fun `given name of category, constructor saves it`() {
		val categoryName = "Test Category"
		assertEquals(categoryName, Category(name = categoryName).name)
	}
}