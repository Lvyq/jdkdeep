package org.stathry.jdkdeep.lang;

/**
 * TODO
 *
 * @author dongdaiming
 * @date 2018/5/16
 */
public class Dream {
    // required
    private final int house;
    private final int child;
    // optional
    private final int year;
    private final int sal;

    public Dream(DreamBuilder builder) {
        this.house = builder.house;
        this.child = builder.child;
        this.year = builder.year;
        this.sal = builder.sal;
    }

    @Override
    public String toString() {
        return "Dream{" +
                "house=" + house +
                ", child=" + child +
                ", year=" + year +
                ", sal=" + sal +
                '}';
    }

    public static class DreamBuilder {
        // required
        private final int house;
        private final int child;
        // optional
        private int year = 2020;
        private int sal = 30;

        public DreamBuilder(int house, int child) {
            this.house = house;
            this.child = child;
        }

        public Dream build() {
            return new Dream(this);
        }

        public DreamBuilder year(int year) {
            this.year = year;
            return this;
        }

        public DreamBuilder sal(int sal) {
            this.sal = sal;
            return this;
        }
    }

}
