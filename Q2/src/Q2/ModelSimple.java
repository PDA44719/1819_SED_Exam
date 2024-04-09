package Q2;

import java.util.ArrayList;
import java.util.List;

public class ModelSimple {

  private final List<Integer> numbers = new ArrayList<>();
  private Integer max;
  private double mean;
  private ViewSimple view;

  public ModelSimple(ViewSimple view) {
    this.view = view;
  }

  public void addNumber(int n) {
    numbers.add(n);
    max = Math.max(max, n);
    mean = numbers.stream().mapToInt(val -> val).average().orElse(0.0);
    view.update(this);
  }

  public int getMax() {
    return max;
  }

  public double getMean() {
    return mean;
  }
}
