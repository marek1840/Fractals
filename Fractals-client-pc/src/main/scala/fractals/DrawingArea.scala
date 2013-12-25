package fractals

import java.awt.BorderLayout
import java.awt.Color
import java.awt.image.BufferedImage

import javax.swing.BoxLayout
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class DrawingArea extends JPanel {
  var fractal: Fractal = null
  var backgroundColor: Color = Color.RED

  def setBackgroundColor(c: Color) = backgroundColor = c

  def redraw = if (fractal != null) draw(fractal)

  def draw(fractal: Fractal): Unit = {
    this.fractal = fractal

    val image = getImage(FractalColoring.color(backgroundColor, fractal))

    pic.setIcon(new ImageIcon(image))
  }

  def getImage(fractal: Array[Array[Color]]): BufferedImage = {
    val width = fractal.length
    val height = fractal(0).length
    val image = new BufferedImage(fractal.length, fractal(0).length, BufferedImage.TYPE_INT_RGB)

    for {
      x <- (0 until width)
      y <- (0 until height)
    } image.setRGB(x, y, fractal(x)(y).getRGB)

    image
  }

  /* ********************************** */
  /* ************* Layout ************* */
  /* ********************************** */

  val pic: JLabel = new JLabel

  setLayout(new BorderLayout)

  add(new JPanel {
    this.add(pic)
  }, BorderLayout.CENTER)

}