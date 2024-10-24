package transformers

class StrikethroughTransformer : TextAppearanceTransformer(Regex("~~(.*?)~~")) {
    override fun applyRegex(text: String): String {
        return text.replace(regex) {
            "<s>${it.groupValues[1]}</s>"
        }
    }
}