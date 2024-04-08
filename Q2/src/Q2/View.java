package Q2;

import javax.swing.*;
import java.awt.*;

public class View {
  JTextField currentMax;
  JTextField currentMean;

  public View(GUI.ControllerCreator controllerCreator) {
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
      button.addActionListener(controllerCreator.createController(n));
      panel.add(button);
    }
    frame.getContentPane().add(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public void update(Model model) {
    currentMax.setText(String.valueOf(model.getMax()));
    currentMean.setText(String.valueOf(model.getMean()));
  }
}
