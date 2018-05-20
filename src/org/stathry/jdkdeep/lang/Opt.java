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

    // 使用enum实现接口也可以使用类似效果
    // 并且推荐使用接口方式，因为更具伸缩性
    abstract int apply(int a, int b);
}
