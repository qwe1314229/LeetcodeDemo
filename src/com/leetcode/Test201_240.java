package com.leetcode;

import java.util.Random;

public class Test201_240 {
	public static void main(String[] args) {
		new Test201_240().testFindKthLargest1();
	}

	/**
	 * leetcode 215题解答
	 * 
	 * 数组中第K个最大元素- 解法3，通过计数排序查找测试
	 * */
	public void testFindKthLargest2() {
		//int[] nums= {3,2,1,5,6,4}; 
		int[] nums= {3,1,2,4};
		int k = 2;
		int ans = findKthLargest2(nums, k);
		System.out.println(ans);
    }

	/**
	 * leetcode 215题解答
	 * 
	 * 数组中第K个最大元素- 解法3，通过计数排序查找
	 * */
	public int findKthLargest2(int[] nums, int k) {
		int count[] = new int[20001];
		for (int i = 0; i < nums.length; i++) {
			int value = nums[i] + 10000;
			count[value]++;
		}
		for (int i = count.length - 1; i >= 0; i--) {
			k = k - count[i];
			if(k <= 0){
				return i-10000;
			}
		}
		return 0;
    }

	/**
	 * leetcode 215题解答
	 * 
	 * 数组中第K个最大元素- 解法2，通过堆排序查找测试
	 * */
	public void testFindKthLargest1() {
		//int[] nums= {3,2,1,5,6,4}; 
		int[] nums= {3,1,2,4};
		int k = 2;
		int ans = findKthLargest1(nums, k);
		System.out.println(ans);
    }

	/**
	 * leetcode 215题解答
	 * 
	 * 数组中第K个最大元素- 解法2，通过堆排序查找
	 * */
	public int findKthLargest1(int[] nums, int k) {
		buildMapHeap(nums);
        int n = nums.length;
        int headSize = n - 1;
        for (int i = n - 1; i > n - k; i--) {
			swap(nums, 0, i);
			headSize--;
			maxHeap(nums, 0, headSize);
		}
		return nums[0];
    }

	private void buildMapHeap(int[] nums){
		int n = nums.length;
		int k = n/2 - 1;
		for (int i = k; i >= 0; i--) {
			maxHeap(nums, i, n-1);
		}
	}

	private void maxHeap(int[] nums, int i, int headSize){
		int left = 2 * i + 1;
		while(left <= headSize){
			if(left + 1 <= headSize && nums[left] < nums[left + 1]){
				left = left + 1;
			}
			if(nums[i] < nums[left]){
				swap(nums, i, left);
				i = left;
				left = 2 * i + 1;
			}else {
				break;
			}
		}
	}

	/**
	 * leetcode 215题解答
	 * 
	 * 数组中第K个最大元素 - 解法1，通过快速查找测试
	 * */
	public void testFindKthLargest() {
		//int[] nums= {3,2,1,5,6,4}; 
		int[] nums= {3,2,3,1,2,4,5,5,6};
		int k = 5;
		int ans = findKthLargest(nums, k);
		System.out.println(ans);
    }

	/**
	 * leetcode 215题解答
	 * 
	 * 数组中第K个最大元素 - 解法1，通过快速查找
	 * */
	public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
		return topKSplit(nums, 0, n - 1, n - k + 1);
    }

	private int topKSplit(int[] nums, int left,int right, int k){
		int mid = quickQuery(nums, left, right);
		if(mid == k - 1){
			return nums[mid];
		}else if(mid > k - 1){
			return topKSplit(nums, left, mid - 1, k);
		} else {
			return topKSplit(nums, mid + 1, right, k);
		}
	}

	private int quickQuery(int[] nums, int left,int right){
		if(left == right ){
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
	
	private void swap(int []nums, int m,int n){
		int temp = nums[m];
		nums[m] = nums[n];
		nums[n] = temp;
	}
}
