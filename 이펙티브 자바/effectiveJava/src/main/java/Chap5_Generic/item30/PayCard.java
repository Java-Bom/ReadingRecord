package Chap5_Generic.item30;

import java.util.EnumSet;
import java.util.Set;

public abstract class PayCard {
    final Set<Benefit> benefits;

    PayCard(Builder<?> builder) {
        benefits = builder.benefits.clone();
    }

    public enum Benefit {
        POINT("포인트"), SALE("할인"), SUPPORT("연회비지원");

        Benefit(String benefit) {
        }
    }

    abstract static class Builder<T extends Builder<T>> { // 재귀적 타입한정
        EnumSet<Benefit> benefits = EnumSet.noneOf(Benefit.class);

        public T addBenefit(Benefit benefit) {
            this.benefits.add(benefit);
            return self();
        }

        abstract PayCard build();

        protected abstract T self();
    }
}
