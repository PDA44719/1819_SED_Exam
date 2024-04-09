package Q2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUISimple {
  private final ViewSimple view = new ViewSimple(new ControllerBuilder());
  private final ModelSimple model = new ModelSimple(view);

  class ControllerBuilder {
    public ActionListener buildController(int n) {
      return e -> model.addNumber(n);
    }
  }

  public static void main(String[] args) {
    new GUI();
  }
}
