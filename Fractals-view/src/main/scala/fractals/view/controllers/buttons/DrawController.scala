package fractals.view.controllers.buttons

import scala.collection.mutable.LinkedHashMap

import fractals.FractalManager
import fractals.view.DrawingArea
import fractals.view.MainWindow
import fractals.view.controllers.ButtonController
import fractals.view.logic.buttons.DrawLogic
import javax.swing.JPanel

trait DrawController extends ButtonController {
  val manager: FractalManager
  val panels: LinkedHashMap[String, JPanel]
  val drawingArea: DrawingArea
  val window: MainWindow

  buttons("draw") addActionListener
    (new DrawLogic(manager, panels, drawingArea, window))
}