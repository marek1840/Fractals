package fractals.view.logic.buttons

import fractals.view.MainWindow
import fractals.view.logic.ControlLogic

class ResetLogic(window: MainWindow) extends ControlLogic(window) {
  def execute: Unit = {
    window.commands = Nil
    window.execute
  }
}