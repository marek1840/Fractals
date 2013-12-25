package fractals.view.logic.buttons

import scala.collection.mutable.LinkedHashMap

import fractals.FractalManager
import fractals.messages.ChangeFactory
import fractals.view.DrawingArea
import fractals.view.MainWindow
import fractals.view.logic.ControlLogic
import fractals.view.panels.ColorSelectionPanel
import fractals.view.panels.IterationSelectionPanel
import fractals.view.panels.SizeSelectionPanel
import javax.swing.JPanel

class DrawLogic(
    val manager: FractalManager,
    val panels: LinkedHashMap[String, JPanel],
    val drawingArea: DrawingArea,
    window: MainWindow) extends ControlLogic(window) {

  def execute: Unit = {
    panels.foreach {
      case (_, p: ColorSelectionPanel)     => drawingArea.backgroundColor = p.backgroundColor
      case (_, p: SizeSelectionPanel)      => manager.size = p.fractalSize
      case (_, p: IterationSelectionPanel) => manager.iterations = p.iterations
      case _                               =>
    }

    manager.execute(ChangeFactory)
    window.execute
  }
}
