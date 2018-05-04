package org.stathry.jdkdeep.concurrent.luckymoney;

import org.stathry.jdkdeep.util.Assert;
import org.stathry.jdkdeep.util.DecimalUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * TODO
 *
 * @author dongdaiming
 * @date 2018/5/3
 */
public class LuckyMoneyService {

    private BlockingQueue<Long> queue = new LinkedBlockingDeque<>();

    private static final BigDecimal MIN = new BigDecimal("0.01");
    private static final BigDecimal MAX =  new BigDecimal("200");
    private static final DecimalUtils DU = new DecimalUtils(5, 2, RoundingMode.HALF_UP);
    private static final MathContext MC = DU.getMathContext();

    public void allocateLuckMoney(double money, int n) throws InterruptedException {
//        String[] gifts = randomAllocateMoney(money, n);

        long uid;
        String curMoney;
        int i = 0;
        while (true) {
            uid = queue.take();
//            curMoney = gifts[i++];
//            System.out.println(uid + " get luck money :" + curMoney);
        }
    }

    public List<String> randomAllocateMoney(double money, int n) {
        List<String> gifts;
        if(n == 1) {
            gifts = new ArrayList<>(1);
            gifts.add(DU.format(money)) ;
            return gifts;
        }
        gifts = new ArrayList<>(n);
        BigDecimal total = new BigDecimal(money, MC) ;
        BigDecimal sum = new BigDecimal(0, MC) ;
        BigDecimal bn;
        BigDecimal curMax;
        int nSum = 0;
        BigDecimal c ;
        SecureRandom random = new SecureRandom();
        int[] an = new int[n];
        for (int i = 0, size = n; i < size; i++) {
            an[i] = random.nextInt(n);
            nSum += an[i];
        }
        BigDecimal bSum = new BigDecimal(nSum, MC);

        for (int i = 0, size = n; i < size; i++) {
            if(i == size - 1) {
//                System.out.println("lastSum:" + sum);
                c = DU.subtract(total, sum);
//                System.out.println("lastGift:" + c);
            } else {
                c = DU.multiply(total, DU.divide(BigDecimal.valueOf(an[i]), bSum));

                c = c.compareTo(MIN) < 0 ? MIN : c;

                 bn = BigDecimal.valueOf(n - i + 1) ;
                curMax = DU.subtract(total, DU.multiply(bn, MIN));
                curMax = curMax.compareTo(MAX) > 0 ? MAX : curMax;
                c = c.compareTo(curMax) > 0 ? curMax : c;

                sum = DU.add(sum, c);
            }
            gifts.add(DU.format(c)) ;

        }
//        System.out.println(Arrays.toString(gifts));
        return gifts;
    }

    public static void main(String[] args) throws InterruptedException {
        LuckyMoneyService s = new LuckyMoneyService();
        BigDecimal sum ;
        List<String> gifts;
        double money = 88;
        int n = 100;
        int times = 10000;
        for (int i = 0; i < times; i++) {
            gifts = s.randomAllocateMoney(money, n);
        sum = s.sumGifts(gifts);
        System.out.println(sum);
        Assert.isTrue(sum.compareTo(BigDecimal.valueOf(money)) == 0);
//        Thread.sleep(500);
        }
    }

    public BigDecimal sumGifts(List<String> gifts) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 0, size = gifts.size(); i < size; i++) {
            sum = DU.add(sum, new BigDecimal(gifts.get(i)));
        }
        return sum;
    }

}
