package me.darthsid.core

object MoneyMapper : ComponentMapper {
	override fun mapComponent(component: String): Money {
		try {
			return Money(component)
		} catch (e: Exception) {
			throw MoneyMapperException(component, e)
		}
	}

	class MoneyMapperException(component: String, override val cause: Throwable?)
		: Exception(
			"""Component {$component} cannot be converted to Money"""
		)
}