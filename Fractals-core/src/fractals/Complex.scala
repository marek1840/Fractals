package fractals

import scala.math.BigDecimal

final class Complex(val re: Double, val im: Double) {

  def +(z: Complex) = Complex(re + z.re, im + z.im)

  def -(z: Complex) = Complex(re - z.re, im - z.im)

  def *(z: Complex) = Complex(re * z.re - im * z.im, re * z.im + im * z.re)

  def *(d: Double) = Complex(re * d, im * d)

  def /(z: Complex) = {
    val sqr = z.abs
    Complex((re * z.re + im * z.im) / sqr, (re * z.im + im * z.re) / sqr)
  }

  def unary_- = Complex(-re, -im)

  def con = Complex(re, -im)

  def abs = re * re + im * im

  def sqr: Complex = Complex(re * re - im * im, 2 * re * im)

  def pow(n: Int): Complex = n match {
    case 0          => Complex(1)
    case 1          => this
    case 2          => sqr
    case x if x < 0 => Complex(1) / pow(-n)
    case x          => pow(n / 2).sqr * pow(n % 2)
  }

  override def toString = "(" + re + ", " + im + "i)"
}

object Complex {
  def apply(re: Double, im: Double) = new Complex(re, im)
  def apply(re: Double) = new Complex(re, 0)

}










