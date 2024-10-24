package transformers

import ReadmeRoot

class MediaTransformer : StructuralTransformer(Regex("!\\[(.*?)]\\((.*?)\\)")) {
    override fun applyRegex(text: String): String {
        return text.replace(regex) {
            val root = ReadmeRoot.path
            val alt = it.groupValues[1]
            var src = it.groupValues[2]
            val isImage = src.endsWith(".png") || it.groupValues[2].endsWith(".jpg") || it.groupValues[2].endsWith(".jpeg") || it.groupValues[2].endsWith(".gif")

            val isLocal = !src.startsWith("http")
            if (isLocal) {
                src = (root + "/" + it.groupValues[2])
                    .replace(" ", "%20")
                    .replace("\\", "/")
                    .replace("//", "/")
                src = "file:///$src"
            }

            val format = if (isLocal) if (isImage) "img" else "video" else "img"

            "<$format src=\"$src\" alt=\"$alt\">"
        }
    }
}