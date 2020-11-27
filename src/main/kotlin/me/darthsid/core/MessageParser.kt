package me.darthsid.core

interface MessageParser {
	fun parse(message: String): List<String>
	fun isMatching(message: String): Boolean
}