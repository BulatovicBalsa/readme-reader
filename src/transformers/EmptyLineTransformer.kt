package transformers

class EmptyLineTransformer : StructuralTransformer(Regex("^\\s*$")) {
    override fun applyRegex(text: String): String {
        return text.replace(regex) {
            ""
        }
    }

    override fun transform(text: String, state: TransformationState): String {
        if (!canTransform(text)) {
            return next?.transform(text, state) ?: text
        }

        if (state.inList) {
            val closingListTags: String = state.listHierarchy.reversed().joinToString(separator = "") { "</$it>\n" }
            state.listHierarchy.clear()
            return "</li>\n$closingListTags"
        }

        if (state.inParagraph) {
            state.inParagraph = false
            return "</p>\n"
        }

        return applyRegex(text)
    }
}