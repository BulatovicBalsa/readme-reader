package transformers

abstract class StructuralTransformer(protected val regex: Regex): Chainable<StructuralTransformer>() {
    open fun transform(text: String, state: TransformationState): String {
        if (!canTransform(text)) {
            return next?.transform(text, state) ?: text
        }

        if (state.inList) {
            return ("</${state.listHierarchy.removeLast()}>\n" + applyRegex(text))
        }

        if (state.inParagraph) {
            state.inParagraph = false
            return ("</p>\n" + applyRegex(text))
        }

        return applyRegex(text)
    }
    protected fun canTransform(text: String): Boolean = regex.containsMatchIn(text)
    protected abstract fun applyRegex(text: String): String
}