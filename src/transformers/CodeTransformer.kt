package transformers

class CodeTransformer : TextAppearanceTransformer(Regex("`([^`]+)`")) {
    override fun applyRegex(text: String): String {
        return text.replace(regex) {
            "<code>${it.groupValues[1]}</code>"
        }
    }
}