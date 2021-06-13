package me.darthsid.core

interface ComponentMapper<out T> {
	fun mapComponent(component: String) : T
}