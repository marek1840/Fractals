package fractals.factories

import java.awt.Color
import fractals.Fractal
import fractals.Complex
import scala.annotation.tailrec

trait FractalFactory {
  def size: (Int, Int)
  def iterations: Int

  def initialBorder: ((Double, Double), (Double, Double))

  protected final def escapesToInfinity(z: Complex) = z.abs > 4

  def iterate(z: Complex): Int

  final def draw: Fractal = draw(initialBorder._1, initialBorder._2)

  final def draw(x: (Double, Double), y: (Double, Double)): Fractal = {
    val s = System.currentTimeMillis
    val dx = Math.abs(x._2 - x._1) / size._1
    val dy = Math.abs(y._2 - y._1) / size._2

    val x0 = x._1
    val y0 = y._1

    val fractal = new Fractal(size, iterations, dx, dy, x._1, y._1)

    for {
      x <- (0 until size._1)
      y <- (0 until size._2)
      value = iterate(Complex(x0 + x * dx, y0 + y * dy))
    } fractal(x, y)(value)

    println("iterated in: " + (System.currentTimeMillis() - s));
    fractal
  }

}