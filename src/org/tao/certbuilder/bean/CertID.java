package org.tao.certbuilder.bean;

import java.util.Random;

public class CertID implements BaseID {
	private boolean flag = false;

	public CertID(boolean flag) {
		this.flag = flag;
	}

	public String generate() {
		String code = getCode();
		String c9 = getC9(code);
		return code + "-" + c9;
	}

	// ��ȡ�������
	private String getCode() {
		String[] str_code = new String[] { "0", "1", "2", "3", "4", "5", "6",
				"7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
				"V", "W", "X", "Y", "Z" };
		StringBuffer buffer = new StringBuffer();
		int i = 0, size = 0;
		Random random = new Random();
		size = (flag == true) ? 36 : 10;
		for (int count = 1; count <= 8; count++) {
			i = random.nextInt(size - 1);
			buffer.append(str_code[i]);
		}
		return buffer.toString();
	}

	/**
	 * C9=11-MOD(��Ci(i=1��8)��Wi,11) ʽ�У� MOD�����������ຯ���� i������������ַ���������λ����ţ�
	 * Ci���������iλ�ϵĴ����ַ���ֵ����������ַ��������� C9��������У���룻 Wi���������iλ�ϵļ�Ȩ����
	 * ��C9��ֵΪ10ʱ��У����Ӧ�ô�д��������ĸX��ʾ����C9��ֵΪ11ʱУ������0��ʾ
	 * 
	 * @param index
	 * @return
	 */
	private String getC9(String code) {
		long result = 0;
		int length = code.length();
		int temp = 0;
		for (int i = 0; i < length; i++) {
			temp = code.charAt(i);
			if (47 < temp && temp < 58)
				temp -= 48;
			else
				temp -= 55;
			result += (temp * getWi(i + 1)) % 11;
		}
		result = 11 - result % 11;

		if (result == 10)
			return "X";
		else if (result == 11)
			return "0";
		else
			return String.valueOf(result);
	}

	/**
	 * Wi���������iλ�ϵļ�Ȩ���ӣ�����ֵ���±�
	 * 
	 * @param index
	 * @return
	 */
	private int getWi(int index) {
		int wi = 0;
		switch (index) {
		case 1:
			wi = 3;
			break;
		case 2:
			wi = 7;
			break;
		case 3:
			wi = 9;
			break;
		case 4:
			wi = 10;
			break;
		case 5:
			wi = 5;
			break;
		case 6:
			wi = 8;
			break;
		case 7:
			wi = 4;
			break;
		case 8:
			wi = 2;
			break;
		default:
			break;
		}
		return wi;
	}

}
