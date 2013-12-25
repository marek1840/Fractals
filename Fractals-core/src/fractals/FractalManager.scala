package fractals

import fractals.factories.BurningShipFactory
import fractals.factories.FractalFactory
import fractals.factories.JuliaFactory
import fractals.factories.MandelbrotFactory
import fractals.messages.BurningShip
import fractals.messages.ChangeFactory
import fractals.messages.FactorySettings
import fractals.messages.FractalType
import fractals.messages.Julia
import fractals.messages.Mandelbrot

class FractalManager(
    var size: (Int, Int),
    var iterations: Int) {
  var Type: FractalType = Mandelbrot(2)

  var factory: FractalFactory = new MandelbrotFactory(2, size, iterations)

  def execute(m: FactorySettings): Unit = m match {

    case ChangeFactory => execute(Type)

    case t @ Mandelbrot(n) => {
      Type = t
      factory = new MandelbrotFactory(n, size, iterations)
    }
    case t @ BurningShip(n) => factory = {
      Type = t
      new BurningShipFactory(n, size, iterations)
    }
    case t @ Julia(p) => {
      Type = t
      factory = new JuliaFactory(p, size, iterations)
    }

  }

  def generateFractal: Fractal = factory.draw
  def generateFractal(x: (Double, Double), y: (Double, Double)): Fractal =
    factory.draw(x, y)

}