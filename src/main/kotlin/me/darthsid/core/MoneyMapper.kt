package me.darthsid.core

import java.math.BigDecimal

class MoneyMapper : ComponentMapper {
	override fun mapComponent(component: String): BigDecimal {
		try {
			return BigDecimal(component)
		} catch (e: Exception) {
			throw MoneyMapperException(component, e)
		}
	}

	class MoneyMapperException(component: String, override val cause: Throwable?)
		: Exception(
			"""Component {$component} cannot be converted to Money"""
		)
}