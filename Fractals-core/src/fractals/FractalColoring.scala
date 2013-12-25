package fractals

import java.awt.Color
import scala.collection.mutable.Set
import scala.collection.mutable.Map

object FractalColoring {

  def scale(color: Color, value: Double): Color =
    new Color((color.getRed() * value).asInstanceOf[Int],
      (color.getGreen() * value).asInstanceOf[Int],
      (color.getBlue() * value).asInstanceOf[Int])

  def mix(c1: Color, value: Double, c2: Color): Color = {
    val C1 = scale(c1, value)
    val C2 = scale(c2, 1 - value)

    add(C1, C2)
  }

  def add(c1: Color, c2: Color): Color = {
    val (r, g, b) = (c1.getRed() + c2.getRed(),
      c1.getGreen() + c2.getGreen(),
      c1.getBlue() + c2.getBlue())
    new Color(r, g, b)
  }

  /* ************************************** */
  def color(background: Color, fractal: Fractal): Array[Array[Color]] = {
    val image = Array.ofDim[Color](fractal.size._1, fractal.size._2)
    val span = ((fractal.max - fractal.min) / 2) + 1.0

    println("min/max: ", fractal.min + "/" + fractal.max)

    for {
      x <- 0 until fractal.size._1
      y <- 0 until fractal.size._2
      i = fractal.image(x)(y)
    } {
      if (i == fractal.setIteration) {
        image(x)(y) = Color.BLACK
      } else if (i * 2 < (fractal.max + fractal.min)) {
        val step: Double = (i - fractal.min) / span
        image(x)(y) = scale(background, step)
      } else {
        val tmp = (i - fractal.min) / span
        val step: Double = if (tmp <= 1) 0 else tmp - 1
        val rStep = 1 - step
        image(x)(y) = mix(Color.WHITE, step, background)
      }
    }

    image
  }
}