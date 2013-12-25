package fractals.view.panels

import javax.swing.BoxLayout
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class SizeSelectionPanel extends JPanel {
  val widthField = new JTextField("640")
  val heightField = new JTextField("480")

  def parse(text: JTextField): Int = {
    val i: Int = if (text.getText != "") Integer.parseInt(text.getText) else 400
    if (i < 10) {
      text.setText("400")
      400
    } else i
  }

  def fractalSize: (Int, Int) = (parse(widthField), parse(heightField))

  /* ********************************** */
  /* ************* Layout ************* */
  /* ********************************** */

  this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS))

  this.add(new JPanel {
    this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS))

    this.add(new JPanel)
    this.add(new JLabel("size:"))
    this.add(new JPanel)
  })

  this.add(new JPanel {
    this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS))

    this.add(new JPanel)

    this.add(widthField)
    this.add(new JLabel("x"))
    this.add(heightField)
    this.add(new JPanel)
  })

  this.add(new JPanel)
}