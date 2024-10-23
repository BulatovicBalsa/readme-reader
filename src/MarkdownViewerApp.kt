import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import javax.swing.filechooser.FileNameExtensionFilter

class MarkdownViewerApp : JFrame() {

    private val textArea: JTextArea = JTextArea()
    private val loadButton: JButton = JButton("Load Markdown File")

    init {
        createUI()
    }

    private fun createUI() {
        // Set the title and default close operation
        title = "Markdown Viewer"
        defaultCloseOperation = EXIT_ON_CLOSE
        size = Dimension(600, 400)

        // Create a scrollable text area
        textArea.isEditable = false
        val scrollPane = JScrollPane(textArea)

        // Add a listener for the load button
        loadButton.addActionListener(LoadButtonListener())

        // Add components to the layout
        layout = BorderLayout()
        add(scrollPane, BorderLayout.CENTER)
        add(loadButton, BorderLayout.SOUTH)
    }

    // ActionListener to handle the "Load File" button
    private inner class LoadButtonListener : ActionListener {
        override fun actionPerformed(e: ActionEvent?) {
            val fileChooser = JFileChooser()
            fileChooser.fileFilter = FileNameExtensionFilter("Markdown Files", "md")

            // Open file dialog and capture user's selection
            val returnVal = fileChooser.showOpenDialog(this@MarkdownViewerApp)

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                val selectedFile: File = fileChooser.selectedFile
                if (selectedFile.extension == "md") {
                    loadFileContent(selectedFile)
                } else {
                    JOptionPane.showMessageDialog(this@MarkdownViewerApp, "Please select a valid Markdown file!")
                }
            }
        }
    }

    // Function to load and display the file content
    private fun loadFileContent(file: File) {
        val content = Files.readAllLines(Paths.get(file.absolutePath)).joinToString("\n")
        textArea.text = content
    }
}

fun main() {
    // Run the application
    SwingUtilities.invokeLater {
        val viewer = MarkdownViewerApp()
        viewer.isVisible = true
    }
}
