package Chap4_Generic.item30;

public class LottePayCard extends PayCard {
    private final Sale sale;

    LottePayCard(Builder builder) {
        super(builder);
        sale = builder.sale;
    }

    public enum Sale {
        LOTTE_WORLD("롯데월드"), LOTTE_DEPT("롯데백화점");

        Sale(String sale) {
        }
    }

    public static class Builder extends PayCard.Builder<Builder> {
        private final Sale sale;

        public Builder(Sale sale) {
            this.sale = sale;
        }

        @Override
        LottePayCard build() {
            return new LottePayCard(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
