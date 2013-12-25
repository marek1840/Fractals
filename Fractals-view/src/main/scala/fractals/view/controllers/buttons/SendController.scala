package fractals.view.controllers.buttons

import fractals.view.DrawingArea
import fractals.FractalSender
import fractals.view.controllers.ButtonController
import fractals.view.logic.buttons.SendLogic

trait SendController extends ButtonController {
  val sender: FractalSender
  val drawingArea: DrawingArea

  buttons("send") addActionListener
    (new SendLogic(sender, drawingArea))
}