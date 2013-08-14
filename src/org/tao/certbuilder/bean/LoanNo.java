package org.tao.certbuilder.bean;

import java.util.Random;

public class LoanNo implements BaseID {
	private String area;
	private boolean flag;

	public LoanNo(String area, boolean flag) {
		this.area = area;
		this.flag = flag;
	}

	@Override
	public String generate() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(area);
		buffer.append(getSequenceNo(flag));
		buffer.append(getCheckNo(buffer.toString()));
		return buffer.toString();
	}

	// 7-14位可以为任意字符
	private String getSequenceNo(boolean flag) {
		StringBuffer buffer = new StringBuffer();

		String[] str_code = new String[] { "0", "1", "2", "3", "4", "5", "6",
				"7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
				"V", "W", "X", "Y", "Z" };

		int i = 0, size = 0;
		Random random = new Random();
		size = (flag == true) ? 36 : 10;
		for (int count = 1; count <= 8; count++) {
			i = random.nextInt(size - 1);
			buffer.append(str_code[i]);
		}
		return buffer.toString();
	}

	// 15,16码为校验码, 就是将前面的14位数字, 乘下面1个系数, 然后再加起来
	// 1, 3, 5, 7, 11, 2, 13, 1, 1, 17, 19, 97, 23, 29
	// 即是...a1*1 + a2*3 + a3*5 + a4*7 + a5*11 + a6*2 + .......
	// 加完之后, 加1除97
	private String getCheckNo(String str) {
		if (str == null || str.length() == 0) {
			return "00";
		}

		int[] check = { 1, 3, 5, 7, 11, 2, 13, 1, 1, 17, 19, 97, 23, 29 };
		int l = 0;
		int temp = 0;
		for (int i = 0; i < str.length(); i++) {
			temp = str.charAt(i);
			if (47 < temp && temp < 58)
				temp -= 48;
			else
				temp -= 55;
			l += temp * check[i];
		}
		l = 1 + l % 97;
		return l < 10 ? "0" + l : String.valueOf(l);
	}
}
