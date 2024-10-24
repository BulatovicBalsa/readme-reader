package transformers

open class ListTransformer : StructuralTransformer {
    constructor(): this(Regex("^(\\s*[*-])\\s*(.+)"))
    protected constructor(regex: Regex): super(regex)

    protected var listTag = "ul"

    override fun transform(text: String, state: TransformationState): String {
        if (!canTransform(text)) {
            return next?.transform(text, state) ?: text
        }

        if (!state.inList) {
            state.listHierarchy.add(listTag)
            return ("<$listTag>\n" + applyRegex(text))
        }
        else {
            if (state.listHierarchy.last() != listTag) {
                state.listHierarchy.add(listTag)
                return ("</li>\n<$listTag>\n" + applyRegex(text))
            }
            return ("</li>\n" + applyRegex(text))
        }
    }

    override fun applyRegex(text: String): String {
        return text.replace(regex) {
            "<li>${it.groupValues[2]}"
        }
    }
}