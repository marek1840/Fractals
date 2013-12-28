package fractals

import java.awt.Color

object FractalColoring {

  def scale(r: Int, g: Int, b: Int, value: Double): (Int, Int, Int) =
    ((r * value).asInstanceOf[Int],
      (g * value).asInstanceOf[Int],
      (b * value).asInstanceOf[Int])

  def mix(r1: Int, g1: Int, b1: Int, value: Double, r2: Int, g2: Int, b2: Int): (Int, Int, Int) = {
    val (red1, green1, blue1) = scale(r1, g1, b1, value)
    val (red2, green2, blue2) = scale(r2, g2, b2, 1 - value)

    add(red1, green1, blue1, red2, green2, blue2)
  }

  def add(r1: Int, g1: Int, b1: Int, r2: Int, g2: Int, b2: Int): (Int, Int, Int) =
    (r1 + r2, g1 + g2, b1 + b2)

  /* ************************************** */
  def color(r: Int, g: Int, b: Int, fractal: Fractal): Array[Array[(Int, Int, Int)]] = {
    val image = Array.ofDim[(Int, Int, Int)](fractal.size._1, fractal.size._2)
    val span = ((fractal.max - fractal.min) / 2) + 1.0

    println("min/max: ", fractal.min + "/" + fractal.max)

    for {
      x <- 0 until fractal.size._1
      y <- 0 until fractal.size._2
      i = fractal.image(x)(y)
    } {
      if (i == fractal.setIteration) {
        image(x)(y) = (Color.BLACK.getRed(), Color.BLACK.getGreen(), Color.BLACK.getBlue())
      } else if (i * 2 < (fractal.max + fractal.min)) {
        val step: Double = (i - fractal.min) / span
        image(x)(y) = scale(r, g, b, step)
      } else {
        val tmp = (i - fractal.min) / span
        val step: Double = if (tmp <= 1) 0 else tmp - 1
        val rStep = 1 - step
        image(x)(y) = mix(Color.WHITE.getRed(), Color.WHITE.getBlue(), Color.WHITE.getBlue(), step, r, g, b)
      }
    }

    image
  }
}