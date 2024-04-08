package Q1;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class PhoneCallTest {

  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();
  BillingSystemInterface billingSystem = context.mock(BillingSystemInterface.class);
  private final String caller = "+447770123456";
  private final String callee = "+4479341554433";
  private final long PEAK_RATE = 25;
  private final long OFF_PEAK_RATE = 10;

  @Test
  public void exampleOfHowToUsePhoneCall() {
    PhoneCall call =
        new PhoneCall("+447770123456", "+4479341554433", new SystemClock(), billingSystem);
    call.start();
    // waitForSeconds(150);
    call.end();
    call.charge();
  }

  private void waitForSeconds(int n) throws Exception {
    Thread.sleep(n * 1000);
  }

  @Test
  public void callsEndingWithinPeakTimeAreChargedPeakRate() {
    // Set clock to peak time
    ControllableClock clock = new ControllableClock(LocalTime.of(8, 50));

    // Make 20-minute phone call
    PhoneCall call = new PhoneCall(caller, callee, clock, billingSystem);
    call.start();
    clock.advanceBy(20, ChronoUnit.MINUTES);
    call.end();

    // Check that the charge is the duration times peak rate
    context.checking(
        new Expectations() {
          {
            exactly(1).of(billingSystem).addBillItem(caller, callee, call.duration() * PEAK_RATE);
          }
        });
    call.charge();
  }

  @Test
  public void callsStartingWithinPeakTimeAreChargedPeakRate() {
    // Set clock to peak time
    ControllableClock clock = new ControllableClock(LocalTime.of(17, 50));

    // Make 20-minute phone call
    PhoneCall call = new PhoneCall(caller, callee, clock, billingSystem);
    call.start();
    clock.advanceBy(20, ChronoUnit.MINUTES);
    call.end();

    // Check that the charge is the duration times peak rate
    context.checking(
        new Expectations() {
          {
            exactly(1).of(billingSystem).addBillItem(caller, callee, call.duration() * PEAK_RATE);
          }
        });
    call.charge();
  }

  @Test
  public void OffPeakCallsAreChargedCorrectly() {
    // Set clock to off-peak time
    ControllableClock clock = new ControllableClock(LocalTime.of(19, 0));

    // Make 20-minute phone call
    PhoneCall call = new PhoneCall(caller, callee, clock, billingSystem);
    call.start();
    clock.advanceBy(20, ChronoUnit.MINUTES);
    call.end();

    // Check that the charge is the duration times off-peak rate
    context.checking(
        new Expectations() {
          {
            exactly(1)
                .of(billingSystem)
                .addBillItem(caller, callee, call.duration() * OFF_PEAK_RATE);
          }
        });
    call.charge();
  }

  private class ControllableClock implements Clock {
    private LocalTime time;

    public ControllableClock(LocalTime time) {
      this.time = time;
    }

    @Override
    public LocalTime now() {
      return time;
    }

    public void advanceBy(int amount, ChronoUnit unit) {
      time = time.plus(amount, unit);
    }
  }

  private class SystemClock implements Clock {
    @Override
    public LocalTime now() {
      return LocalTime.now();
    }
  }
}
