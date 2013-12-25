package fractals.view.controllers.buttons

import fractals.view.MainWindow
import fractals.view.controllers.ButtonController
import fractals.view.logic.buttons.BackLogic

trait BackController extends ButtonController {
  val window: MainWindow

  buttons("back") addActionListener (new BackLogic(window))
}