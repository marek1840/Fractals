package fractals.view.panels

import javax.swing.BoxLayout
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class IterationSelectionPanel extends JPanel {
  val iterationField = new JTextField("50")

  def iterations = {
    val i: Int = if (iterationField.getText() != "") Integer.parseInt(iterationField.getText()) else 400
    if (i < 1) {
      iterationField.setText("50")
      50
    } else i
  }

  /* ********************************** */
  /* ************* Layout ************* */
  /* ********************************** */

  this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS))

  this.add(new JPanel)

  this.add(new JPanel {
    this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS))

    this.add(new JPanel)

    this.add(new JLabel("iterations "))
    this.add(new JPanel)
    this.add(iterationField)

    this.add(new JPanel)
  })

  this.add(new JPanel)
}