package transformers

class HyperlinkTransformer : StructuralTransformer(Regex("\\[(.*?)]\\(([^\\s)]+)(?:\\s+\"(.*?)\")?\\)")) {
    override fun applyRegex(text: String): String {
        val res = text.replace(regex) {
            "<a href=\"${it.groupValues[2]}\">${it.groupValues[1]}</a>"
        }

        return res
    }
}