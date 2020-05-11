package Chap6_EnumTypeAndAnnotation.item34;

public enum PayrollDay {
    MONDAY(PayType.WEEKDAY),
    TUESDAY(PayType.WEEKDAY),
    WEDNESDAY(PayType.WEEKDAY),
    THURSDAY(PayType.WEEKDAY),
    FRIDAY(PayType.WEEKDAY),
    SATURDAY(PayType.WEEKEND),
    SUNDAY(PayType.WEEKEND);

    private final PayType payType;

    PayrollDay(PayType payType) {
        this.payType = payType;
    }

    int pay(int minutesWorked, int payRate) {
        return payType.pay(minutesWorked, payRate);
    }

    private enum PayType {
        WEEKDAY {
            int overtimePay(int minutesWorked, int payRate) {
                return minutesWorked <= MINS_PER_SHIFT ?
                        0 : (minutesWorked - MINS_PER_SHIFT) * payRate / 2;
            }
        },
        WEEKEND {
            int overtimePay(int minutesWorked, int payRate) {
                return minutesWorked * payRate / 2;
            }
        };

        private static final int MINS_PER_SHIFT = 8 * 60;

        abstract int overtimePay(int minutesWorked, int payRate);

        int pay(int minutesWorked, int payRate) {
            int basePay = minutesWorked * payRate;
            return basePay + overtimePay(minutesWorked, payRate);
        }
    }
}
