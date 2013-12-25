package fractals.view.controllers.buttons

import fractals.view.MainWindow
import fractals.view.controllers.ButtonController
import fractals.view.logic.buttons.ResetLogic

trait ResetController extends ButtonController {
  val window: MainWindow

  buttons("reset") addActionListener (new ResetLogic(window))
}