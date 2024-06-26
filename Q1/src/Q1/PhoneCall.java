package Q1;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.LocalTime;

public class PhoneCall {
  private static final long PEAK_RATE = 25;
  private static final long OFF_PEAK_RATE = 10;
  private final String caller;
  private final String callee;
  private LocalTime startTime;
  private LocalTime endTime;
  private Clock clock;
  private BillingSystemInterface billingSystem;

  public PhoneCall(
      String caller, String callee, Clock clock, BillingSystemInterface billinngSystem) {
    this.caller = caller;
    this.callee = callee;
    this.clock = clock;
    this.billingSystem = billinngSystem;
  }

  public void start() {
    startTime = clock.now();
  }

  public void end() {
    endTime = clock.now();
  }

  public void charge() {
    billingSystem.addBillItem(caller, callee, priceInPence());
  }

  private long priceInPence() {
    if (startTime.isAfter(LocalTime.of(9, 00)) || endTime.isBefore(LocalTime.of(18, 00))) {
      return duration() * PEAK_RATE;
    } else {
      return duration() * OFF_PEAK_RATE;
    }
  }

  public long duration() {
    return MINUTES.between(startTime, endTime) + 1;
  }
}
