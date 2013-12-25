package fractals

import javax.swing.JFrame
import java.awt.Dimension
import java.awt.BorderLayout
import javax.swing.UIManager
import javax.swing.SwingUtilities

class FractalClient extends JFrame {
  val drawingArea = new DrawingArea
  val controlPanel = new ControlPanel(drawingArea)
  val (windowWidth, windowHeight) = (1024, 600)

  setSize(new Dimension(windowWidth, windowHeight))
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  getContentPane().setLayout(new BorderLayout)
  getContentPane().add(drawingArea, BorderLayout.CENTER)
  getContentPane().add(controlPanel, BorderLayout.EAST)

}

object FractalClient {
  def main(args: Array[String]) {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())

    SwingUtilities.invokeLater(new Runnable {

      def run() = {

        val f = new FractalClient
        f.setVisible(true)
      }

    })
    
  }
}