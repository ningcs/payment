package com.example.demo.test;


import com.example.demo.test.entity.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class main {
    public static void main(String[] args)throws Exception {
//        int[] ints = new int[2];
//        int[] nums = {3, 2, 4};
//        int target = 6;
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = 0; j < nums.length; j++) {
//                if (i != j && (nums[i] + nums[j]) == target) {
//                    ints[0] = i;
//                    ints[1] = j;
//                }
//            }
//        }

//        String s="aabbcczgksvc";
//        int n = s.length();
//        Set<Character> set = new HashSet<>();
//        int ans = 0, i = 0, j = 0;
//        while (i < n && j < n) {
//            // try to extend the range [i, j]
//            if (!set.contains(s.charAt(j))){
//                set.add(s.charAt(j++));
//                ans = Math.max(ans, j - i);
//            }
//            else {
//                set.remove(s.charAt(i++));
//            }
//        }
//        System.out.println(ans);

//        //选择排序 从小到大排序 每次选择最小一个放在数组里面 ，直到剩下最后一个元素。
//        int[] nums = {3, 2, 4,9,8};
//        for(int i=0;i<nums.length-1;i++){
//          int   min=i; //每趟排序最小值先等于第一个数，遍历剩下的数
//            for (int j=i+1;j<nums.length;j++){
//                if (nums[min]>nums[j]){
//                    min=j; //获取最小数的下标
//                }
//            }
//            //如果和第一个赋值的下标不同，则跟第一个数交换位置
//            if (min!=i){
//                int temp=nums[min];
//                nums[min]=nums[i];
//                nums[i]=temp;
//            }
//        }
//        for (int i = 0; i < nums.length; i++) {
//            System.out.print(nums[i] + " ");
//        }
//        // 插入排序  后面的数依次跟前面比较  小的放前面  例 2跟3 比较 ，4跟 2,3比较
//        int[] nums = {3, 2, 4, 9, 8};
//        int j ;
//        for (int i = 1; i < nums.length; i++) {
//            int temp = nums[i];
//            j = i - 1;
//            while (j > -1 && temp < nums[j]) {
//                nums[j + 1] = nums[j];
//                j--;
//            }
//            nums[j + 1] = temp;
//
//        }
//        for (int i = 0; i < nums.length; i++) {
//            System.out.print(nums[i] + " ");
//        }
        int[] nums = {3,2,4,9,8,13,5,2,9};
        funMergeSort(nums);
    }
    //合并两个数组
    static int[] merge(int[] list1, int[] list2) {

        int[] list3 = new int[list1.length + list2.length];

        int count1 = 0;
        int count2 = 0;
        int count3 = 0;

        while (count1 < list1.length && count2 < list2.length) {

            if (list1[count1] < list2[count2]) {
                list3[count3++] = list1[count1++];
            } else {
                list3[count3++] = list2[count2++];
            }
        }

        while (count1 < list1.length) {
            list3[count3++] = list1[count1++];
        }

        while (count2 < list2.length) {
            list3[count3++] = list2[count2++];
        }
        for (int i = 0; i < list3.length; i++) {
            System.out.print(list3[i] + " ");
        }
        return list3;

    }


    //合并两个数组 int[] nums = {3,2,4,9,8,13,5,2,9};
    static void funMergeSort(int[] nums) {
//
        if (nums.length > 1) {

            int length1 = nums.length / 2;
            int[] array1 = new int[length1];
            System.arraycopy(nums, 0, array1, 0, length1);
            funMergeSort(array1);

            int length2 = nums.length - length1;
            int[] array2 = new int[length2];
            System.arraycopy(nums, length1, array2, 0, length2);
            funMergeSort(array2);

            int[] datas = merge(array1, array2);
            System.arraycopy(datas, 0, nums, 0, nums.length);

        }
    }
}
