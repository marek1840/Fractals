package fractals.view.controllers

import fractals.Fractal
import fractals.view.DrawingArea
import fractals.view.MainWindow
import fractals.view.logic.Zoomlogic

trait ZoomController extends ButtonController {
  val drawingArea: DrawingArea
  val window: MainWindow
  def getFractal: () => Fractal = () => drawingArea.fractal

  drawingArea.pic.addMouseListener(new Zoomlogic(window, getFractal, buttons))
}