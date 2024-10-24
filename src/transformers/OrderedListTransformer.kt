package transformers

class OrderedListTransformer : ListTransformer(Regex("^(\\s*\\d+\\.)\\s*(.+)")) {
    init {
        listTag = "ol"
    }

    override fun applyRegex(text: String): String {
        return text.replace(regex) {
            "<li>${it.groupValues[2]}"
        }
    }
}