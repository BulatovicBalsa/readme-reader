package transformers

class TransformationState {
    var inParagraph = false
    val listHierarchy = ArrayList<String>()
    val inList: Boolean
        get() = listHierarchy.isNotEmpty()
}