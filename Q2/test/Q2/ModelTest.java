package Q2;

import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ModelTest {

  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();
  Updatable view = context.mock(Updatable.class);
  private final ModelSimple model = new ModelSimple(view);

  @Test
  public void maximumProperlyUpdated() {
    model.addNumber(1);
    assertEquals(model.getMax(), 1);
    model.addNumber(7);
    model.addNumber(2);
    assertEquals(model.getMax(), 7);
    model.addNumber(8);
    model.addNumber(9);
    assertEquals(model.getMax(), 9);
  }

  @Test
  public void meanProperlyCalculated() {
    model.addNumber(1);
    model.addNumber(7);
    model.addNumber(2);
    model.addNumber(9);
    model.addNumber(8);
    // Last parameter is delta, to allow for some precision loss
    assertEquals(model.getMean(), 5.4, 0.001);
  }
}
