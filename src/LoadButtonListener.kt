import transformers.TransformationState
import transformers.TransformerRegistry
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import javax.swing.JEditorPane
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.filechooser.FileNameExtensionFilter

class LoadButtonListener(
    private val editorPane: JEditorPane,
    private val frame: JFrame
) : ActionListener {


    override fun actionPerformed(e: ActionEvent?) {
        val fileChooser = JFileChooser()
        fileChooser.fileFilter = FileNameExtensionFilter("Markdown Files", "md")

        val returnVal = fileChooser.showOpenDialog(frame)

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            val selectedFile: File = fileChooser.selectedFile
            if (selectedFile.extension == "md") {
                ReadmeRoot.path = selectedFile.parent
                loadFileContent(selectedFile)
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a valid Markdown file!")
            }
        }
    }

    private fun loadFileContent(file: File) {
        val content = Files.readAllLines(Paths.get(file.absolutePath)).joinToString("\n")

        val htmlContent = markdownToHtml(content)

        editorPane.text = htmlContent
        editorPane.caretPosition = 0
    }

    private fun markdownToHtml(markdown: String): String {
        val htmlBuilder = StringBuilder("<html><body style='font-family: Arial; padding: 0 20px;'>")
        val transformationState = TransformationState()
        markdown.lines()
            .map { processLine(it, transformationState) }
            .forEach(htmlBuilder::append)

        htmlBuilder.append("</body></html>")
        return htmlBuilder.toString()
    }

    private fun processLine(line: String, state: TransformationState): String {
        val result = TransformerRegistry.getStructuralTransformer().transform(line, state)
        return TransformerRegistry.getTextAppearanceTransformer().transform(result)
    }
}
