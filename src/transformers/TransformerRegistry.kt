package transformers

object TransformerRegistry {
    private val structuralTransformers = listOf(
        HeadingTransformer(),
        MediaTransformer(),
        HyperlinkTransformer(),
        ListTransformer(),
        OrderedListTransformer(),
        ParagraphTransformer(),
        EmptyLineTransformer(),
    )

    private val textAppearanceTransformers = listOf(
        CodeTransformer(),
        BoldTransformer(),
        ItalicTransformer(),
        StrikethroughTransformer(),
    )

    private val structuralTransformer = chain(structuralTransformers)
    private val textAppearanceTransformer = chain(textAppearanceTransformers)

    fun getStructuralTransformer(): StructuralTransformer {
        return structuralTransformer
    }

    fun getTextAppearanceTransformer(): TextAppearanceTransformer {
        return textAppearanceTransformer
    }

    private fun<T : Chainable<T>> chain(transformers: List<T>): T {
        val head = transformers.first()
        var currentTransformer = head
        for (transformer in transformers.drop(1)) {
            currentTransformer.chain(transformer)
            currentTransformer = transformer
        }
        return head
    }
}