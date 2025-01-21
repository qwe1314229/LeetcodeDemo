package com.leetcode;

public class Test141_160 {
	public static void main(String[] args) {
		testFindMin1();
	}

	/**
	 * leetcode 154题测试
	 * 
	 * */
	public static void testFindMin1() {
		int[] nums = {2,2,2,1,1,1,1,1,1,1};
		int min = findMin1(nums);
		System.out.println(min);
    }

	/**
	 * leetcode 154题解答
	 * 
	 * 
	 * 发现旋转数组中的最小值.旋转数组有重复值
	 * */
	public static int findMin1(int[] nums) {
		// 如果第一位小于最后一位，说明此数组没有旋转，是升序排列的数组，直接返回第一个值即是最小值
		if(nums[0] < nums[nums.length - 1]){
			return nums[0];
		}
		
        int left = 0;
        int right = nums.length-1;
        while(left < right){
        	// int mid = (right + left)/2;
        	// right + left 可能会溢出，改为下面这种写法
        	int mid = left + (right - left)/2;
			if(nums[mid] < nums[right]){
				right = mid;
			} else if(nums[mid] > nums[right]){
				left = mid + 1;
			} else{
				right--;
			}
		}
		return nums[right];
    }

	/**
	 * leetcode 33 题 第一种解法用到了 153题的结果，所以先处理153题
	 * 
	 * */
	public static void testFindMin() {
		int[] nums = {3,4,5,1,2};
		int min = findMin(nums);
		System.out.println(min);
    }

	/**
	 * leetcode 153题解答
	 * leetcode 33 题 第一种解法用到了 153题的结果，所以先处理153题
	 * 
	 * 发现旋转数组中的最小值.旋转数组无重复值
	 * */
	public static int findMin(int[] nums) {
		// 如果第一位小于最后一位，说明此数组没有旋转，是升序排列的数组，直接返回第一个值即是最小值
		// 如果第一位等于最后一位，说明这个数组的最后一位就是第一位，即，数组长度为一，也直接返回数组唯一的第一个值即可
		if(nums[0] <= nums[nums.length - 1]){
			return nums[0];
		}
		
        int left = 0;
        int right = nums.length-1;
        while(left < right){
        	// int mid = (right + left)/2;
        	// right + left 可能会溢出，改为下面这种写法
        	int mid = left + (right - left)/2;
			if(nums[mid] < nums[right]){
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return nums[right];
    }
}
