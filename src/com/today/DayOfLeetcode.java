package com.today;

import java.util.Arrays;
import java.util.PriorityQueue;

public class DayOfLeetcode {
	public static void main(String[] args) {
		testMinimumCost();
	}

	public static void testIsSubstringPresent() {
		boolean ans = isSubstringPresent("");
		System.out.println(ans);
	}

	public static boolean isSubstringPresent(String s) {
		
		return false;
    }

	public static void testMinimumCost() {
		int m = 6,n=3;
		int [] horizontalCut = new int[]{2,3,2,3,1};
		int [] verticalCut = new int[]{1,2};
		int min = minimumCost1(m, n, horizontalCut, verticalCut);
		System.out.println(min);
	}

	public static int minimumCost1(int m, int n, int[] horizontalCut, int[] verticalCut) {
		Arrays.sort(horizontalCut);
		Arrays.sort(verticalCut);
		
		int x =1, y = 1,ans = 0;
		while(m >= 2 &&  n >= 2){
			if(horizontalCut[m-2] > verticalCut[n-2]){
				ans = ans + horizontalCut[m-2] * x;
				y++;
				m--;
			}else{
				ans = ans + verticalCut[n-2] * y;
				x++;
				n--;
			}
		}

		while(m >= 2){
			ans = ans + horizontalCut[m-2] * x;
			m--;
		}

		while(n >= 2){
			ans = ans + verticalCut[n-2] * y;
			n--;
		}
		return ans;
    }

	/**
	 * 2024-12-25 leetcode 每日一题   3219.切蛋糕的最小总开销
	 * 第一次打算用 动态规划算法，结果发现结果是有问题的， 改用minimumCost1 方法的贪心算法，
	 * */
	public static int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        int [][] sum = new int[m+1][n+1];
        
        // 每个数组至少被调用一次，
		//int min = 0;
		if(m == 1 && n == 1){
			return 0;
		}
		int sumM = 0; 
		// 代表着长n为1，高m为 i 矩形蛋糕的水平分割总开销
		for (int i = 2; i < m+1; i++) {
			sumM = sumM + horizontalCut[i-2];
			sum[i][1] = sumM;
		}
		
		int sumN = 0;
		// 代表着长n为i，高m为 1 矩形蛋糕的 垂直分割总开销
		for (int i = 2; i < n+1; i++) {
			sumN = sumN + verticalCut[i-2];
			sum[1][i] = sumN;
		}
		if(m == 1){
			return sum[1][n-1];
		}
		if(n == 1){
			return sum[m-1][n];
		}
		
		// 长n为1，高m为 1 矩形蛋糕的 水平分割总开销为0
		//sum[1][1] = 0;
		
		//sum[2-1][1-1] = 1;
		for (int i = 2; i < m+1; i++) {
			for (int j = 2; j < n+1; j++) {
				int x = sum[i-1][j-1];
				int y = sum[i][1];
				int z = sum[1][j];
				int w = Math.min(horizontalCut[i-2], verticalCut[j-2]);
				//sum[i][j] = sum[i-1][j-1] + area[0][i-1] + area[1][j-1] + Math.min(horizontalCut[i-1], verticalCut[j-1]);
				sum[i][j] = x + y + z + w;
			}
		}
		return sum[m][n];
    }

	public static void testEatenApples(){
		int[] apples = {3,1,1,0,0,2};
		int[] days = {3,1,1,0,0,2};
		int k = eatenApples(apples, days);
		System.out.println(k);
	}
	
	public static int eatenApples(int[] apples, int[] days) {
		int ans = 0;
		// 创建一个优先队列，存储所有未腐烂的苹果， int[]数组 第一位存腐烂的日期，第二位存 腐烂的苹果数量
		PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> a[0] - b[0]);
		// 计算在n（等于apples.length） 天内吃的苹果 
		for (int i = 0; i < apples.length; i++) {
			// 判断一下当前 优先队列的节点对应的苹果有没有 腐烂，如果腐烂了，就丢弃掉，
			while(!queue.isEmpty() && queue.peek()[0] <= i){
				queue.poll();
			}
			// 取出当天生产的苹果数
			int apple = apples[i];
			// 算出当天生产苹果对应的腐烂日期
			int day = days[i] + i;
			if(apple > 0){
				queue.offer(new int[]{day,apple});
			}
			
			if(!queue.isEmpty()){
				// 取出当前 优先队列的头节点，
				int result[] = queue.peek();
				// 将当前腐烂日期的苹果数量减一
				result[1] = result[1] - 1; 
				// 如果苹果数量 减一后等于0，说明当前数组对应的苹果已吃完，可以从优先队列中 删除该元素， 
				if(result[1] == 0){
					queue.poll();
				}
				ans++;
			}
		}
		int k = apples.length;
		// 计算在n天后吃的苹果
		while(!queue.isEmpty()){
			// 去掉第k天腐烂的苹果数组元素
			while(!queue.isEmpty() && queue.peek()[0] < k){
				queue.poll();
			}
			if(queue.isEmpty()){
				return ans;
			}
			// 当前苹果数组元素 过期的天数
			int m = queue.peek()[0] - k;
			// 当前苹果数组元素 苹果的数量
			int n = queue.peek()[1];
			// 删除优先队列中该元素
			queue.poll();
			// 取m,n的其中较小的元素作为可吃苹果的数量
			m = Math.min(m, n);
			k = k + m;
			ans = ans + m;
		}
		return ans;
    }
}
