package Q2;

import java.awt.event.ActionListener;

public class GUI {
  private final ControllerCreator controllerCreator = new ControllerCreator();
  private final View view = new View(controllerCreator);
  private final Model model = new Model();

  public GUI() {
    model.addObserver(view);
  }

  public static void main(String[] args) {
    new GUI();
  }

  public class ControllerCreator {
    public ActionListener createController(int n) {
      return actionEvent -> model.addNewNumber(n);
    }
  }
}
