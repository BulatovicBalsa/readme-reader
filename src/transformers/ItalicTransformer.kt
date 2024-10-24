package transformers

class ItalicTransformer : TextAppearanceTransformer(Regex("(\\*(.*?)\\*|_(.*?)_)")) {
    override fun applyRegex(text: String): String {
        return text.replace(regex) { matchResult ->
            // Check which group matched
            val italicText = matchResult.groupValues[2].ifEmpty { matchResult.groupValues[3] }
            "<em>$italicText</em>"
        }
    }
}