package fractals.view

import java.awt.BorderLayout
import java.awt.Color
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

import scala.collection.mutable.LinkedHashMap

import fractals.FractalManager
import fractals.messages.FractalType
import fractals.view.controllers.buttons.ChangeController
import fractals.view.panels.ColorSelectionPanel
import fractals.view.panels.FractalSelectionPanel
import fractals.view.panels.IterationSelectionPanel
import fractals.view.panels.SizeSelectionPanel
import javax.swing.BorderFactory
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JSeparator
import javax.swing.SwingConstants

class ControlPanel(
    val window: MainWindow,
    val manager: FractalManager,
    val drawingArea: DrawingArea) extends JPanel {

  val sender = new FractalSender

  def setFractalType(t: FractalType) = {
    manager.execute(t)
    buttons("reset").doClick()
  }

  def reset: Unit = buttons("reset").doClick

  /* ********************************** */
  /* ************* Layout ************* */
  /* ********************************** */

  val buttons =
    Map[String, JButton](
      "back" -> new JButton("<"),
      "reset" -> new JButton("<<"),
      "draw" -> new JButton("Draw"),
      "send" -> new JButton("Send to GoogleDrive"))

  val panels =
    LinkedHashMap[String, JPanel](
      "fractalType" -> new FractalSelectionPanel(this, manager) with ChangeController,
      "color" -> new ColorSelectionPanel,
      "size" -> new SizeSelectionPanel,
      "iterations" -> new IterationSelectionPanel,
      "buttons" -> new JPanel {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS))
        this.add(new JPanel)

        this.add(new JPanel {
          this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS))
          this.add(new JPanel)

          this.add(buttons("back"))
          this.add(buttons("reset"))

          this.add(new JPanel)
          this.add(new JSeparator(SwingConstants.VERTICAL))
          this.add(new JPanel)

          this.add(new JPanel {
            this.setLayout(new BorderLayout)
            this.add(buttons("draw"), BorderLayout.CENTER)
          })

          this.add(new JPanel)
        })

        this.add(new JPanel)
        this.add(new JSeparator(SwingConstants.HORIZONTAL))
        this.add(new JPanel)

        this.add(new JPanel {
          this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS))
          this.add(new JPanel)

          this.add(buttons("send"))

          this.add(new JPanel)
        })

        this.add(new JPanel)
      })

  panels.foreach(_._2.setBorder(BorderFactory.createLineBorder(Color.GRAY)))

  val panel = new JPanel {
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS))
  }

  panels foreach { p => panel.add(p._2); panel.add(new JPanel) }

  this.add(panel)
  this.add(new JPanel)
}