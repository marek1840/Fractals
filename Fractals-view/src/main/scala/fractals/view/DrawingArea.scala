package fractals.view

import java.awt.BorderLayout
import java.awt.Color
import java.awt.image.BufferedImage

import fractals.Fractal
import fractals.FractalColoring
import javax.swing.BoxLayout
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

class DrawingArea(val window: MainWindow) extends JPanel {
  var fractal: Fractal = _
  var backgroundColor: Color = Color.RED

  def setBackgroundColor(c: Color) = backgroundColor = c

  def draw(fractal: Fractal): Unit = {
    this.fractal = fractal

    val (r, g, b) = (backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue())
    val image = getImage(FractalColoring.color(r, g, b, fractal))

    pic.setIcon(new ImageIcon(image))
    iterationLabel.setText(fractal.min + "/" + fractal.max)
  }

def getImage(fractal: Array[Array[(Int, Int, Int)]]): BufferedImage = {
    val width = fractal.length
    val height = fractal(0).length
    val image = new BufferedImage(fractal.length, fractal(0).length, BufferedImage.TYPE_INT_RGB)

    for {
      x <- (0 until width)
      y <- (0 until height)
      v = fractal(x)(y)
      c = new Color(v._1, v._2, v._3)
    } image.setRGB(x, y, c.getRGB())

    image
  }

  /* ********************************** */
  /* ************* Layout ************* */
  /* ********************************** */

  val pic: JLabel = new JLabel
  val iterationLabel = new JLabel("???/???")

  setLayout(new BorderLayout)

  add(new JPanel {
    this.add(pic)
  }, BorderLayout.CENTER)

  add(new JPanel {
    this.add(new JPanel {
      this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS))

      this.add(new JPanel {
        this.setLayout(new BorderLayout)
        this.add(new JPanel, BorderLayout.CENTER)
      })
      this.add(new JLabel("Min/Max iterations"))
      this.add(new JPanel)

      this.add(iterationLabel)
      this.add(new JPanel)
    })
  }, BorderLayout.PAGE_END)

}