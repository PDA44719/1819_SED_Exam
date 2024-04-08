package Q2;

import java.util.ArrayList;
import java.util.List;

public class Model {
  private final List<Integer> numbers = new ArrayList<>();
  private final List<View> views = new ArrayList<>();
  private int max;
  private double mean;

  public void addNewNumber(Integer n) {
    numbers.add(n);
    max = Math.max(max, n);
    mean = numbers.stream().mapToInt(val -> val).average().orElse(0.0);
    notifyObservers();
  }

  public void addObserver(View view) {
    views.add(view);
  }

  private void notifyObservers() {
    for (View view : views) {
      view.update(this);
    }
  }

  public int getMax() {
    return max;
  }

  public double getMean() {
    return mean;
  }
}
