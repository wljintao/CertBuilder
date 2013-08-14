package org.tao.certbuilder.bean;

import java.util.Random;

public class CardID implements BaseID {
	private String area;
	private String birth;
	private String sex;

	public CardID(String area, String birth, String sex) {
		this.area = area;
		this.birth = birth;
		this.sex = sex;
	}

	@Override
	public String generate() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(area);
		buffer.append(birth);
		buffer.append(getSequenceNo(sex));
		buffer.append(getCheckNo(buffer.toString()));
		return buffer.toString();
	}

	// 15~18��Ϊ˳����, ����ͬ��ͬ��ͬ��ͬ�ط���������
	// ��17����Ϊ GENDER��, �е���, Ů˫��
	private String getSequenceNo(String sex) {
		StringBuffer buffer = new StringBuffer();

		Random random = new Random();
		int i = random.nextInt(9);
		buffer.append(i);
		i = random.nextInt(9);
		buffer.append(i);

		if (sex == null)
			buffer.append("1");
		else if (sex.equalsIgnoreCase("male"))
			buffer.append("1");
		else if (sex.equalsIgnoreCase("female"))
			buffer.append("2");
		else
			buffer.append("1");

		return buffer.toString();
	}

	// 18��ΪУ����, ���ǽ�ǰ���17λ����, ������1��ϵ��, Ȼ���ټ�����
	// 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
	// ����...a1*7 + a2*9 + a3*10 + a4*5 + a5*8 + a6*4 + .......
	// ����֮��, ��11
	private String getCheckNo(String str) {
		if (str == null || str.length() == 0) {
			return "0";
		}

		String[] no = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
		int[] check = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,
				8, 4, 2 };
		int l = 0;
		for (int i = 0; i < str.length(); i++) {
			int temp = Integer.parseInt(str.substring(i, i + 1));
			l += temp * check[i];
		}
		l = l % 11;
		return no[l];
	}
}
