package org.stathry.jdkdeep.concurrent.luckymoney;

import org.stathry.jdkdeep.util.Assert;
import org.stathry.jdkdeep.util.DecimalUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * TODO
 *
 * @author dongdaiming
 * @date 2018/5/3
 */
public class LuckyMoneyService {

    private static final DecimalUtils DECIMAL = new DecimalUtils(5, 2, RoundingMode.HALF_UP);
    private static final BigDecimal MIN = DECIMAL.valueOf("0.01");
    private static final BigDecimal MAX = DECIMAL.valueOf("200.00");

    public List<BigDecimal> randomAllocateMoney(double money, int num) {
        List<BigDecimal> gifts;
        if (num == 1) {
            return Arrays.asList(DECIMAL.valueOf(money));
        }

        checkRange(money, num);

        // TODO 刚好大或刚好小，直接均分
        // TODO 预先判断金额是否太大或太小
        gifts = new ArrayList<>(num);
        BigDecimal total = DECIMAL.valueOf(money);
        BigDecimal sum = DECIMAL.valueOf(0);
        BigDecimal bn;
        BigDecimal curMax = MAX;
        BigDecimal curMin = MIN;
        BigDecimal c;
        int[] an = new int[num];
        int nSum = randomInitArrayAndSum(an, num);
        BigDecimal bSum = DECIMAL.valueOf(nSum);
        BigDecimal rate;
        for (int i = 0, size = num; i < size; i++) {
            if (i == size - 1) {
                c = DECIMAL.subtract(total, sum);
            } else {
                bn = DECIMAL.valueOf(num - i - 1);
                rate = DECIMAL.divide(DECIMAL.valueOf(an[i]), bSum);
                c = DECIMAL.multiply(total, rate);

                if (c.compareTo(MIN) <= 0) {
                    c = MIN;
                } else if (c.compareTo(MAX) >= 0) {
                    c = MAX;
                }
                curMax = DECIMAL.subtract(DECIMAL.subtract(total, sum), DECIMAL.multiply(bn, MIN));
                curMax = curMax.compareTo(MAX) > 0 ? MAX : curMax;
                c = c.compareTo(curMax) > 0 ? curMax : c;

                curMin = DECIMAL.subtract(DECIMAL.subtract(total, sum), DECIMAL.multiply(bn, MAX));
                curMin = curMin.compareTo(MIN) < 0 ? MIN : curMin;
                c = c.compareTo(curMin) < 0 ? curMin : c;
            }

            sum = DECIMAL.add(sum, c);
            Assert.isTrue(c.compareTo(MIN) >= 0 && c.compareTo(MAX) <= 0, "curSum=" + sum.toString()
                    + ",curLuckyMoney=" + c.toString() + ",curMax=" + curMax.toString()
                    + ",gifts=" + gifts);
            gifts.add(c);
        }
        Collections.shuffle(gifts);
        return gifts;
    }

    private void checkRange(double money, int num) {
        if (money / num > MAX.doubleValue()) {
            throw new IllegalArgumentException("illegal money:" + money + ",too big.");
        }
        if (money / num < MIN.doubleValue()) {
            throw new IllegalArgumentException("illegal money:" + money + ",too little.");
        }
    }

//    times:1000000,total cost times:17369
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        LuckyMoneyService s = new LuckyMoneyService();
        BigDecimal sum;
        List<BigDecimal> gifts;
        double money = 1000;
        int n = 10;

//        double money = 100;
//        int n = 100;

// double money = 100;
//        int n = 10;
        int times = 1000000;
        for (int i = 0; i < times; i++) {
//            System.out.println("curTimes:" + i);
            gifts = s.randomAllocateMoney(money, n);
            sum = s.sumGifts(gifts);
//        System.out.println(sum);
            Assert.isTrue(sum.compareTo(BigDecimal.valueOf(money)) == 0);
            Assert.isTrue(gifts.size() == n);
//            Thread.sleep(50);
        }
        long end = System.currentTimeMillis();
        System.out.println("times:" + times + ",total cost times:" + (end - start));
    }

    public BigDecimal sumGifts(List<BigDecimal> gifts) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 0, size = gifts.size(); i < size; i++) {
            sum = DECIMAL.add(sum, gifts.get(i));
        }
        return sum;
    }

    private int randomInitArrayAndSum(int[] a, int n) {
        int sum = 0;
        SecureRandom random = new SecureRandom();
        for (int i = 0, size = n; i < size; i++) {
            a[i] = random.nextInt(n);
            sum += a[i];
        }
        return sum;
    }

}
