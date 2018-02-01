package org.bryadong.jdkdeep.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/**
 * TODO
 */
public class StringPermutation {

    // 题目：输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
    // 解题思路：
    // 1.把字符串分成两部分，一部分是字符串的一个字符，另一部分是除此字符外的所有字符。递归求另一部分字符的排列。
    // 2.拿第一个字符和它后面的字符逐个交换。注：交换完还要换回去
    public static List<String> permutation(String s) {
        if (s == null || s.trim().length() == 0) {
            return Collections.emptyList();
        }

        Set<String> rset = new HashSet<>();
        List<String> rlist = new ArrayList<>();

        permutation0(rset, s.trim().toCharArray(), 0);

        rlist.addAll(rset);
        Collections.sort(rlist);

        return rlist;
    }

    private static void permutation0(Set<String> rset, char[] a, int i) {
        // 得到结果
        if (i == a.length) {
            rset.add(new String(a));
            return;
        }
        for (int j = 0; j < a.length; j++) {
            swap(a, i, j);
            permutation0(rset, a, i + 1);
            // 回溯
            swap(a, i, j);
        }
    }
   

    private static void swap(char[] a, int i, int j) {
        if (i == j) {
            return;
        }
        char t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    @Test
    public void test() throws Exception {
        List<String> list = permutation("abc");
        System.out.println(list);
    }
}
