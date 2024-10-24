package transformers

class BoldTransformer : TextAppearanceTransformer(Regex("(\\*\\*(.*?)\\*\\*|__(.*?)__)")) {
    override fun applyRegex(text: String): String {
        return text.replace(regex) { matchResult ->
            // Check which group matched
            val boldText = matchResult.groupValues[2].ifEmpty { matchResult.groupValues[3] }
            "<strong>$boldText</strong>"
        }
    }
}