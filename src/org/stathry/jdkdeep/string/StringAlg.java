package org.stathry.jdkdeep.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/**
 * TODO
 */
public class StringAlg {
    
    public static int countWord(String s) {
        if(s == null || s.trim().length() == 0) {
            return 0;
        }
        s = s.trim();
        char[] a = s.toCharArray();
        int len = a.length;
        int count = 0;
        for(int i = 0; i < len; i++) {
            if(!Character.isLetter(a[i]) && (i - 1) > 0 && Character.isLetter(a[i-1])) {
                count++;
            }
            if(Character.isLetter(a[i])) {
                continue;
            }
        }
        count++;
        return count;
    }
    
    // abc
    // abc,acb,bac,bca,cab,cba
    // abc bac cba acb
    public static List<String> combAlph(String s) {
        if(s == null || s.trim().length() == 0) {
            return Collections.emptyList();
        }
        Set<Character> chars = new HashSet<>();
        s = s.trim();
        int len = s.length();
        for(int i = 0; i < len; i++) {
            chars.add(s.charAt(i)); 
        }
        Character[] ca = new Character[chars.size()];
        chars.toArray(ca);
        
        List<String> list = new ArrayList<>();
        for(int i = 0; i < ca.length; i++) {
            StringBuilder temp = new StringBuilder(ca[i]);
            for(int j = i + 1; j < ca.length; j++) {
                temp.append(ca[j]);
            }
        }
        return Collections.emptyList();
    }
    
    /** 
     * 对字符串进行全排列 
     * @param str 
     * @param startIndex 
     */  
    public static void permutation(char[] str, int startIndex){    
        if(str == null || startIndex < 0 || startIndex > str.length){    
            return;    
        }    
        if( startIndex == str.length ){    
            System.out.println(new String(str));    
        }else{    
            for(int j = startIndex; j < str.length; j++){    
//                //交换前缀,使之产生下一个前缀    
//                swap(str, startIndex, j);  
//                permutation(str, startIndex+1);    
//                //将前缀换回来,继续做上一个的前缀排列.    
//                swap(str, startIndex, j);  
            }    
        }    
    }    
    
    @Test
    public void test1() {
        String s = "JDK this   page     dont take";
        int n = countWord(s);
        System.out.println(n);
        
    }
    
    @Test
    public void test2() {
        int i = 0;
       for(; i < 10; i++) {
           if(i == 5) {
               break;
           }
       }
       System.out.println(i);
    }
    
    @Test
    public void test3() {
        int i = 0;
        for(; i < 10; i++) {
            if(i == 5) {
                continue;
            }
        }
        System.out.println(i);
    }
    @Test
    public void test4() {
        int i = 0;
        for(; i < 10; i++) {
        }
        System.out.println(i);
    }

}
