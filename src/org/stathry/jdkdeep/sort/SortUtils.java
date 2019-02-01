package org.stathry.jdkdeep.sort;

import java.util.Random;

/**
 * TODO
 * 
 * @author dongdaiming
 * @date 2017年9月11日
 */
public class SortUtils {

    public static void main(String[] args) {
        // int[] a = new int[100];
        // randomA(a);
        int[] a = new int[] {3, 2, 8, 1};
        printa(a);
        //
//        quickSort(a);
        // insertSort(a);
        // selectSort(a);
        // mergeSort(a);
        bubbleSort(a);
        printa(a);
        // testMerge();
        // testMerge0();
    }

    public static void bubbleSort(int []arr) {
        for(int i =0;i<arr.length-1;i++) {
            for(int j=0;j<arr.length-i-1;j++) {//-1为了防止溢出
                if(arr[j]>arr[j+1]) {
                    int temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }

    public static void quickSort(int[] a) {
        quickSort0(a, 0, a.length - 1);
    }

    // 3, 2, 8, 1
    private static void quickSort0(int[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int i = low, j = high, ref = a[low];
        while (i < j) {
            while (i < j && a[j] >= ref) {
                j--;
            }
            if (i < j) {
                a[i] = a[j];
                i++;
            }
            while (i < j && a[i] <= ref) {
                i++;
            }
            if (i < j) {
                a[j] = a[i];
                j--;
            }
        }
        a[i] = ref;
        quickSort0(a, low, i - 1);
        quickSort0(a, j + 1, high);
    }

    public static void insertSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    swap(a, j - 1, j);
                }
                // 因为前面的序列都是有序的，肯定比a[j]小，所以直接跳出循环
                else {
                    break;
                }
            }
        }
    }

    public static void selectSort(int[] a) {
        if (a == null || a.length < 1) {
            return;
        }
        for (int i = 0; i < a.length - 1; i++) {
            int j = a.length - 1;
            int iMin = i;
            for (; j > i; j--) {
                if (a[j] < a[iMin]) {
                    iMin = j;
                }
            }
            if (i != iMin) {
                swap(a, i, iMin);
            }
        }
    }

    public static void mergeSort(int[] a) {
        int[] t = new int[a.length];
        mergeSort0(a, 0, a.length - 1, t);
    }

    private static void mergeSort0(int[] a, int l, int h, int[] t) {
        if (l < h) {
            int m = (h + l) >> 1;
            mergeSort0(a, l, m, t);
            mergeSort0(a, m + 1, h, t);
            mergeArray(a, l, m, h, t);
        }
    }

    private static void mergeArray(int[] a, int l, int m, int h, int[] t) {
        int k = 0, i = l, j = m + 1;
        while (i <= m && j <= h) {
            t[k++] = (a[i] < a[j]) ? a[i++] : a[j++];
        }
        while (i <= m) {
            t[k++] = a[i++];
        }
        while (j <= h) {
            t[k++] = a[j++];
        }
        for (int x = 0; x < k; x++) {
            a[l + x] = t[x];
        }
    }

    private static void swap(int[] a, int i1, int i2) {
        int temp = a[i1];
        a[i1] = a[i2];
        a[i2] = temp;
    }

    private static void merge(int[] a1, int[] a2, int[] a) {
        int l1 = a1.length;
        int l2 = a2.length;
        int i, j, k;
        i = j = k = 0;
        while (i < l1 && j < l2) {
            if (a1[i] < a2[j]) {
                a[k++] = a1[i++];
            } else {
                a[k++] = a2[j++];
            }
        }

        while (i < l1) {
            a[k++] = a1[i++];
        }
        while (j < l2) {
            a[k++] = a2[j++];
        }
    }

    public static void testMerge() {
        int[] a1 = new int[] { 1, 3, 5, 7 };
        int[] a2 = new int[] { 0, 2, 4, 6, 8, 10 };
        int[] a = new int[a1.length + a2.length];
        merge(a1, a2, a);

        printa(a);
    }

    public static void testMerge0() {
        // int[] a = new int[]{0,2,4,6,8,10};
        // int[] t = new int[4];
        // merge0(a, 2, 4, 5, t);

        // printa(t);
    }

    public static void randomA(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = new Random().nextInt(100);
        }
    }

    public static void printa(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + "  ");
        }
        System.out.println();
    }

}
