package fractals

import java.awt.Color
import java.io.Serializable
import scala.collection.mutable.Map
import scala.collection.mutable.Set

class Fractal(
    val size: (Int, Int),
    val setIteration: Int,
    val dx: Double,
    val dy: Double,
    val x0: Double,
    val y0: Double) extends Serializable {

  var max = 1
  var min = setIteration
  val image = Array.ofDim[Int](size._1, size._2)

  def apply(x: Int, y: Int)(value: Int) = {
    if (value != setIteration && value > max) {
      max = value
    }
    if(value < min)
      min = value
    image(x)(y) = value
  }
}