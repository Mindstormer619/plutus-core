package me.darthsid.core

class RegexMessageParser (pattern: String) : MessageParser {
	private val regex: Regex = Regex(pattern)

	override fun parse(message: String): List<String> {
		val groups = regex.matchEntire(message)?.groupValues
		return groups?.subList(1, groups.size) ?: throw CouldNotMatchRegexPattern(message, regex.toString())
	}

	override fun isMatching(message: String): Boolean {
		return regex.matchEntire(message) != null
	}
}

class CouldNotMatchRegexPattern(messageToParse: String, regexPattern: String)
	: Throwable(message = """Could not use the following regex to parse the message!
		|Regex: $regexPattern
		|Message: $messageToParse
	""".trimMargin())