package transformers

class ParagraphTransformer : StructuralTransformer(Regex("\\s*(.+)")) {
    override fun applyRegex(text: String): String {
        return "<p>$text"
    }

    override fun transform(text: String, state: TransformationState): String {
        if (!canTransform(text)) {
            return next?.transform(text, state) ?: text
        }

        if (state.inList) {
            return " ${text.trim()}"
        }

        if (!state.inParagraph) {
            state.inParagraph = true
            return applyRegex(text)
        }
        else {
            return " ${text.trim()}"
        }
    }
}