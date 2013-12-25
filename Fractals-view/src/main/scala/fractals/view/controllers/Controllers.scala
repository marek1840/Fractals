package fractals.view.controllers

import java.awt.Point
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

import javax.swing.JButton

trait Controller {
  protected implicit def executableToActionListener(e: { def execute: Unit }): ActionListener = new ActionListener {
    def actionPerformed(x: ActionEvent) = e.execute
  }

  protected implicit def WithTwoPointsToMouseListener(l: {
    def registerStartPoint(p: Point): Unit
    def registerEndPoint(p: Point): Unit
    def execute: Unit
  }): MouseListener = new MouseListener {
    def mouseClicked(e: MouseEvent): Unit = ()
    def mouseEntered(e: MouseEvent): Unit = ()
    def mouseExited(e: MouseEvent): Unit = ()

    def mousePressed(e: MouseEvent): Unit =
      l.registerStartPoint(e.getPoint())

    def mouseReleased(e: MouseEvent): Unit = {
      l.registerEndPoint(e.getPoint())
      l.execute
    }

  }
}

trait ButtonController extends Controller {
  val buttons: Map[String, JButton]
}

