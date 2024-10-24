import java.awt.BorderLayout
import java.awt.Desktop
import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.*
import javax.swing.event.HyperlinkEvent

class MarkdownViewerApp : JFrame() {

    private val editorPane: JEditorPane = JEditorPane()
    private val loadButton: JButton = JButton("Load Markdown File")

    init {
        createUI()
    }

    private fun createUI() {
        title = "Markdown Viewer"
        defaultCloseOperation = EXIT_ON_CLOSE
        size = Dimension(1200, 800)
        setLocationRelativeTo(null)

        editorPane.isEditable = false
        editorPane.contentType = "text/html"

        editorPane.addHyperlinkListener { e ->
            if (e.eventType == HyperlinkEvent.EventType.ACTIVATED) {
                val url = e.url
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(url.toURI())
                }
            }
        }

        val scrollPane = JScrollPane(editorPane)

        loadButton.addActionListener(LoadButtonListener(editorPane, this))

        val buttonPanel = JPanel().apply {
            layout = FlowLayout(FlowLayout.CENTER)
        }
        buttonPanel.add(loadButton)

        layout = BorderLayout()
        add(scrollPane, BorderLayout.CENTER)
        add(buttonPanel, BorderLayout.SOUTH)
    }
}

fun main() {
    SwingUtilities.invokeLater {
        val viewer = MarkdownViewerApp()
        viewer.isVisible = true
    }
}
