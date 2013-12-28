package fractals.factories

import fractals.Complex

class JuliaFactory(
    val point: (Double, Double),
    val size: (Int, Int),
    val iterations: Int) extends FractalFactory {

  val c = Complex(point._1, point._2)

  val initialBorder = ((-2.2, 2.2), (-1.2, 1.2))

  def iterate(z: Complex): Int = {
    var Z = Complex(z.re, z.im)
    var i = 0
    while (i < iterations)
      if (escapesToInfinity(Z))
        return i
      else {
        Z = Z.sqr + c
        i += 1
      }
    iterations
  }
}