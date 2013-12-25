package fractals.view.logic.buttons

import fractals.view.logic.ControlLogic
import fractals.view.MainWindow

class BackLogic(window: MainWindow) extends ControlLogic(window) {
  def execute: Unit = {
    window.prev
    window.execute
  }
}