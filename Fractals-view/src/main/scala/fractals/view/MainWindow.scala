package fractals.view

import java.awt.BorderLayout
import java.awt.Dimension

import fractals.FractalManager
import fractals.messages.Draw
import fractals.view.controllers.ZoomController
import fractals.view.controllers.buttons.BackController
import fractals.view.controllers.buttons.DrawController
import fractals.view.controllers.buttons.ResetController
import fractals.view.controllers.buttons.SendController
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.swing.UIManager
class MainWindow extends JFrame {

  val manager: FractalManager = new FractalManager((640, 480), 50)

  var commands = List[Draw]()

  def add(msg: Draw) = {
    commands = msg :: commands
  }

  def prev = {
    if (commands.size > 0) commands = commands.tail
    else commands = commands

    execute
  }

  def execute: Unit = {
    val fractal = commands match {
      case Nil              => manager.generateFractal
      case Draw(x, y) :: tl => manager.generateFractal(x, y)
    }
    drawingArea.draw(fractal)
  }
  /* *************************************** */
  val (windowWidth, windowHeight) = (1024, 600)

  val drawingArea = new DrawingArea(this)
  val controlPanel =
    new ControlPanel(this, manager, drawingArea) with BackController with ResetController with DrawController with SendController with ZoomController

  setSize(new Dimension(windowWidth, windowHeight))
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  getContentPane().setLayout(new BorderLayout)
  getContentPane().add(drawingArea, BorderLayout.CENTER)
  getContentPane().add(controlPanel, BorderLayout.EAST)

}

object MainWindow {
  def main(args: Array[String]) {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())

    SwingUtilities.invokeLater(new Runnable {

      def run() = {

        val f = new MainWindow
        f.setVisible(true)
      }

    })
  }
}