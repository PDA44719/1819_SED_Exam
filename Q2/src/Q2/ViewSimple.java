package Q2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewSimple {
  private final JTextField currentMax;
  private final JTextField currentMean;

  public ViewSimple(GUISimple.ControllerBuilder controllerBuilder) {
    JFrame frame = new JFrame("Simple Stats");
    frame.setSize(250, 350);
    Panel panel = new Panel();
    currentMax = new JTextField(11);
    currentMean = new JTextField(11);
    panel.add(new JLabel("Max: value "));
    panel.add(currentMax);
    panel.add(new JLabel("Mean: value "));
    panel.add(currentMean);
    for (int i = 1; i <= 12; i++) {
      final int n = i;
      JButton button = new JButton(String.valueOf(i));
      button.addActionListener(controllerBuilder.buildController(n));
      panel.add(button);
    }
    frame.getContentPane().add(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public void update(ModelSimple model) {
    currentMax.setText(String.valueOf(model.getMax()));
    currentMean.setText(String.valueOf(model.getMean()));
  }
}
