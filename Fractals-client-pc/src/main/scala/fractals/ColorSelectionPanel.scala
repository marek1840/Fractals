package fractals.view.panels

import java.awt.Color

import javax.swing.BoxLayout
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class ColorSelectionPanel extends JPanel {
  val red = new JTextField("255")
  val green = new JTextField("0")
  val blue = new JTextField("0")

  private def parse(text: JTextField): Int = {
    val i: Int = if (text.getText() != "") Integer.parseInt(text.getText()) else 0

    if (i < 1) {
      text.setText("0")
      0
    } else if (i > 255) {
      text.setText("255")
      255
    } else i
  }

  def backgroundColor: Color = new Color(parse(red),
    parse(green), parse(blue))

  /* ********************************** */
  /* ************* Layout ************* */
  /* ********************************** */

  def panel(name: String, text: JTextField) =
    new JPanel {
      setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS))
      this.add(new JPanel)
      this.add(new JLabel(name + ": "))
      this.add(text)
      this.add(new JPanel)
    }

  this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS))

  this.add(new JPanel)

  this.add(panel("r", red))
  this.add(panel("g", green))
  this.add(panel("b", blue))

  this.add(new JPanel)
}