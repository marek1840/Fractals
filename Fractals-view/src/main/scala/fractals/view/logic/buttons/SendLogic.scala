package fractals.view.logic.buttons

import fractals.view.DrawingArea
import fractals.FractalSender

class SendLogic(
    val sender: FractalSender,
    val drawingArea: DrawingArea) {

  def execute: Unit = {
    sender.send(drawingArea.fractal)
  }
}