package org.stathry.jdkdeep.java8;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

/**
 * TODO
 * 
 * @author dongdaiming
 * @date 2017年12月4日
 */
public class StreamTest {

    @Test
    public void testFilter() {
        System.out.println(1 & 2);
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = list.stream().filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer n) {
                return (n.intValue() & 1) != 1;
            }
        });
        List<Integer> list2 = stream.collect(Collectors.toList());

        System.out.println(list2);
        Assert.assertEquals(2, list2.size());
        Assert.assertEquals(4, list2.get(1).intValue());
    }

	@Test
	public void testSum0() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		int sum1 = list.stream().reduce(0, new BinaryOperator<Integer>() {

			@Override
			public Integer apply(Integer t, Integer u) {

				return t + u;
			}
		}).intValue();

		int sum2 = list.stream().reduce(0, (r, e) -> r + e);

		int sum3 = Stream.of(1, 2, 3, 4, 5).reduce((r, e) -> r + e).get();
		Assert.assertEquals(sum1, 15);
		Assert.assertEquals(sum2, 15);
		Assert.assertEquals(sum3, 15);
	}

	@Test
	public void testSum1() {
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map1 = new HashMap<>();
		map1.put("name", "ddm1");
		map1.put("sex", 1);
		map1.put("salary", 26000.66);
		Map<String, Object> map2 = new HashMap<>();
		map2.put("name", "ddm2");
		map2.put("sex", 1);
		map2.put("salary", 36000.66);
		Map<String, Object> map3 = new HashMap<>();
		map3.put("name", "ddm3");
		map3.put("sex", 0);
		map3.put("salary", 16000.66);

		list.add(map1);
		list.add(map2);
		list.add(map3);

		double sum = list.stream().filter((map) -> (int) map.get("sex") == 1)
				.mapToDouble((map) -> (double) map.get("salary")).sum();
		System.out.println(sum);
	}

    @Test(expected = IllegalStateException.class)
    public void testSum2() {
        // 每个流只能消费一次，所以如果要做多种操作就需要多次构造流
        IntStream s1 = IntStream.of(1, 3, 4);
        System.out.println(s1.sum());
        System.out.println(s1.max().getAsInt());
    }

	@Test
    public void testSum3() {
        List<BigDecimal> gifts = Arrays.asList(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN);
        BigDecimal sum = gifts.stream().reduce( (a, b) -> a.add(b)).get();
        System.out.println(sum);
        Assert.assertEquals(11.0, sum.doubleValue(), 0);
    }

	@Test
	public void testIntStream() {
		// 每个流只能消费一次，所以如果要做多种操作就需要多次构造流
		IntStream s1 = IntStream.of(1, 3, 4);
		System.out.println(s1.sum());

		IntStream s2 = IntStream.of(1, 3, 4);
		System.out.println(s2.max().getAsInt());

		IntStream s3 = IntStream.of(1, 3, 4);
		System.out.println(s3.average().getAsDouble());
	}


	
	@Test
	public void testMap() {
		List<String> words = Arrays.asList("ali", "gg");
//		List<String> r = words.stream().map((word) -> word.toUpperCase()).collect(Collectors.toList());
		System.out.println(words.stream().map((word) -> word.toUpperCase()));
//		System.out.println(r);
	}
	
	@Test
	public void testForeach() {
		List<String> words = Arrays.asList("ali", "gg");
		words.stream().forEach((e) -> System.out.println(e));
	}
	
	@Test
	public void testReduce() {
		List<Integer> a = Arrays.asList(8, 3, 6, 9);
		System.out.println(a.stream().reduce((r, e) -> r + e).get());
		System.out.println(Stream.of("a", "b", "c").reduce("", String::concat));
	}
	
	@Test
	public void testLimit() {
		List<Integer> a = Arrays.asList(8, 3, 6, 9, 10);
		System.out.println(a.stream().limit(2).collect(Collectors.toList()));
		System.out.println(a.stream().limit(2).skip(1).collect(Collectors.toList()));
		System.out.println(a.stream().skip(1).limit(2).collect(Collectors.toList()));
	}
	
	@Test
	public void testSort() {
		List<Integer> a = Arrays.asList(8, 3, 6, 9, 10);
		System.out.println(a.stream().limit(4).sorted().collect(Collectors.toList()));
	}
	
	@Test
	public void testMax() {
		List<Integer> a = Arrays.asList(8, 3, 6, 9, 10);
		System.out.println(a.stream().limit(4).max((t1, t2) -> t1.compareTo(t2)).get());
	}
	
	@Test
	public void testDistinct() {
		List<String> a2 = Arrays.asList("a", "a", "b", "c", "b");
		System.out.println(a2.stream().distinct().collect(Collectors.toList()));
	}
	
	@Test
	public void testMatch() {
		List<Integer> a = Arrays.asList(8, 3, 6, 9, 10);
		assertTrue(a.stream().anyMatch((e) -> e % 2 == 0));
		assertFalse(a.stream().allMatch((e) -> e % 2 == 0));
		assertTrue(a.stream().noneMatch((e) -> e > 12));
	}

    @Test
    public void testAvg() {
	    int size = 5;
	    List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        double avg1 = list.stream().mapToInt(n -> n).average().getAsDouble();
        System.out.println(avg1);
    }

    @Test
    public void testAvgStreamVSNormal() {
	    boolean parallel = false;
        int size = 100_0000;
        List<Integer> data;
	    int testTimes = 10;
        List<Long> streamTimes = new ArrayList<>(testTimes);
        List<Double> streamRs = new ArrayList<>(testTimes);
        long start, end;
        double avg;
        for (int i = 0; i < testTimes; i++) {
            data = randomData(size);
            start = System.currentTimeMillis();
            avg = parallel ? data.parallelStream().mapToInt(n -> n).average().getAsDouble() :
                    data.stream().mapToInt(n -> n).average().getAsDouble();
            end = System.currentTimeMillis();
            streamRs.add(avg);
            streamTimes.add(end - start);
        }

        System.out.println("testAvgStream:parallel:" + parallel);
        System.out.println(streamRs);
        System.out.println(streamTimes);
        System.out.println(streamTimes.parallelStream().mapToLong(n -> n).average().getAsDouble());

        List<Long> nTimes = new ArrayList<>(testTimes);
        List<Double> nRs = new ArrayList<>(testTimes);
        int sum1;
        long sum2 = 0,tmp;
        for (int i = 0; i < testTimes; i++) {
            data = randomData(size);
            sum1 = 0;
            start = System.currentTimeMillis();
            for (int j = 0; j < size; j++) {
                sum1 += data.get(j);
            }
            avg = sum1 * 1.0 / size;
            end = System.currentTimeMillis();
            nRs.add(avg);
            tmp = end - start;
            sum2 += tmp;
            nTimes.add(tmp);
        }
        System.out.println("testAvgNormal:");
        System.out.println(nRs);
        System.out.println(nTimes);
        System.out.println(sum2 / testTimes);
    }

    private List<Integer> randomData(int size) {
        SecureRandom random = new SecureRandom();
        List<Integer> data = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            data.add(random.nextInt(10));
        }
        return data;
    }

}
