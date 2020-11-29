package me.darthsid.core

class RegexMessageParser (pattern: String) : MessageParser {
	private val regex: Regex = Regex(pattern)

	override fun parse(message: String): List<String> {
		val groups = regex.matchEntire(message)?.groupValues
		return groups?.subList(1, groups.size) ?: throw CouldNotMatchRegexPattern(message, regex.toString())
	}
}

class CouldNotMatchRegexPattern(messageToParse: String, regexPattern: String)
	: Throwable(message = """The regex doesn't match the message:
		|	Regex: $regexPattern
		|	Message: $messageToParse
	""".trimMargin())