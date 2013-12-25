package fractals.messages

import java.awt.Color

sealed trait Message

trait FactorySettings extends Message
case object ChangeFactory extends FactorySettings

trait FractalType extends FactorySettings
case class Mandelbrot(n: Int) extends FractalType
case class Julia(point: (Double, Double)) extends FractalType
case class BurningShip(n: Int) extends FractalType

trait ImageMessage extends Message
case class GenerateNewFractal extends ImageMessage
case class SetBackground(color: Color) extends ImageMessage
case class Draw(x: (Double, Double), y: (Double, Double)) extends ImageMessage