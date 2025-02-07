package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class Test21_40 {
	public static void main(String[] args) {
		//System.out.println((int)'0');
		testReverseKGroup();
	}

	public static void testCombinationSum2() {
		int[] candidates = {2,5,2,1,2};
		int target = 5;
		List<List<Integer>> list = combinationSum2(candidates, target);
		System.out.println(list);
    }

	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
		List<List<Integer>> clist = new ArrayList<>(); 
        List<Integer> list = new ArrayList<>();
        combinationSum22(candidates, 0, target, list, clist);
		return clist;
    }

	public static void combinationSum22(int[] candidates,int idx, int target,List<Integer> list,List<List<Integer>> clist){
		if(target == 0){
			clist.add(new ArrayList<>(list));
			return;
		}
		for (int i = idx; i < candidates.length; i++) {
			if(target < candidates[i]){
				return;
			}
			if(i > idx && candidates[i] == candidates[i-1]){
				continue;
			}
			list.add(candidates[i]);
			combinationSum22(candidates, i+1, target - candidates[i], list, clist);
			Integer n = candidates[i];
			list.remove(n);
		}
	}
	
	public static void combinationSum21(int[] candidates, int target,List<Integer> list,List<List<Integer>> clist, int len){
		if(target == 0){
			list.sort(null);
			for (int i = 0; i < clist.size(); i++) {
				List<Integer> list1 = clist.get(i);
				// 如果已经有了同样的组合，直接返回
				if(list1.size() == list.size() && list1.equals(list)){
					return;
				}
			}
			// 如果没有同样的组合，将该list 加入到list集合中
			List<Integer> list1 = new ArrayList<>();
			list1.addAll(list);
			clist.add(list1);
			return;
		}
		if(target < candidates[0] || len < 0){
			return;
		}
		for (int i = len; i >= 0; i--) {
			int m = candidates[i];
			if(m <= target){
				list.add(m);
				combinationSum21(candidates, target - m, list, clist,i-1);
				Integer n = m;
				list.remove(n);
			}
		}
	}

	public static void testCombinationSum() {
		int[] candidates = {2,3,5,7};
		int target = 8;
		List<List<Integer>> list = combinationSum(candidates, target);
		System.out.println(list);
    }

	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> clist = new ArrayList<>(); 
        List<Integer> list = new ArrayList<>();
        combinationSum1(candidates, target, list, clist);
		return clist;
    }

	public static void combinationSum1(int[] candidates, int target,List<Integer> list,List<List<Integer>> clist){
		if(target == 0){
			list.sort(null);
			for (int i = 0; i < clist.size(); i++) {
				List<Integer> list1 = clist.get(i);
				// 如果已经有了同样的组合，直接返回
				if(list1.size() == list.size() && list1.equals(list)){
					return;
				}
			}
			// 如果没有同样的组合，将该list 加入到list集合中
			List<Integer> list1 = new ArrayList<>();
			list1.addAll(list);
			clist.add(list1);
			return;
		}
		if(target < candidates[0]){
			return;
		}
		for (int i = candidates.length - 1; i >= 0; i--) {
			int m = candidates[i];
			if(m <= target){
				list.add(m);
				combinationSum1(candidates, target - m, list, clist);
				Integer n = m;
				list.remove(n);
			}
		}
	}

	public static void testCountAndSay() {
        String ans = countAndSay(7);
        System.out.println(ans);
		
    }

	public static String countAndSay(int n) {
        if(n == 1) {
        	return "1";
        } else {
        	String ans = countAndSay(n - 1);
        	
        	StringBuffer s = new StringBuffer();
        	// 当ans = 1时，做特殊处理，
        	if(ans.length() == 1){
        		s.append(1).append(ans);
        	}
        	int k = 1;
        	for (int i = 0; i < ans.length() - 1; i++) {
    			if(ans.charAt(i) == ans.charAt(i + 1)){
    				k++;
    			} else {
    				s.append(k).append(ans.charAt(i));
    				k = 1;
    			}
    			// 如果是最后一次循环，将最后一位添加至字符串末尾
    			if(i == ans.length() - 2){
					s.append(k).append(ans.charAt(i + 1));
				}
    		}
        	 
        	return s.toString();
        }
    }

	public static void testSolveSudoku1() {
		char[][] board = {{'.','.','9','7','4','8','.','.','.'},
				{'7','.','.','.','.','.','.','.','.'},
				{'.','2','.','1','.','9','.','.','.'},
				{'.','.','7','.','.','.','2','4','.'},
				{'.','6','4','.','1','.','5','9','.'},
				{'.','9','8','.','.','.','3','.','.'},
				{'.','.','.','8','.','3','.','2','.'},
				{'.','.','.','.','.','.','.','.','6'},
				{'.','.','.','2','7','5','9','.','.'}};
		solveSudoku1(board);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				char cs = board[i][j];
				System.out.print(cs + "  ");
			}
			System.out.println();
		}
		
    }

	public static boolean [][] row = new boolean[9][9];
	
	public static boolean [][] column = new boolean[9][9];
	
	public static boolean [][][] block = new boolean[3][3][9];

	public static List<int[]> spaces = new ArrayList<>(); 

	//public static boolean vaild = false;
	

	/**
	 * 统计法有时候没法解出当前数独，只能修改方法 递归+回溯
	 * 
	 * */
	public static void solveSudoku1(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				char a = board[i][j];
				if(a == '.'){
					spaces.add(new int[]{i,j});
				} else {
					// 变量a的值为字符'9'时，b的值为8
					int b = a - '0' - 1;
					row[i][b] = column[j][b] = block[i/3][j/3][b] = true;
				}
			}
		}
		dfs(board, 0);
    }

	public static boolean dfs(char[][] board, int pos){
		if(pos == spaces.size()){
			return true;
		}
		int []space = spaces.get(pos);
		int x = space[0];
		int y = space[1];
		for (int n = 0; n < 9; n++) {
			if(!row[x][n] && !column[y][n] && !block[x/3][y/3][n]){
				board[x][y] = (char)(n + '0' + 1);
				row[x][n] = column[y][n] = block[x/3][y/3][n] = true;
				if(dfs(board, pos + 1)){
					return true;
				}
				board[x][y] = '.';
				row[x][n] = column[y][n] = block[x/3][y/3][n] = false;
			}
		}
		return false;
	}
	
	public static void testSolveSudoku() {
		char[][] board = {{'5','3','.','.','7','.','.','.','.'}
		,{'6','.','.','1','9','5','.','.','.'}
		,{'.','9','8','.','.','.','.','6','.'}
		,{'8','.','.','.','6','.','.','.','3'}
		,{'4','.','.','8','.','3','.','.','1'}
		,{'7','.','.','.','2','.','.','.','6'}
		,{'.','6','.','.','.','.','2','8','.'}
		,{'.','.','.','4','1','9','.','.','5'}
		,{'.','.','.','.','8','.','.','7','9'}};
		solveSudoku(board);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				char cs = board[i][j];
				System.out.print(cs + "  ");
			}
			System.out.println();
		}
		
    }

	/**
	 * 采用横/纵/3*3宫 统计法，如果出现8个数字，就在当前.位置填入缺少的数字，
	 * 如果循环81次，数独还没填完，说明统计法没法解出当前数独，
	 * 
	 * */
	public static void solveSudoku(char[][] board) {
		// 存储某一行的所有数字
		Map<Integer,Set<Integer>> rmap = new HashMap<>();
		//Set<Character> rset = new HashSet<>();
		// 存储某一列的所有数字
		Map<Integer,Set<Integer>> cmap = new HashMap<>();
		//Set<Character> cset = new HashSet<>();
		// 每个九宫格对应的数组列表
		Map<Integer,Set<Integer>> map = new HashMap<>();
		// 对数独每一行做循环
		for (int i = 0; i < 9; i++) {
			// 对数独每一列做循环
			for (int j = 0; j < 9; j++) {
				// 按每一行 的循环取值
				char rowval = board[i][j];
				
				// 算出i,j在九宫格所属位置对应的set, 此处i代表行，j代表列
				int size = i/3 * 3 + j/3;
				// 取对应九宫格的数字set;
				Set<Integer> s3 = map.get(size);
				if(s3 == null){
					s3 = new HashSet<>();
					map.put(size, s3);
				}
				
				// 取出i行对应的数组值set
				Set<Integer> rset = rmap.get(i);
				if(rset == null){
					rset = new HashSet<>();
					rmap.put(i, rset);
				}
				
				// 取出j列对应的数组值set
				Set<Integer> cset = cmap.get(j);
				if(cset == null){
					cset = new HashSet<>();
					cmap.put(j, cset);
				}
				
				if(rowval != '.'){
					rset.add(rowval-'0');
					s3.add(rowval-'0');
					cset.add(rowval-'0');
				}
			}
		}
		int k = 0;
		// 避免死循环，就算每次只填一个值，也最多循环81次，如果循环81次仍然没有结果，说明此数独无解
		int m = 0;
		while(k < 81 && m < 81){
			m++;
			// 每次循环重置计数器
			k = 81;
			// 对数独每一行做循环
			for (int i = 0; i < 9; i++) {
				// 对数独每一列做循环
				for (int j = 0; j < 9; j++) {
					// 按每一行 的循环取值
					int rowval = board[i][j] - '0';
					Set<Integer> sum = new HashSet<>();
					// 取出i行对应的数组值set
					Set<Integer> rset = rmap.get(i);
					
					// 取出j列对应的数组值set
					Set<Integer> cset = cmap.get(j);
					sum.addAll(rset);
					sum.addAll(cset);
					// 算出i,j在九宫格所属位置对应的set, 此处i代表行，j代表列
					int size = i/3 * 3 + j/3;
					// 取对应九宫格的数字set;
					Set<Integer> s3 = map.get(size);
					sum.addAll(s3);
					// '.'的 unicode编码为46，'0'的unicode编码为48，'.'减去'0'的 int值为-2
					// 此处等于rowval == board[i][j] - '0' == -2就是 等价于 board[i][j] == '.'
					if(rowval == -2){
						if(sum.size() == 8){
							int ans = getValue(sum);
							rset.add(ans);
							cset.add(ans);
							s3.add(ans);
							ans = ans + 48;
							board[i][j] = (char)ans; 
						}else{
							k--;
							continue;
						}
					}
				}
			}
		}
		
    }

	public static int getValue(Set<Integer> sum){
		if(!sum.contains(1)){
			return 1;
		} else if(!sum.contains(2)){
			return 2;
		} else if(!sum.contains(3)){
			return 3;
		} else if(!sum.contains(4)){
			return 4;
		} else if(!sum.contains(5)){
			return 5;
		} else if(!sum.contains(6)){
			return 6;
		} else if(!sum.contains(7)){
			return 7;
		} else if(!sum.contains(8)){
			return 8;
		} else {
			return 9;
		}
		
	}
	
	public static void testIsValidSudoku() {
		char[][] board = {{'5','3','.','.','7','.','.','.','.'}
		,{'6','.','.','1','9','5','.','.','.'}
		,{'.','9','8','.','.','.','.','6','.'}
		,{'8','.','.','.','6','.','.','.','3'}
		,{'4','.','.','8','.','3','.','.','1'}
		,{'7','.','.','.','2','.','.','.','6'}
		,{'.','6','.','.','.','.','2','8','.'}
		,{'.','.','.','4','1','9','.','.','5'}
		,{'.','.','.','.','8','.','.','7','9'}};
		boolean ans = isValidSudoku(board);
		System.out.println(ans);
    }

	
	public static boolean isValidSudoku(char[][] board) {
		// 存储某一行的所有数字
		Set<Character> rset = new HashSet<>();
		// 存储某一列的所有数字
		Set<Character> cset = new HashSet<>();
		// 每个九宫格对应的数组列表
		Map<Integer,Set<Character>> map = new HashMap<>();
		// 校验每一行有没有重复值
		for (int i = 0; i < board.length; i++) {
			char []row = board[i];
			// 取出每一行
			for (int j = 0; j < row.length; j++) {
				
				// 取每一行的值
				char rowval = row[j];
				
				// 取每一列的值
				char colval = board[j][i];
				
				if(rowval != '.'){
					// 算出i,j在九宫格所属位置对应的set, 此处i代表行，j代表列
					int size = i/3 * 3 + j/3 + 1;
					// 取对应九宫格的数字set;
					Set<Character> s3 = map.get(size);
					if(s3 == null){
						s3 = new HashSet<>();
						map.put(size, s3);
					}
					if(rset.contains(rowval) || s3.contains(rowval)){
						return false;
					} else {
						rset.add(rowval);
						s3.add(rowval);
					}
				}
				if(colval != '.'){
					if(cset.contains(colval) ){
						return false;
					} else {
						cset.add(colval);
					}
				}
			}
			cset.clear();
			rset.clear();
		}
        return true;
    }

	public static void testSearchInsert() {
		int[] nums = {0,2,2};
		int target = 1;
		int ans = searchInsert(nums, target);
		System.out.println(ans);
    }

	public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
        	int mid = left + (right - left) / 2;
        	if(target == nums[mid]){
        		return mid;
        	} else if(target < nums[mid]){
        		right = mid - 1;
        	} else if(target > nums[mid]){
        		left = mid + 1;
        	}
        }
		return left;
    }

	public static void testSearchRange() {
		
		int[] nums = {2,2};
		int target = 1;
		int ans[] = searchRange(nums, target);
		for (int i = 0; i < ans.length; i++) {
			System.out.println(ans[i]);
		}
	}
	
	public static int[] searchRange(int[] nums, int target) {
		int start = searchRangeStart(nums, target);
		int end = searchRangeEnd(nums, target);
		int ans[] = {start,end};
        return ans;
    }

	public static int searchRangeStart(int[] nums, int target) {
		if(nums == null || nums.length == 0 || (nums.length == 1 && nums[0] != target)){
			return -1;
		}
		int left = 0,right = nums.length - 1;
        while(left <= right){
        	int k = left + (right - left)/2;
        	if(target > nums[k]){
        		left = k + 1;
    		} else if(target <= nums[k]){
    			right = k - 1;
    		}
        }
        if(left > nums.length - 1){
        	return -1;
        }
		return nums[left] == target ? left : -1;
	}
	
	public static int searchRangeEnd(int[] nums, int target) {
		if(nums == null || nums.length == 0 || (nums.length == 1 && nums[0] != target)){
			return -1;
		}
		int left = 0,right = nums.length - 1;
        while(left <= right){
        	int k = left + (right - left)/2;
        	if(target >= nums[k]){
        		left = k + 1;
    		} else if(target < nums[k]){
    			right = k - 1;
    		}
        }
        if(right < 0){
        	return -1;
        }
        return nums[right] == target ? right : -1;
	}

	public static void testSearch() {
		int[] nums = {2,1};
		int target = 1;
		int ans = search(nums, target);
		System.out.println(ans);
    }

	public static int search(int[] nums, int target) {
		if(nums.length == 1){
			return nums[0] == target ? 0:-1;
		}
        int left = 0,right = nums.length-1;
		while(left <= right){
			int k = left + (right - left)/2;
			// 如果目标值等于 数组K位置的值，那直接返回K位置
			if(target == nums[k]){
				return k;
			}
			// 如果nums[k] >= nums[0] ，说明K左侧是有序递增数组，k右侧是无序数组
        	if(nums[k] >= nums[0]){
        		if(target >= nums[0] && target < nums[k]){
        			right = k - 1;
        		}else {
        			left = k + 1;
        		}
        	// 如果nums[k] < nums[0] ，说明K左侧是无序数组，k右侧是有序递增数组	
        	} else{
        		if(target > nums[k] && target <= nums[nums.length - 1]){
        			left = k + 1;
        		}else {
        			right = k - 1;
        		}
        	}
        }
		return -1;
    }

	public static void testLongestValidParentheses() {
        int len = longestValidParentheses("()(()");
        System.out.println(len);
    }

	public static int longestValidParentheses(String s) {
		if(s == null || s.length() == 0){
			return 0;
		}
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        //int sum = 0;
        // 当次有效长度
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if('(' == c){
				stack.push(i);
			} else{
				stack.pop();
				if(stack.isEmpty()){
					stack.push(i);
				} else{
					max = Math.max(max, i - stack.peek());
				}
			}
		}
		return max;
    }

	public static void testNextPermutation() {
        int []nums = {5,1,1};
        nextPermutation(nums);
        for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
    }

	public static void nextPermutation(int[] nums) {
		if(nums.length <= 1){
			return;
		}
		int i = nums.length - 2;
		while(i >= 0 && nums[i] >= nums[i+1]){
			i--;
		}
		if(i >= 0 ){
			int j = nums.length - 1;
			while(j > i && nums[j] <= nums[i]){
				j--;
			}
			swap(nums,i,j);
		}
		
		reverse(nums, i+1);
        
    }

	public static void reverse(int[] nums,int start){
		int left = start; 
		int right = nums.length - 1;
		while(left < right){
			swap(nums, left, right);
			left++;
			right--;
		}
	}

	public static void swap(int[] nums,int i,int j){
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	public static void testFindSubstring() {
		String s = "ababababab";
		String []words = {"ababa","babab"};
		List<Integer> list = findSubstring1(s, words);
		System.out.println(list);
    }

	public static List<Integer> findSubstring1(String s, String[] words) {
		List<Integer> list = new ArrayList<>();
		
		if(s == null || s.length() == 0 || words.length == 0 || words[0].length() == 0){
			return list;
		}
		int wordNum = words.length;
		int wordLen = words[0].length();
		int sumLen = wordNum * wordLen;
		if(s.length() < sumLen){
			return list;
		}
		for (int i = 0; i < wordLen; i++) {
			Map<String, Integer> differ = new HashMap<String, Integer>();
			if(i > s.length() - sumLen){
				break;
			}
			// 将 字符串s第一个滑动窗口的 所有 按words 的 单个字符串的长度分组
			for (int j = i; j < i + sumLen; j = j + wordLen) {
				String word = s.substring(j,j+wordLen);
				differ.put(word, differ.getOrDefault(word, 0) + 1);
			}
			// 减去 字符串数组 works中的所有字符串
			for (int j = 0; j < words.length; j++) {
				String word = words[j];
				differ.put(word, differ.getOrDefault(word, 0) - 1);
				if(differ.get(word) == 0){
					differ.remove(word);
				}
			}
			for (int j = i; j < s.length() - sumLen + 1; j = j + wordLen) {
				if(j != i){
					// 向右滑动works 数组中 单个字符串元素的长度， 右边新增的字符串元素值
					String right = s.substring(j + sumLen - wordLen, j + sumLen);
					differ.put(right, differ.getOrDefault(right, 0) + 1);
					if(differ.get(right) == 0){
						differ.remove(right);
					}
					
					// 向右滑动works 数组中 单个字符串元素的长度， 左边应减去的 字符串元素值
					String left = s.substring(j - wordLen, j);
					differ.put(left, differ.getOrDefault(left, 0) - 1);
					if(differ.get(left) == 0){
						differ.remove(left);
					}				
				}
				if(differ.isEmpty()){
					list.add(j);
				}
			}
			
		}
		return list;
	}
	
	/***
	 * 此种写法在测试用例170直接超时了，通不过leetcode，只能放弃
	 * 
	 * */
	public static List<Integer> findSubstring(String s, String[] words) {
		HashSet<Integer> slist = new HashSet<>();
		List<Integer> list = new ArrayList<>();
		
		//int len = words.length;
        //int sumLen = len * words[0].length();
        // 如果字符串 数组中 有某一个字符串 不是 s 的子串，那么words所有串联子串都不是  s的子串，直接返回空数组
        for (int i = 0; i < words.length; i++) {
			if(!s.contains(words[i])){
				return list;
			}
		}
		List<String> w = Arrays.asList(words);
		List<String> wlist = new ArrayList<>(w);
		
		findSubstring(s, wlist, "", null, slist);
		
		list.addAll(slist);
		return list;
    }

	public static void findSubstring(String s, List<String> words, String result, String word, Set<Integer> slist){
		List<String> wlist = new ArrayList<>(words);
		if(word != null){
			wlist.remove(word);
		}
		if(s.indexOf(result) == -1){
			return;
		}
		if(wlist.size() == 0){
			int index = s.indexOf(result);
			String temp = s;
			int k = index;
			while(index > -1 && temp.length() >= (index + 1)){
				slist.add(k);
				temp = temp.substring(index + 1);
				index = temp.indexOf(result);
				k = k + index + 1;
			}
		} else{
			for (int i = 0; i < wlist.size(); i++) {
				findSubstring(s, wlist, result + wlist.get(i), wlist.get(i), slist);
			}
		}
	}

	public static void testDivide() {
        System.out.println(divide(2147483647, 1));
    }

	public static int divide(int dividend, int divisor) {
		if(dividend == 0 || divisor == 0){
			return 0;
		}
		if(dividend == Integer.MIN_VALUE){
			if(divisor == -1){
				return Integer.MAX_VALUE;
			} else if(divisor == 1){
				return Integer.MIN_VALUE;
			}
			
		}
		if(divisor == Integer.MIN_VALUE){
			if(dividend == Integer.MIN_VALUE){
				return 1;
			}else{
				return 0;
			}
		}
		// 表示结果是负数还是正数
		boolean isNegativeNumber = false;
		// 将被除数转为负数
		if(dividend > 0){
			dividend = -dividend;
			isNegativeNumber = !isNegativeNumber;
		}
		// 将除数转为负数
		if(divisor > 0){
			divisor = -divisor;
			isNegativeNumber = !isNegativeNumber;
		}
		List<Integer> list = new ArrayList<>();
		list.add(divisor);
		int i = 0;
		while(list.get(i) >= dividend - list.get(i)){
			list.add(list.get(i) + list.get(i));
			i++;
		}
		int ans = 0;
		for (int j = list.size() - 1; j >= 0; j--) {
			if(dividend <= list.get(j)){
				dividend = dividend - list.get(j);
				ans = ans + (1 << j);
			}
		}
        return isNegativeNumber ? -ans : ans;
    }

	public static void testStrStr() {
		String haystack = "mississippi";
		String needle = "issip";
		int res = strStr1(haystack, needle);
		System.out.println(res);
	}
	/**
	 * kmp算法 
	 * */
	public static int strStr1(String haystack, String needle) {
		int []m = new int[needle.length()];
		//m[0] = 0;
		//  left 开始代表着最长前缀的 第一个位置
		int left = 0;
		// right 代表着子串最长后缀的第一个位置
		for (int right = 1; right < needle.length(); right++) {
			while (left > 0 && needle.charAt(left) != needle.charAt(right)){
				left = m[left-1];
			}
			if(needle.charAt(left) == needle.charAt(right)){
				left++;
			}
			m[right] = left;
		}
		int n = 0;
		int i = 0;
		for (i = 0; i < haystack.length(); i++) {
			while(n > 0 && haystack.charAt(i) != needle.charAt(n)){
				n = m[n-1];
			} 
			if(haystack.charAt(i) == needle.charAt(n)){
				n++;
			}
			if(n == needle.length()){
				return i - n + 1;
			}
		}
		return -1;
	}

	public static int strStr(String haystack, String needle) {
		int i = 0;
		int j = 0;
        for (; i <= haystack.length() && j < needle.length(); i++) {
			if(haystack.charAt(i) == needle.charAt(j)){
				j++;
			}else{
				if(j > 0){
					i = i - j;
				}
				j = 0;
			}
		}
		if(j == 0 || j < needle.length()){
			return -1;
		}else{
			return i - needle.length();
		}
    }

	public static int removeElement(int[] nums, int val) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
        	   int m = nums[i];
			          if(nums[i] != val){
				             nums[res++] = m;
				         }
				     }
        return res;
    }

	public static void testRemoveDuplicates() {
        int []nums = {1,1,2};
        removeDuplicates(nums);
        for (int i = 0; i < nums.length; i++) {
        	System.out.println(nums[i]);
		}
        
    }

	public static int removeDuplicates(int[] nums) {
		//Set<Integer> set = new HashSet<>();
		int res = 1;
        for (int i = 1; i < nums.length; i++) {
        	int m = nums[i];
			if(nums[i] != nums[i-1]){
				nums[res++] = m;
			}
		}
        return res;
    }

	public static void testReverseKGroup() {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		a.next = b;
		ListNode c = new ListNode(3);
		b.next = c;
		ListNode d = new ListNode(4);
		c.next = d;
		ListNode e = new ListNode(5);
		d.next = e;
		ListNode result = reverseKGroup1(a,3);
		while(result != null){
			System.out.println(result.val);
			result = result.next;
		}
    }

	// leetcode 25.2
	public static ListNode reverseKGroup1(ListNode head, int k) {
		ListNode hair = new ListNode(-1);
		hair.next = head;
		ListNode prev = hair;
		while(head != null){
			ListNode tail = prev;
			for (int i = 0; i < k; i++) {
				tail = tail.next;
				if(tail == null){
					return hair.next;
				}
			}
			ListNode next = tail.next;
			tail.next = null;
			ListNode[] reverse = myReverseKGroup(head,tail);
			head = reverse[0];
			tail = reverse[1];
			// 将反转子链表连接到主链表
			tail.next = next;
			prev.next = head;
			prev = tail;
			head = tail.next;
		}
		return hair.next;
    }

	private static ListNode[] myReverseKGroup(ListNode head,ListNode tail){
		ListNode prev = tail.next;
		ListNode temp = head;
		while(temp != null){
			ListNode next = temp.next;
			temp.next = prev;
			prev = temp;
			temp = next;
		}
		return new ListNode[]{tail,head};
	}

	// leetcode 25
	public static ListNode reverseKGroup(ListNode head, int k) {
		Stack<ListNode> stack = new Stack<>();
		int m = 0;
		ListNode ans = new ListNode(-1);
		
		ListNode temp = ans;
		temp.next = head;
        while(head != null){
        	m++;
        	stack.push(head);
        	head = head.next;
        	if(m == k){
        		m = 0;
        		while(!stack.isEmpty()){
        			temp.next = stack.pop();
        			temp = temp.next;
        		}
        		temp.next = head;
        	}
        }
		return ans.next;
    }

	public static void testSwapPairs() {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		a.next = b;
		ListNode c = new ListNode(3);
		b.next = c;
		ListNode d = new ListNode(4);
		c.next = d;
		ListNode result = swapPairs(a);
		while(result != null){
			System.out.println(result.val);
			result = result.next;
		}
    }

	public static ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
        	return head;
        }
        ListNode ans = new ListNode(-1);
        ans.next = head;
        ListNode cur = ans;
        while(cur != null && cur.next != null && cur.next.next != null){
        	// 取到交换位置的第一个元素 的前一个元素
        	ListNode frist = cur;
        	
        	// 取到交换位置的第一个元素
        	ListNode two = cur.next;
        	
        	// 取到交换位置第二个元素
        	ListNode three = two.next;
        	
        	// 修改指向，交换位置的第一个元素 的前一个元素 的下一个元素 改为交换位置前的 第二个元素，
        	frist.next = three;
        	// 交换位置前的第一个元素 的下一个元素 改为  交换位置前的第二个元素的下一个元素，
        	two.next = three.next;
        	// 交换位置前的第二个元素的 下一个元素 改为第一个元素
        	three.next = two;

        	// 将当前元素后移两位
        	cur = cur.next.next;
        }
		return ans.next;
    }

	public static void testMergeKLists() {
		ListNode [] lists = new ListNode[2];
		ListNode a = new ListNode(-2);
		ListNode b = new ListNode(-1);
		a.next = b;
		ListNode c = new ListNode(-1);
		b.next = c;
		ListNode d = new ListNode(-1);
		c.next = d;
		
		lists[0] = a;
		//ListNode e = new ListNode();
		ListNode result = mergeKLists(lists);
		while(result != null){
			System.out.println(result.val);
			result = result.next;
		}
		
	}

	public static ListNode mergeKLists(ListNode[] lists) {
		PriorityQueue<ListNode> q =new PriorityQueue<>((a,b)->a.val-b.val);
		for(int i=0;i<lists.length;i++){
		    ListNode n =lists[i];
		    while(n != null){
		        q.offer(n);
		        n=n.next;
		    }
		}
		ListNode head = null;
		ListNode node = null;
		while(!q.isEmpty()){
		    if(head == null){
		        head = node = q.poll();    
		    }else{
		        node.next = q.poll();
		        node = node.next;
		    }
		}
		if(node != null){
			node.next = null;
		}
		return head;
	}

	public static void testGenerateParenthesis() {
		List<String> list = generateParenthesis(4);
		System.out.println(list);
    }

	public static List<String> generateParenthesis(int n) {
	    List<String> list = new ArrayList<String>();
	    generateParenthesis(list,n,n,"");
		return list;
	}
	
	public static void generateParenthesis(List<String> list,int left,int right, String s) {
		if(left == 0 && right == 0){
			list.add(s);
		} else{
			if(left > 0){
				generateParenthesis(list,left - 1,right,s + "(");
			}
			if(right > left){
				generateParenthesis(list,left,right-1,s + ")");
			}
		}
	}
	
	public static void testMergeTwoLists() {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		a.next = b;
		ListNode c = new ListNode(5);
		b.next = c;
		
		ListNode d = new ListNode(1);
		//c.next = d;
		ListNode e = new ListNode(3);
		d.next = e;
		ListNode f = new ListNode(6);
		e.next = f;

		ListNode result = mergeTwoLists(a, d);
		while(result != null){
			System.out.println(result.val);
			result = result.next;
		}
	}
	
	public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		if(list1 == null && list2 == null){
			return null;
		} else if(list1 == null){
			return list2;
		} else if(list2 == null){
			return list1;
		}
		
		ListNode head = new ListNode(-1);
		ListNode node = head;
		while(list1 != null && list2 != null){
			if(list1.val < list2.val){
				node.next = list1;
				list1 = list1.next;
			} else {
				node.next = list2;
				list2 = list2.next;
			}
			node = node.next;
		}
		node.next = list1 != null ? list1:list2;
		return head.next;
    }
}

class ListNode {
	int val;
	ListNode next;
	ListNode() {}
	ListNode(int val) { this.val = val; }
	ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
