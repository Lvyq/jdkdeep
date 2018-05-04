package org.stathry.jdkdeep.concurrent.luckymoney;

import org.stathry.jdkdeep.util.Assert;
import org.stathry.jdkdeep.util.DecimalUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.ArrayList;
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
            gifts = new ArrayList<>(1);
            gifts.add(DECIMAL.valueOf(money));
            return gifts;
        }
        if (money / num > MAX.doubleValue()) {
            throw new IllegalArgumentException("illegal money:" + money + ",too big.");
        }
        if (money / num < MIN.doubleValue()) {
            throw new IllegalArgumentException("illegal money:" + money + ",too little.");
        }
        // TODO 刚好大或刚好小，直接均分
        gifts = new ArrayList<>(num);
        BigDecimal total = DECIMAL.valueOf(money);
        BigDecimal sum = DECIMAL.valueOf(0);
        BigDecimal bn;
        BigDecimal curMax = MAX;
        int nSum = 0;
        BigDecimal c;
        SecureRandom random = new SecureRandom();
        int[] an = new int[num];
        for (int i = 0, size = num; i < size; i++) {
            an[i] = random.nextInt(num);
            nSum += an[i];
        }
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
                } else {
                    if (c.compareTo(MAX) >= 0) {
                        c = MAX;
                    }
                    curMax = DECIMAL.subtract(DECIMAL.subtract(total, sum), DECIMAL.multiply(bn, MIN));
                    curMax = curMax.compareTo(MAX) > 0 ? MAX : curMax;
                    c = c.compareTo(curMax) > 0 ? curMax : c;
                }

                sum = DECIMAL.add(sum, c);
            }
            Assert.isTrue(c.compareTo(MIN) >= 0, "curSum=" + sum.toString()
                    + ",curLuckyMoney=" + c.toString() + ",curMax=" + curMax.toString()
                    + ",gifts=" + gifts);

            gifts.add(c);

        }
//        System.out.println("sort before:" + gifts);
        Collections.sort(gifts);
////        System.out.println("before:" + gifts);
        BigDecimal last = gifts.get(num - 1);
        BigDecimal src;
        BigDecimal cur;
        if (last.compareTo(MAX) > 0) {
            BigDecimal remain = DECIMAL.subtract(last, MAX);
            gifts.set(num - 1, MAX);
            for (int i = 0; i < num; i++) {
                if (remain.compareTo(BigDecimal.ZERO) == 0) {
                    break;
                }
                src = gifts.get(i);
                cur = DECIMAL.add(src, remain);
                cur = cur.compareTo(MAX) >= 0 ? MAX : cur;
                gifts.set(i, cur);
                remain = DECIMAL.subtract(remain, DECIMAL.subtract(cur, src));
            }
        }
//
        last = gifts.get(num - 1);
        BigDecimal first = gifts.get(0);

//        System.out.println("gifts:" + gifts);
        Assert.isTrue(last.compareTo(MAX) <= 0 && last.compareTo(MIN) >= 0, "last=" + last.toString());
        Assert.isTrue(first.compareTo(MAX) <= 0 && first.compareTo(MIN) >= 0, "first=" + first.toString());
        Collections.shuffle(gifts);
        return gifts;
    }

    public static void main(String[] args) throws InterruptedException {
        LuckyMoneyService s = new LuckyMoneyService();
        BigDecimal sum;
        List<BigDecimal> gifts;
//        double money = 1000;
//        int n = 10;

 double money = 100;
        int n = 100;

// double money = 100;
//        int n = 10;
        int times = 1000000;
        for (int i = 0; i < times; i++) {
            System.out.println("curTimes:" + i);
            gifts = s.randomAllocateMoney(money, n);
            sum = s.sumGifts(gifts);
//        System.out.println(sum);
            Assert.isTrue(sum.compareTo(BigDecimal.valueOf(money)) == 0);
            Assert.isTrue(gifts.size() == n);
//            Thread.sleep(50);
        }
    }

    public BigDecimal sumGifts(List<BigDecimal> gifts) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 0, size = gifts.size(); i < size; i++) {
            sum = DECIMAL.add(sum, gifts.get(i));
        }
        return sum;
    }

}
