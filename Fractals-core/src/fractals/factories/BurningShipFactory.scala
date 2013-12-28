package fractals.factories

import fractals.Complex

class BurningShipFactory(
    val n: Int,
    val size: (Int, Int),
    val iterations: Int) extends FractalFactory {

  // Y-axis is reversed
  val initialBorder = ((-2.0, 1.4), (-2.0, 1.6))

  def iterate(z: Complex): Int = {
    var Z = Complex(z.re, z.im)
    var i = 0
    while (i < iterations)
      if (escapesToInfinity(Z))
        return i
      else {
        Z = Complex(Math.abs(Z.re), Math.abs(Z.im)).pow(n) + z
        i += 1
      }
    iterations
  }

}