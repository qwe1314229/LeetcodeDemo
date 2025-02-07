package com.leetcode;

import java.util.Random;

public class Test881_920 {
	public static void main(String[] args) {
		new Test881_920().testSortArray1();
	}
	/**
	 * leetcode 912题解答
	 * 
	 * 排序数组 - 解法1，通过堆排序测试
	 * */
	public void testSortArray1() {
		int[] nums = {5,2,6};
		int []ans = sortArray1(nums);
		for (int i = 0; i < ans.length; i++) {
			System.out.print(ans[i] + " ");
		}
    }

	/**
	 * leetcode 912题解答
	 * 
	 * 排序数组 - 解法1，通过堆排序
	 * */
	public int[] sortArray1(int[] nums) {
		buildMaxHeap(nums);
		for (int i = nums.length - 1; i >= 0; i--) {
			swap(nums, 0, i);
			maxHeap(nums, 0, i-1);
		}
		return nums;
    }

	private void buildMaxHeap(int[] nums){
		int k = nums.length/2 - 1;
		for (int i = k; i >= 0; i--) {
			maxHeap(nums, i, nums.length - 1);
		}
	}

	private void maxHeap(int[] nums, int i, int headSize){
		int left = 2 * i + 1;
		while(left <= headSize) {
			if(left + 1 <= headSize && nums[left] < nums[left+1]){
				left = left + 1;
			}
			if(nums[i] < nums[left]){
				swap(nums, i, left);
				i = left;
				left = 2 * i + 1;
			}else{
				break;
			}
		}
		
	}

	/**
	 * leetcode 912题解答
	 * 
	 * 排序数组 - 解法1，通过快速排序测试
	 * */
	public void testSortArray() {
		int[] nums = {5,2,3,1};
		int []ans = sortArray(nums);
		for (int i = 0; i < ans.length; i++) {
			System.out.print(ans[i] + " ");
		}
    }

	/**
	 * leetcode 912题解答
	 * 
	 * 排序数组 - 解法1，通过快速排序
	 * */
	public int[] sortArray(int[] nums) {
		quickSort(nums, 0, nums.length - 1);
		return nums;
    }

	private void quickSort(int[] nums,int left,int right){
		if(left < right){
			int povit = randomizedPartition(nums, left, right);
			quickSort(nums, left, povit - 1);
			quickSort(nums, povit + 1, right);
		}
	}

	private int randomizedPartition(int[] nums,int left,int right){
		if(left >= right){
			return left;
		}
		int povitIndex = left + new Random().nextInt(right - left);
		swap(nums, left, povitIndex);
		int povit = nums[left];
		while(left < right){
			while(left < right && nums[right] > povit){
				right--;
			}
			if(left < right){
				nums[left] = nums[right];
				left++;
			}
			while(left < right && nums[left] < povit){
				left++;
			}
			if(left < right){
				nums[right] = nums[left];
				right--;
			}
		}
		nums[left] = povit;
		return left;		
	}
	
	private void swap(int[] nums,int m , int n){
		int temp = nums[m];
		nums[m] = nums[n];
		nums[n] = temp;
	}
}
