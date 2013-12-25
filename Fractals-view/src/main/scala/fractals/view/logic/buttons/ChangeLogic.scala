package fractals.view.logic.buttons

import fractals.FractalManager
import fractals.messages.FractalType
import fractals.view.ControlPanel

class ChangeLogic(
    val manager: FractalManager,
    val controlPanel: ControlPanel,
    val fractalType: () => FractalType) {

  def execute: Unit = {
    manager.execute(fractalType())
    controlPanel.reset
  }
}