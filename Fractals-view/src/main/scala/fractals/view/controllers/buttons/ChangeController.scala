package fractals.view.controllers.buttons

import fractals.FractalManager
import fractals.messages.FractalType
import fractals.view.ControlPanel
import fractals.view.controllers.Controller
import fractals.view.logic.buttons.ChangeLogic
import javax.swing.JButton

trait ChangeController extends Controller {
  val manager: FractalManager
  val controlPanel: ControlPanel
  val change: JButton

  def fractalType: () => FractalType

  change addActionListener (new ChangeLogic(manager, controlPanel, fractalType))

}