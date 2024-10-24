package transformers

class HeadingTransformer : StructuralTransformer(Regex("^(#{1,6})\\s*(.*)")) {
    override fun applyRegex(text: String): String {
        return text.replace(regex) {
            val level = it.groupValues[1].length
            val hr = if (level <= 2) "<hr>" else ""
            "<h$level>${it.groupValues[2]}</h$level>$hr"
        }
    }
}