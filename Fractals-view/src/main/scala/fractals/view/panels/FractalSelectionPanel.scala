package fractals.view.panels

import java.awt.BorderLayout
import java.awt.Dimension

import fractals.FractalManager
import fractals.messages.BurningShip
import fractals.messages.FractalType
import fractals.messages.Mandelbrot
import fractals.view.ControlPanel
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.ButtonGroup
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JRadioButton
import javax.swing.JSeparator
import javax.swing.JTextField
import javax.swing.SwingConstants

class FractalSelectionPanel(
    val controlPanel: ControlPanel,
    val manager: FractalManager) extends JPanel {

  val mandelbrot = new JRadioButton("Mandelbrot")
  val burningShip = new JRadioButton("Burning Ship")
  val power = new JTextField("2")

  val group = new ButtonGroup {
    this.add(mandelbrot)
    this.add(burningShip)
  }

  val change = new JButton("change")

  mandelbrot.setSelected(true)

  def fractalType: () => FractalType = () => {
    val n = if (power.getText() != "") Integer.parseInt(power.getText()) else 2
    if (mandelbrot.isSelected())
      Mandelbrot(n)
    else
      BurningShip(n)
  }

  /* ********************************** */
  /* ************* Layout ************* */
  /* ********************************** */

  this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS))
  this.setPreferredSize(new Dimension(200, 100))

  this.add(Box.createHorizontalBox)
  this.add(new JPanel {
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS))

    this.add(new JPanel {
      this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS))

      this.add(new JPanel)
      this.add(new JPanel {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS))

        this.add(mandelbrot)
        this.add(burningShip)
      })
      this.add(new JPanel)
    })

    this.add(new JPanel {
      this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS))

      this.add(new JPanel {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS))

        this.add(new JPanel {
          this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS))

          this.add(new JPanel)
          this.add(new JLabel("Power:"))
          this.add(new JPanel)
        })

        this.add(new JPanel {
          this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS))
          this.add(new JPanel)
          this.add(power)
          this.add(new JPanel)
        })
      })

      this.add(new JPanel)
      this.add(new JSeparator(SwingConstants.VERTICAL))
      this.add(new JPanel)

      this.add(new JPanel {
        this.setLayout(new BorderLayout)
        this.add(change, BorderLayout.CENTER)
      })

      this.add(new JPanel)
    })

    this.add(Box.createVerticalBox)
    this.add(new JPanel)
  })
  this.add(Box.createHorizontalBox)
}