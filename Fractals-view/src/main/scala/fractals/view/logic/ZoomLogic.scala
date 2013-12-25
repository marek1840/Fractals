package fractals.view.logic

import java.awt.Point

import fractals.Fractal
import fractals.messages.Draw
import fractals.messages.Julia
import fractals.view.MainWindow
import javax.swing.JButton

class Zoomlogic(
    window: MainWindow,
    fractal: () => Fractal,
    buttons: Map[String, JButton]) {
  var p1: Point = _
  var p2: Point = _

  def registerStartPoint(p: Point): Unit = p1 = p

  def registerEndPoint(p: Point): Unit = p2 = p

  def execute: Unit = {
    if (p2 == p1) {
      window.manager.execute(
        Julia((fractal().x0 + p1.x * fractal().dx,
          fractal().y0 + p1.y * fractal().dy)))

      window.commands = Nil
      window.execute
    } else {
      val s = System.currentTimeMillis

      val (x0, x1): (Double, Double) = if (p1.x < p2.x) (p1.x, p2.x) else (p2.x, p1.x)
      val (y0, y1): (Double, Double) = if (p1.y < p2.y) (p1.y, p2.y) else (p2.y, p1.y)

      val (dx, dy, fx0, fy0) = (fractal().dx, fractal().dy,
        fractal().x0, fractal().y0)

      val draw = Draw((fx0 + x0 * dx, fx0 + dx * x1), (fy0 + dy * y0, fy0 + y1 * dy))

      window.add(draw)
      buttons("draw").doClick

      println("executed in: " + (System.currentTimeMillis() - s));
    }
  }
}

