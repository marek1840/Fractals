package fractals

import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

import fractals.view.panels.ColorSelectionPanel
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JSeparator
import javax.swing.SwingConstants

class ControlPanel(val drawingArea: DrawingArea) extends JPanel {
  val colorPanel = new ColorSelectionPanel
  val redraw = new JButton("redraw")
  val download = new JButton("download")
  val reader: FractalReader = new FractalReader

  this.setLayout(new BorderLayout)
  this.add(new JPanel, BorderLayout.PAGE_START)
  this.add(new JPanel {
    this.add(new JPanel {
      this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS))
      this.add(colorPanel)
      this.add(new JPanel {
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS))
        this.add(new JPanel)

        this.add(redraw)

        this.add(new JPanel)
        this.add(new JSeparator(SwingConstants.VERTICAL))
        this.add(new JPanel)

        this.add(download);

        this.add(new JPanel)
      })
    })
  }, BorderLayout.CENTER)
  this.add(new JPanel, BorderLayout.PAGE_END)

  redraw.addActionListener(new ActionListener {
    def actionPerformed(e: ActionEvent) = {
      drawingArea.backgroundColor = colorPanel.backgroundColor
      drawingArea.draw
    }
  })

  download.addActionListener(new ActionListener {
    def actionPerformed(e: ActionEvent) = {
      drawingArea.fractal = reader.getFractal
      redraw.doClick
    }
  })
}