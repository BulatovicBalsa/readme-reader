import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.*

class MarkdownViewerApp : JFrame() {

    private val textArea: JTextArea = JTextArea()
    private val loadButton: JButton = JButton("Load Markdown File")

    init {
        createUI()
    }

    private fun createUI() {
        title = "Markdown Viewer"
        defaultCloseOperation = EXIT_ON_CLOSE
        size = Dimension(1200, 800)
        setLocationRelativeTo(null)

        textArea.isEditable = false
        val scrollPane = JScrollPane(textArea)

        // Add a listener for the load button
        val loadButtonListener = LoadButtonListener(this, textArea)
        loadButton.addActionListener(loadButtonListener)

        // Add components to the layout
        layout = BorderLayout()
        add(scrollPane, BorderLayout.CENTER)
        add(loadButton, BorderLayout.SOUTH)
    }
}

fun main() {
    // Run the application
    SwingUtilities.invokeLater {
        val viewer = MarkdownViewerApp()
        viewer.isVisible = true
    }
}
