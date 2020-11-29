package me.darthsid.core

interface MessageParser {
	fun parse(message: String): List<String>
}