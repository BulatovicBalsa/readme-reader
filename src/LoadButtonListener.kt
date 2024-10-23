import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*
import java.io.File
import java.nio.file.Files
import javax.swing.filechooser.FileNameExtensionFilter

class LoadButtonListener(
    private val frame: JFrame,
    private val textArea: JTextArea
) : ActionListener {

    override fun actionPerformed(e: ActionEvent?) {
        val fileChooser = JFileChooser()
        fileChooser.fileFilter = FileNameExtensionFilter("Markdown Files", "md")

        // Open file dialog and capture user's selection
        val returnVal = fileChooser.showOpenDialog(frame)

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            val selectedFile: File = fileChooser.selectedFile
            if (selectedFile.extension == "md") {
                loadFileContent(selectedFile)
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a valid Markdown file!")
            }
        }
    }

    // Function to load and display the file content
    private fun loadFileContent(file: File) {
        val content = Files.readAllLines(file.toPath()).joinToString("\n")
        textArea.text = content
        textArea.caretPosition = 0
    }
}
