package org.stathry.jdkdeep.lang;

public enum Opt {

    ADD {
        @Override
        int apply(int a, int b) {
            return a + b;
        }
    },
    SUB {
        @Override
        int apply(int a, int b) {
            return a - b;
        }
    },
    MUL {
        @Override
        int apply(int a, int b) {
            return a * b;
        }
    },
    DIV {
        @Override
        int apply(int a, int b) {
            return a / b;
        }
    };

    abstract int apply(int a, int b);
}
