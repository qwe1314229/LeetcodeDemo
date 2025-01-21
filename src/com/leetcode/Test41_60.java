package com.leetcode;

import java.util.HashSet;
import java.util.Set;

public class Test41_60 {
	public static void main(String[] args) {
		//System.out.println((int)'0');
		multiply();
	}

	public static void multiply() {
		String num1 = "1238";
		String num2 = "456";
		String ans = multiply(num1, num2);
		System.out.println(ans);
    }

	public static String multiply(String num1, String num2) {
		StringBuilder ans = new StringBuilder();
		if("0".equals(num1) || "0".equals(num2)){
			return "0";
		}
		int m = num1.length();
		int n = num2.length();
		int []a = new int [m + n];
        for (int i = m-1; i >= 0; i--) {
        	int a1 = num1.charAt(i) - '0';
			for (int j = n-1; j >= 0; j--) {
				int a2 = num2.charAt(j) - '0';
				a[i + j + 1] = a[i + j + 1] + a1 * a2;
			}
		}
        
		for (int i = a.length - 1; i > 0; i--) {
			 a[i - 1] = a[i - 1] + a[i]/10;
			 a[i] = a[i] % 10;
		}
		int index = 0;
		if(a[0] == 0){
			index = 1;
		}
		
		for (int i = index; i < a.length; i++) {
			ans.append(a[i]);
		}
		return ans.toString();
    }

	public static void testTrap() {
		int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
		int area = trap1(height);
		System.out.println(area);
    }

	public static int trap1(int[] height) {
		if(height.length == 0){
			return 0;
		}
		int ans = 0;
		int left = 0,right = height.length - 1;
		int leftMax = 0;
		int rightMax = 0;
		while(left <= right ){
			leftMax = Math.max(height[left], leftMax);
			rightMax = Math.max(height[right], rightMax);
			if(leftMax <= rightMax){
				ans += leftMax - height[left];
				left++;
			} else {
				ans += rightMax - height[right];
				right--;
			}
		}
		return ans;
    }

	public static int trap(int[] height) {
		if(height.length == 0){
			return 0;
		}
		int leftMax[] = new int[height.length];
		int rightMax[] = new int[height.length];
		leftMax[0] = height[0];
		rightMax[height.length - 1] = height[height.length - 1];
		for (int i = 1; i < height.length; i++) {
			leftMax[i] = Math.max(height[i], leftMax[i-1]);
		}
		for (int i = height.length - 2; i >= 0; i--) {
			rightMax[i] = Math.max(height[i], rightMax[i+1]);
		}
		int area = 0;
        for (int i = 0; i < height.length; i++) {
        	int min = Math.min(leftMax[i], rightMax[i]);
			area = area + min - height[i];	
		}		
		return area;
    }

	public static void testFirstMissingPositive() {
        int []nums = {-1,4,2,1,9,10};
		int ans = firstMissingPositive1(nums);
		System.out.println(ans);
    }

	public static int firstMissingPositive1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
        	// 将temp切换到对应的位置，如temp = 1，将temp 的位置 nums[i]和 nums[1-1]互换
        	while(nums[i] > 0  && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]){
        		int temp = nums[nums[i] - 1];
        		nums[nums[i]-1] = nums[i];
        		nums[i] = temp;
        	}
		}
        for (int i = 0; i < nums.length; i++) {
			if(nums[i] != i + 1){
				return i + 1;
			}
		}
        return nums.length + 1;
    }

	public static int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
        	set.add(nums[i]);
		}
        int k = 1;
        for (int i = 1; i <= nums.length + 1; i++) {
			if(!set.contains(i)){
				k = i;
				break;
				
			}
		}
        return k;
    }
}

