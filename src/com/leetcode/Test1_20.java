package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Test1_20 {
	public static void main(String[] args) {
		testIsValid();
		
	}
	
	public static void testIsValid() {
		System.out.println(isValid1("()"));
    }

	public static boolean isValid1(String s) {
		if(s.length() % 2 !=0){
			return false;
		}
		Map<Character, Character> map = new HashMap<>();
		map.put(')', '(');
		map.put(']', '[');
		map.put('}', '{');
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
        	// 将输入字符串按字符逐个判断
			if(map.containsKey(s.charAt(i))){
				if(stack.size() == 0 || map.get(s.charAt(i)) != stack.pop()){
					return false;
				}
			}else{
				stack.push(s.charAt(i));
			}
		}
		
		return stack.size() == 0;
	}
	
	
	// 这种写法有问题， 如字符串 "[([]])" 结果就为true, 实际应该输出false;
	public static boolean isValid(String s) {
        // 如果第一位是右括号，那s 左边没有值了，没有与这个右括号匹配的左括号，直接返回非法
        if(')' == s.charAt(0) || ']' == s.charAt(0) || '}' == s.charAt(0)){
    		return false;
    	}
        // 如果最后一位是左括号，那s 右边没有值了，没有与这个左括号匹配的右括号，直接返回非法
        int l = s.length() - 1;
        if('(' == s.charAt(l) || '[' == s.charAt(l) || '{' == s.charAt(l)){
    		return false;
    	}
        // m 表示（）的左括号（ 数量  减右括号）数量差值
        // n 表示[]的左括号[ 数量 减右括号] 数量差值
        // k 表示{}的左括号{ 数量 减右括号} 数量差值
        int m = 0,n = 0,k = 0;
        for (int i = 0; i < s.length(); i++) {
        	// 如果 m,n,k 有任一一个为0，说明出现了右括号比左括号多的情况，直接返回不匹配
        	if(m < 0 || n < 0 || k < 0){
        		return false;
        	}
        	// 将输入字符串按字符逐个判断
        	switch (s.charAt(i)) {
        	case '(':
        		if(s.charAt(i + 1) == ']' || s.charAt(i + 1) == '}'){
					return false;
				}
				m++;
				break;
			case '[':
				if(s.charAt(i + 1) == ')' || s.charAt(i + 1) == '}'){
					return false;
				}
				n++;
				break;
			case '{':
				if(s.charAt(i + 1) == ')' || s.charAt(i + 1) == ']'){
					return false;
				}
				k++;
				break;
			case ')':
				if(s.charAt(i - 1) == '[' || s.charAt(i - 1) == '{'){
					return false;
				}
				m--;
				break;
			case ']':
				if(s.charAt(i - 1) == '(' || s.charAt(i - 1) == '{'){
					return false;
				}
				n--;
				break;
			case '}':
				if(s.charAt(i - 1) == '(' || s.charAt(i - 1) == '['){
					return false;
				}
				k--;
			}
		}
        if(m != 0 || n != 0 || k != 0){
    		return false;
    	}
		return true;
    }

	public class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	public static void testRemoveNthFromEnd() {
		ListNode a = new Test1_20().new ListNode(1);
//		ListNode b = new Test().new ListNode(2);
//		a.next = b;
//		ListNode c = new Test().new ListNode(3);
//		b.next = c;
//		ListNode d = new Test().new ListNode(4);
//		c.next = d;
//		ListNode e = new Test().new ListNode(5);
//		d.next = e;

		ListNode result = removeNthFromEnd(a, 1);
		while(result != null){
			System.out.println(result.val);
			result = result.next;
		}
	}
	
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		if(head == null){
			return null;
		}
		ListNode first = head;
		// 表示链表的长度
		int i = 1;
		// 计算链表有多少元素，
		while(first.next != null){
			first = first.next;
			i++;
		}
		if(i < n){
			return head;
		} else if(i == n){
			return head.next;
		}
		ListNode res = head;
		
		while(i - n > 1){
			i--;
			res = res.next;
		}
		res.next = res.next.next;
		
		return head;
    }

	public static void testFourSum() {
		int []nums = {0,0,0,1000000000,1000000000,1000000000,1000000000};
		List<List<Integer>> list = fourSum(nums, 1000000000);
		System.out.println(list);
	}
	
	public static List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> list = new ArrayList<>();
		if(nums == null || nums.length < 4){
			return list;
		}
		Arrays.sort(nums);
		int n = nums.length;
		for (int i = 0; i < nums.length - 3; i++) {
			int first = nums[i];
			if(i > 0 && nums[i] == nums[i-1]){
				continue;
			}
			// 当前循环最小的组合 比目标值大，说明后续不需要继续循环了，因为数组排序是正向的，后续的组合值会越来远大
			if((long)first + nums[i+1] + nums[i+2] + nums[i+3] > target){
				break;
			}
			// 当前循环最大的组合 比目标值小，说明当前循环不需要继续循环了，继续下次循环
			// 因为数组排序是正向的，后续的组合值会越来远大
			if((long)first + nums[n - 3] + nums[n - 2] + nums[n - 1] < target){
				continue;
			}
			for (int j = i + 1; j < nums.length - 2; j++) {
				if(j > i + 1 && nums[j] == nums[j-1]){
					continue;
				}
				
				int two = nums[j];
				// 当前循环最小的组合 比目标值大，说明后续不需要继续循环了，因为数组排序是正向的，后续的组合值会越来远大
				if((long)first + two + nums[j+1] + nums[j+2] > target){
					break;
				}
				// 当前循环最大的组合 比目标值小，说明当前循环不需要继续循环了，继续下次循环
				// 因为数组排序是正向的，后续的组合值会越来远大
				if((long)first + two + nums[n - 2] + nums[n - 1] < target){
					continue;
				}
				int left = j + 1;
				int right = nums.length - 1;
				
				while(left < right){
					long m = (long)first + two + nums[left] + nums[right];
					if(m > target){
						right--;
					}else if(m < target){
						left++;
					} else{
						list.add(Arrays.asList(first,two,nums[left], nums[right]));
						while(left < right && nums[left] == nums[left+1]) {
							left++;
						}
						
						while(left < right && nums[right] == nums[right-1]) {
							right--;
						}
						right--;
						left++;
					}
				}
			}
		}
		return list;
    }

	public static void testLetterCombinations() {
		List<String> list = letterCombinations("2345");
		System.out.println(list);
    }

	public static List<String> letterCombinations(String digits) {
		List<String> ans = new ArrayList<>(256);
		if(digits == null || digits.length() == 0 || digits.trim().length() == 0){
			return ans;
		}
		
		
		// 数字下标和字母的对应关系如下数组
        String param[] = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        String result[] = new String[digits.length()];
        for (int i = 0; i < digits.length(); i++) {
			char a = digits.charAt(i);
			// char字符 1的 int 值 是 49，所以如果要字符1 还原为数字1需要减去48，
			result[i] = param[a - 48];
		}
        
        StringBuilder sb = new StringBuilder();
        dfs(result, 0, ans, sb);
        
		return ans;
    }

	public static void dfs(String result[], int i,List<String> list,StringBuilder sb){
		if(i == result.length){
			list.add(sb.toString());	
		}else{
			for (int j = 0; j < result[i].length(); j++) {
				sb.append(result[i].charAt(j));
				dfs(result, i+1, list, sb);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}
	
	public static void testThreeSumClosest() {
        int []nums = {-1,2,1,-4};
        System.out.println(threeSumClosest(nums, 1));
    }
	
	public static int threeSumClosest(int[] nums, int target) {
        if(nums.length == 3){
        	return nums[0] + nums[1] + nums[2];
        }
		Arrays.sort(nums);
		// 排序后，数据前三位相加即是最小值
		int min = nums[0] + nums[1] + nums[2];
		// 排序后，数组后三位相加即是最大值
		int max = nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1];
		// 如果目标值比 相加最小值还小，返回最小值，
		if(target <= min){
			return min;
		}
		// 如果目标值比 相加最大值还大，返回最大值，
		if(target >= max){
			return max;
		}
		// 取最大值和目标值的差值
		int differ = max - target; 
		// 初始结果为最大值
		int answer = max;
		for (int i = 0; i < nums.length - 2; i++) {
			if(i > 0 && nums[i] == nums[i-1]){
				continue;
			}
			int left = i + 1;
			int right = nums.length - 1;
			while(left < right){
				int result = nums[i] + nums[left] + nums[right];
				if(result - target > 0){
					right--;
				} else if(result - target < 0){
					left++;
				// 如果三数相加结果 减去目标值 等于0，此时即是最靠近目标值的数据，直接返回目标值
				}else{
					return target;
				}
				if(differ > Math.abs(result - target)){
					// 更新差值为更小值
					differ = Math.abs(result - target);
					// 将此差值对应的目标值存储起来，
					answer = result;
				}
			}
		}
		return answer;
    }

	public static void testTthreeSum() {
		int nums[] = {-1,0,1,2,-1,-4};
		List<List<Integer>> list = threeSum(nums);
		System.out.println(list);
	}

	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		if(nums == null || nums.length < 3){
			return list;
		}
		
        // 对输入数组进行排序
		Arrays.sort(nums);
		
		List<Integer> zlist = null;
		
		// 表示取三元组 第一个元素，因为是取三元组，所以第一个元素后必须至少有两个元素
		for (int i = 0; i < nums.length-2; i++) {
			// 如果第一个元素（排序后，第一个元素是最小元素）都比0 大，说明后续循环没必要进行
			if(nums[i] > 0){
				break;
			}
			// 如果 第 i个元素是第二位以后，且值等于上一个元素，
			// 那由于上一个元素 已经循环过了，不需要再重复循环了，可以继续下一次循环
			if(i > 0 && nums[i] == nums[i-1]){
				continue;
			}
			// 表示三元组的第二个元素，从 第一个元素后一个元素取值，
			int left = i+1;
	        int right = nums.length - 1;
	        while (left < right){
	        	int sum = nums[i] + nums[left] + nums[right];
	        	if(sum > 0){
	        		right--;
	        	} else if(sum < 0){
	        		left++;
	        	} else {
	        		// 将三元组的三个值存储起来，
        			zlist = new ArrayList<>();
	        		zlist.add(nums[i]);
	        		zlist.add(nums[left]);
	        		zlist.add(nums[right]);
	        		list.add(zlist);

	        		// 去重，如果左指针的下一个元素和当前元素相等，直接将左指针向右移动一位
	        		while(left < right && nums[left] == nums[left+1]){
	        			left++;
	        		}
	        		
	        		// 去重，如果右指针的下一个元素和当前元素相等，直接将右指针向左移动一位
	        		while(left < right && nums[right] == nums[right-1]){
	        			right--;
	        		}
	        		
	        		// 如果等于0，那么如果只移动左指针或右指针，再次出现等于0的情况，是重复值，所以这里同时移动左右指针
	        		right--;
	        		left++;
	        	}
	        }
		}
		return list;
    }

	public static String longestCommonPrefix(String[] strs) {
		if(strs == null || strs.length == 0 || strs[0].length() == 0){
			return "";
		}
		// min 代表最长公共前缀  的最大长度，初始化为第一个字符串的长度，
        int min = strs[0].length();
        for (int i = 0; i < strs.length; i++) {
        	if(strs[i] == null || strs[i].length() == 0){
        		return "";
        	}
        	// 如果字符串数组中有字符串的长度比min小，将min改为更小值
        	min = Math.min(min, strs[i].length());
		}
		
		StringBuilder sb = new StringBuilder();
		// min 代表最长公共前缀  的最大长度，i代表着每次比较的是字符串数组的第i个字符
        for (int i = 0; i < min; i++) {
        	// 取出第一个字符串 的第i个字符做比较初始值
        	char a = strs[0].charAt(i);
        	
        	// 遍历字符串数组， 
        	for (int j = 0; j < strs.length; j++) {
        		// 比较字符串数组中 第一个字符串的 第i个字符 和 第j个字符串 的 第i个字符 是否相同，
        		// 如果字符串数组 当前字符 不一致，直接返回前面一致的  最长公共前缀字符串
        		if(a != strs[j].charAt(i)){
        			return sb.toString();
        		}
			}
        	// 如果没有跳出36行循环，说明当前字符是 一致的，添加到最长公共前缀字符串
        	sb.append(a);
		}
		return sb.toString();
    }

	public static void testromanToInt1() {
		System.out.println(romanToInt("III"));
	}
	
	
	public static int romanToInt1(String s) {
        int ans = 0; 
        for (int i = s.length() - 1; i >=0; i--) {
			switch(s.charAt(i)){
				case 'I' : 
					ans = ans >= 5 ? ans -1 :ans +1; 
					break;
				case 'V' : 
					ans = ans + 5;
					break;
				case 'X' : 
					ans = ans >= 50 ? ans -10 :ans +10;
					break;
				case 'L' : 
					ans = ans + 50;
					break;
				case 'C' : 
					ans = ans >= 500 ? ans -100 :ans + 100;
					break;
				case 'D' : 
					ans = ans + 500;
					break;
				case 'M' : 
					ans = ans + 1000;
			}
		}
        return ans;
    }
	
	public static int romanToInt(String s) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int ans = 0 ; 
        if (s.contains("CM")){
        	ans = ans + 900;
        	s = s.replace("CM", "");
        }
        if (s.contains("CD")){
        	ans = ans + 400;
        	s = s.replace("CD", "");
        }
        if (s.contains("XC")){
        	ans = ans + 90;
        	s = s.replace("XC", "");
        }
        if (s.contains("XL")){
        	ans = ans + 40;
        	s = s.replace("XL", "");
        }
        if (s.contains("IX")){
        	ans = ans + 9;
        	s = s.replace("IX", "");
        }
        if (s.contains("IV")){
        	ans = ans + 4;
        	s = s.replace("IV", "");
        }
		for (int i = 0; i < s.length(); i++) {
			ans = ans + map.get(s.charAt(i));
		}
        return ans;
    }
	
	public static void testIntToRoman() {
		System.out.println(intToRoman(1994));
	}
	
	public static String intToRoman(int num) {
		Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "I");
        map.put(5, "V");
        map.put(10, "X");
        map.put(50, "L");
        map.put(100, "C");
        map.put(500, "D");
        map.put(1000, "M");
        
        String [] res = new String[4];
        for (int i = 0; i < res.length; i++) {
        	res[i] = new String();
		}
        
        int j = 1;
        int i = 0;
		while(num > 0){
			String s = res[i];
			int size = num % 10;
			
			if(size > 0 && size <= 3){
				for (int k = 0; k < size; k++) {
					s = s + map.get(1*j); 
				}
			} else if(size == 4){
				s = s + map.get(1*j) + map.get(5*j); 
			} else if(size == 5){
				s = s + map.get(5*j); 
			} else if(size >= 6 && size < 9){
				s = s + map.get(5*j);
				for (int k = 0; k < size - 5; k++) {
					s = s + map.get(1*j); 
				}
			} else if(size == 9){
				s = s + map.get(1*j) + map.get(10 *j);
			}
			res[i] = s;
			i++;
			num = num / 10;
			j = j * 10;
		}
		
		String ans = "";
		for (int m = res.length - 1; m > -1; m--) {
			if(res[m] != null && res[m].length() > 0){
				ans = ans + res[m];
			}
		}
        
		return ans;
    }
	
	public static int maxArea(int[] height) {
		// 初始化左指针，右指针， 最大面积
		int i = 0,j = height.length - 1, area = 0;
		while(i < j){
			if(height[i] > height[j]){
				area = Math.max(area, (j-i) * height[j]);
				j--;
			}else {
				area = Math.max(area, (j-i) * height[i]);
				i++;
			}
		}
		return area;
    }
	
	public static void testIsMatch() {
		System.out.println(isMatch("aa", "a*") );
	}
	
	public static boolean isMatch(String s, String p) {
		
		int m = s.length(); 
		int n = p.length();
		boolean [][]dp = new boolean [m+1][n+1];
		// 表示当s 和 p 都为空字符串的时候，是匹配
		dp[0][0] = true;
		
		// 当s 为空时 , 只有a* 这种格式，
		for (int j = 1; j <= n; j++) {
			if (p.charAt(j-1) == '*'){
				dp[0][j] = dp[0][j-2];
			}
		}
		
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){
					dp[i][j] = dp[i-1][j-1];
				} else if(p.charAt(j-1) == '*'){
					if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'){
						dp[i][j] = dp[i][j-2] || dp[i-1][j-2] || dp[i-1][j];
					}else {
						dp[i][j] = dp[i][j-2];
					}
				}
			}
		}
		
		return dp[m][n];
    }
	
	public  static void testMyAtoi() {
		String result = " 42";
		System.out.println(myAtoi(result));
	}
	
	public static int myAtoi(String s) {
		if (s == null || s.length() == 0 || s.trim().length() == 0){
			return 0;
		}
		// 直接用trim方法去掉空格，完成题目第一个去空格的要求
		s = s.trim();
		
		StringBuffer sb = new StringBuffer();
		// 输入的数字是否是负数, 默认false为 正数， true 为负数
		boolean isNegativeNumber = false;
		for (int i = 0; i < s.length(); i++) {
			char a = s.charAt(i);
			// 检查去掉空格后的 首个字符是不是 "+" 或 "-"，如果是"-",isNegativeNumber设为true;
			if(i == 0 && ('+' == a || '-' == a)){
				if('-' == a){
					isNegativeNumber = true;
				}
				continue;
			}
			// 如果sb 为空，说明还没开始数字解析， 根据要求三去掉前置0，将解析到的零全部抛弃， 并继续下一次循环
			if(a == '0' && sb.length() == 0){
				continue;
			}
			// 如果当前字符是数字， 将字符添加到strinbuffer尾部， 如果不是数字，直接结束循环
			if(a >= '0' && a <= '9'){
				sb.append(a);
			}else {
				break;
			}
		}
		// 如果解析得到的 strinbuffer 长度为0，直接按要求三   结尾返回数字 0；
		if(sb.length() == 0){
			return 0;
		}
		// 将strinbuffer 转化为字符串
		String result = sb.toString();
		// 统计数字位长度，
		int len = result.length();
		// 如果是负数进入下面分支
		if (isNegativeNumber){
			result = "-" + result;
			// 如果数字位数大于10位，肯定溢出了， 如果等于10位，和int最小值比较，比最小值还小，也会溢出， 
			// 溢出返回int 最小值，否则返回 解析值，
			if(len >10 || (len==10 && result.compareTo(String.valueOf(Integer.MIN_VALUE)) > 0)){
				return Integer.MIN_VALUE;
			} else {
				return Integer.parseInt(result);
			}
		// 如果是正数进入下面分支
		}else {
			// 如果数字位数大于10位，肯定溢出了， 如果等于10位，和int最大值比较，比最大值还大，也会溢出， 
			// 溢出返回int 最大值，否则返回 解析值，
			if(len >10 || (len==10 && result.compareTo(String.valueOf(Integer.MAX_VALUE)) > 0)){
				return Integer.MAX_VALUE;
			}else {
				return Integer.parseInt(result);
			}
		}
    }
	
	public static void testIsPalindrome1() {
		System.out.println(isPalindrome1(1001));
	}
	
	public static boolean isPalindrome1(int x) {
		if(x < 0 || (x > 0 && x % 10 == 0)){
			return false;
		}
		if(x >= 0 && x < 10){
			return true;
		}
		if(x >= 10 && x < 100){
			return x/10 == x % 10;
		}
		int res = 0;
		while(x > res){
			res = res * 10 + x % 10;
			x = x / 10;
		}
		
		return x == res || x == res/10;
    }
	
	
	public static boolean isPalindrome(int x) {
		if(x < 0){
			return false;
		}
		
		if(x >= 0 && x < 10){
			return true;
		}
		
		if(x >= 10 && x < 100){
			return x/10 == x % 10;
		}
		int i = 0;
		int a = x;
		while(a != 0){
			a = a / 10;
			i++;
		}
		a = x;
		int m = i;
		int n[] = new int[i];
		while(m > 0){
			n[--m] = a % 10;
			a = a / 10;
			
		}
		int j = (i+1)/2;
		int len = n.length;
		if(i % 2 == 0 ){
			len = len - 1;
		}
		for (int k= 0; j <= len - k && j >= k; k++) {
			int y = j - k - 1;
			int z = j + k - 1;
			if(i % 2 == 0){
				z = z+1;
			}
			if(n[y] != n[z]){
				return false;
			}
		}
		return true;
    }
	
	public static void testReverse(){
		System.out.println(reverse1(-214748322));
	}
	
	public static int reverse1(int x) {
		int rev = 0;
		while(x != 0){
			if(rev < Integer.MIN_VALUE/10 || rev > Integer.MAX_VALUE/10){
				return 0;
			}
			int digit = x % 10;
			x = x/10;
			rev = rev * 10 + digit;
		}
		return rev;
    }
	
	
	public static int reverse(int x) {
		boolean flag = x < 0;
		String s = String.valueOf(Math.abs((long)x));
		StringBuffer sb = new StringBuffer();
		for (int i = s.length() - 1; i > -1; i--) {
			sb.append(s.charAt(i));
		}
		long l = Long.parseLong(sb.toString());
		if(flag){
			l = -l;
		}
		if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE){
			return 0;
		}
		return (int)l;
    }
	
	public static void testConvert(){
		String s = "PAYPALISHIRING";
		int numRows = 3;
		System.out.println(convert1(s, numRows));
	}
	
	public static String convert1(String s, int numRows) {
		int len = s.length();
		if (numRows >= len || numRows == 1) {
			return s;
		}
        // 每一组字符的数量
        int rowMax = numRows * 2 - 2;
        //int columnMax = (len / rowMax + 1) * (numRows - 1);
        StringBuffer sb[] = new StringBuffer[numRows];
        for (int i = 0; i < numRows; ++i) {
        	sb[i] = new StringBuffer();
        }

        //Character [][] c = new Character[numRows][columnMax];
        int row = 0;
        //int column = 0;
        for (int i = 0; i < len; i++) {
			char a = s.charAt(i);
			sb[row].append(a);
			// 计算字符在每个分组   多少位， 位数小于行数，每次向下移动一位，
			int rowmod = i % rowMax;
			// 如果组内取模值 等于0，那就代表着是组内最后一位， 行数就是取模值，列数就是
			if (rowmod < numRows - 1){
				++row;
			} else {
				--row;
				//column++;
			} 
		}
        StringBuffer s1 = new StringBuffer();
        for (int i = 0; i < sb.length; i++) {
        	s1.append(sb[i]);
		}
		return s1.toString();
    }
	
	public static String convert(String s, int numRows) {
		int len = s.length();
		if (numRows >= len || numRows == 1) {
			return s;
		}
		String result = "";
		if (numRows == 2) {
			String r1 = "";
			for (int i = 0; i < len / 4; i++) {
				result = result + s.charAt(i*4) + s.charAt(i*4+2);
				r1 = r1 + s.charAt(i*4+1) + s.charAt(i*4+3);
			}
			if (len % 4 == 1){
				result = result + s.charAt(len - 1);
			} else if (len % 4 == 2){
				result = result + s.charAt(len - 2);
				r1 = r1 + s.charAt(len-1);
			} else if (len % 4 == 3){
				result = result + s.charAt(len - 3) + s.charAt(len-1);
				r1 = r1 + s.charAt(len-2);
			}
			return result + r1;
		}
        // 每一组字符的数量
        int rowMax = numRows * 2 - 2;
        int columnMax = (len / rowMax + 1) * (numRows - 1);
        Character [][] c = new Character[numRows][columnMax];
        int row = 0;
        int column = 0;
        for (int i = 1; i < len + 1; i++) {
			char a = s.charAt(i-1);
			// 计算字符在多少组   多少位， 根据多少组，多少位，算出横纵坐标
			int rowIndex = i / rowMax;
			int rowmod = i % rowMax;
			// 如果组内取模值 等于0，那就代表着是组内最后一位， 行数就是取模值，列数就是
			if (rowmod == 0){
				row = 1;
				column = rowIndex * (numRows - 1) - 1;
			// 如果组内取模值小于 给定的行数，且大于0，那 行数就是取模值 ， 但是要转化位数组下标，需要减一
			} else if (rowmod > 0 && rowmod <= numRows){
				row = rowmod - 1;
				column = rowIndex * (numRows - 1);
			} else{
				row = rowMax - rowmod + 1;
				column = (rowIndex+1) * (numRows - 1) - 1 - (rowMax - rowmod);
			}
			c[row][column] = a;
		}
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[i].length; j++) {
				Character b = c[i][j];
				if(b != null){
					sb.append(b);
				}
			}
		}
		return sb.toString();
    }
	
	
	public static String longestPalindrome(String s) {
		if(s.length() == 1){
			return s;
		}
		int i = 0;
		int length = 0;
		int start = 0;
        while(i < s.length()){
        	// 以当前字符  为回文中心的最大回文字符串长度
        	int a1 = maxHuiWenLength(s, i, i);
        	// 以当前字符 和下一个字符  为回文中心的最大回文字符串长度
        	int a2 = maxHuiWenLength(s, i, i+1);
        	// 取最大的回文长度
        	int a3 = Math.max(a1, a2);
        	// 如果当前字符的最大回文长度比  已知最大回文长度大，更新回文长度，和回文字符起始位置
        	if(a3 > length){
        		length = a3;
        		start = i - (length-1)/2;
        	}
        	i++;
        }
		
		return s.substring(start,start + length);
    }
	
	public static int maxHuiWenLength(String s,int left,int right){
		if (right >= s.length() || s.charAt(left) != s.charAt(right)){
			return 1;
		}
		while (left > 0 && right < s.length()-1){
			if (left <= right && s.charAt(left-1) == s.charAt(right+1)){
				left--;
				right++;
			} else{
				break;
			}
		}
		return right-left+1;
	}
	
	public static void testFindMedianSortedArrays(){
		int[] nums1 = {1,3};
		int[] nums2 = {2};
//		int[] nums1 = {2,2,3,4,4};
//		int[] nums2 = {2,2,4,4};
		double ans = findMedianSortedArrays(nums1, nums2);
		System.out.println(ans);
	}
	
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;
		
        // 取数组的中位数所在的下标位置
		int len = (m + n)/2;
		
        // 判断数组的总长度是双数还是单数,，如果是双数，就是true
		boolean isTwo = (m + n) % 2 == 0;
		// 当前正在读取的数
		int left = 0;
		// 最后读取的数据
		int right = 0;
        
        for (int i = 0,j = 0,k=0; k <= len;k++) {
        	left = right;
			if (i < m && (j >= n || nums1[i] < nums2[j] )){
				right = nums1[i];
				i++;
			} else {
				right = nums2[j];
				j++;
			}
		}
        if (isTwo){
        	return (left + right)/2.0;
        }else {
        	return right;
        }
    }
	
	public static void testLengthOfLongestSubstring(){
		String s = "abcabcbb";
		//String s = "bbbbb";
		//String s = "pwwkew";
		int length = lengthOfLongestSubstring(s);
		System.out.println(length);
//		String [] str = s.split("b");
//		for (int i = 0; i < str.length; i++) {
//			System.out.println(str[i]);
//		}
	}
	
	public static int lengthOfLongestSubstring(String s){
		Set<Character> set = new HashSet<>();
		int n = s.length();
		// 记录子字符串的结束位置
		int right = 0 ;
		
		// 字串的最长长度
		int ans = 0;
		// for 循环的默认参数i 可以作为循环的左指针
		for (int i = 0;  i  < s.length();  i++) {
			if (i != 0){
				set.remove(s.charAt(i-1));
			}
			while (right < n  && !set.contains(s.charAt(right))){
				set.add(s.charAt(right));
				right ++;
			}
			ans = Math.max(ans, right - i);
			
		}
		return ans;
	}

}
