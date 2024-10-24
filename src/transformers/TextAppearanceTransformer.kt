package transformers

abstract class TextAppearanceTransformer(protected val regex: Regex): Chainable<TextAppearanceTransformer>() {
    protected abstract fun applyRegex(text: String): String

    fun transform(text: String): String {
        val transformedText = applyRegex(text)
        return next?.transform(transformedText) ?: transformedText
    }
}