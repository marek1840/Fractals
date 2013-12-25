package fractals.factories

import java.awt.Color
import fractals.Fractal
import fractals.Complex

class MandelbrotFactory(
    val n: Int,
    val size: (Int, Int),
    val iterations: Int) extends FractalFactory {

  val initialBorder = ((-2.0, 1.2), (-1.2, 1.2))

  def iterate(z: Complex): Int = {
    var Z = Complex(z.re, z.im)
    var i = 0
    while (i < iterations)
      if (escapesToInfinity(Z))
        return i
      else {
        Z = Z.pow(n) + z
        i += 1
      }
    iterations
  }
}