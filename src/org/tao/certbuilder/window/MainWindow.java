package org.tao.certbuilder.window;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.tao.certbuilder.bean.BaseID;
import org.tao.certbuilder.bean.CardID;
import org.tao.certbuilder.bean.CertID;
import org.tao.certbuilder.bean.LoanNo;
import org.tao.certbuilder.xml.AreaReader;
import org.tao.certbuilder.xml.area.City;
import org.tao.certbuilder.xml.area.County;
import org.tao.certbuilder.xml.area.Province;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class MainWindow {

	protected Shell shlCertbuilder;
	protected TabFolder tabFolder;
	protected TabItem tabCardID;
	protected TabItem tabCertID;
	protected TabItem tabLoanNo;
	private AreaReader area = null;
	private List<Province> provinces = new ArrayList<Province>();
	private List<City> cities = new ArrayList<City>();
	private List<County> counties = new ArrayList<County>();
	private List<City> cities2 = new ArrayList<City>();
	private List<County> counties2 = new ArrayList<County>();

	// ���֤��
	protected Combo comProvince;
	protected Combo comCity;
	protected Combo comCountry;
	protected Combo comYear;
	protected Combo comMonth;
	protected Combo comDay;
	protected Button radioMale;
	protected Button radioFemale;
	protected Text txtCardID;
	protected Button btnGenerateCardID;

	// ��֯��������֤
	protected Button chkNumber;
	protected Text txtCertID;
	protected Button btnGenerateCertID;

	// �����
	protected Combo comProvince2;
	protected Combo comCity2;
	protected Combo comCountry2;
	protected Button chkNumber2;
	protected Text txtLoanNo;
	protected Button btnGenerateLoanNo;

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		listen();
		shlCertbuilder.open();
		shlCertbuilder.layout();
		while (!shlCertbuilder.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * 
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlCertbuilder = new Shell(SWT.MIN);
		shlCertbuilder.setImage(SWTResourceManager.getImage("number.ico"));
		shlCertbuilder.setSize(439, 294);
		shlCertbuilder.setText("����������");
		shlCertbuilder.setLayout(new FillLayout(SWT.HORIZONTAL));

		tabFolder = new TabFolder(shlCertbuilder, SWT.NONE);

		tabCardID = new TabItem(tabFolder, SWT.NONE);
		tabCardID.setText("���֤");
		{
			Composite composite = new Composite(tabFolder, SWT.NONE);
			tabCardID.setControl(composite);

			Label lblAddress = new Label(composite, SWT.NONE);
			lblAddress.setBounds(10, 22, 61, 17);
			lblAddress.setText("���ڵ�:");

			comProvince = new Combo(composite, SWT.READ_ONLY);
			comProvince.setBounds(88, 19, 88, 25);

			comCity = new Combo(composite, SWT.READ_ONLY);
			comCity.setBounds(199, 19, 88, 25);

			comCountry = new Combo(composite, SWT.READ_ONLY);
			comCountry.setBounds(310, 19, 88, 25);

			Label lblBirthday = new Label(composite, SWT.NONE);
			lblBirthday.setBounds(10, 66, 61, 17);
			lblBirthday.setText("��������:");

			comYear = new Combo(composite, SWT.READ_ONLY);
			comYear.setBounds(88, 63, 88, 25);

			comMonth = new Combo(composite, SWT.READ_ONLY);
			comMonth.setBounds(199, 63, 88, 25);

			comDay = new Combo(composite, SWT.READ_ONLY);
			comDay.setBounds(310, 63, 88, 25);

			Label lblSex = new Label(composite, SWT.NONE);
			lblSex.setBounds(10, 107, 61, 17);
			lblSex.setText("�Ա�:");

			radioMale = new Button(composite, SWT.RADIO);
			radioMale.setSelection(true);
			radioMale.setBounds(88, 107, 39, 17);
			radioMale.setText("��");

			radioFemale = new Button(composite, SWT.RADIO);
			radioFemale.setBounds(133, 107, 39, 17);
			radioFemale.setText("Ů");

			Label lblCertID = new Label(composite, SWT.NONE);
			lblCertID.setBounds(10, 140, 61, 17);
			lblCertID.setText("���֤��:");

			txtCardID = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
			txtCardID.setBounds(88, 137, 310, 23);

			btnGenerateCardID = new Button(composite, SWT.NONE);
			btnGenerateCardID.setBounds(318, 186, 80, 27);
			btnGenerateCardID.setText("����");
		}

		tabCertID = new TabItem(tabFolder, SWT.NONE);
		tabCertID.setText("��֯��������֤");
		{
			Composite composite = new Composite(tabFolder, SWT.NONE);
			tabCertID.setControl(composite);

			Label lblNumber = new Label(composite, SWT.NONE);
			lblNumber.setBounds(10, 59, 88, 17);
			lblNumber.setText("������������:");

			chkNumber = new Button(composite, SWT.CHECK);
			chkNumber.setSelection(true);
			chkNumber.setBounds(98, 59, 136, 17);
			chkNumber.setText("�Ƿ����Ӣ����ĸ");

			Label lblCertID = new Label(composite, SWT.NONE);
			lblCertID.setBounds(10, 115, 88, 17);
			lblCertID.setText("��֯��������:");

			txtCertID = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
			txtCertID.setBounds(98, 112, 300, 23);

			btnGenerateCertID = new Button(composite, SWT.NONE);
			btnGenerateCertID.setBounds(318, 186, 80, 27);
			btnGenerateCertID.setText("����");
		}

		tabLoanNo = new TabItem(tabFolder, SWT.NONE);
		tabLoanNo.setText("�����");
		{
			Composite composite = new Composite(tabFolder, SWT.NONE);
			tabLoanNo.setControl(composite);

			Label lblAddress = new Label(composite, SWT.NONE);
			lblAddress.setBounds(10, 22, 61, 17);
			lblAddress.setText("���ڵ�:");

			comProvince2 = new Combo(composite, SWT.READ_ONLY);
			comProvince2.setBounds(88, 19, 88, 25);

			comCity2 = new Combo(composite, SWT.READ_ONLY);
			comCity2.setBounds(199, 19, 88, 25);

			comCountry2 = new Combo(composite, SWT.READ_ONLY);
			comCountry2.setBounds(310, 19, 88, 25);

			Label lblNumber = new Label(composite, SWT.NONE);
			lblNumber.setBounds(10, 85, 61, 17);
			lblNumber.setText("��������:");

			chkNumber2 = new Button(composite, SWT.CHECK);
			chkNumber2.setSelection(true);
			chkNumber2.setBounds(88, 85, 136, 17);
			chkNumber2.setText("�Ƿ����Ӣ����ĸ");

			Label lblCertID = new Label(composite, SWT.NONE);
			lblCertID.setBounds(10, 140, 61, 17);
			lblCertID.setText("�����:");

			txtLoanNo = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
			txtLoanNo.setBounds(88, 137, 310, 23);

			btnGenerateLoanNo = new Button(composite, SWT.NONE);
			btnGenerateLoanNo.setBounds(318, 186, 80, 27);
			btnGenerateLoanNo.setText("����");
		}
		initArea();
		initBirthday();
	}

	protected void listen() {
		// Ϊ��ť����¼����������֤��
		btnGenerateCardID.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = comCountry.getSelectionIndex();
				String area = counties.get(index).getCode();
				String year = comYear.getText();
				String month = comMonth.getText().length() == 2 ? comMonth
						.getText() : "0" + comMonth.getText();
				String day = comDay.getText().length() == 2 ? comDay.getText()
						: "0" + comDay.getText();

				String birth = year + month + day;

				String sex = radioFemale.getSelection() ? "female" : "male";
				BaseID certID = new CardID(area, birth, sex);
				String result = certID.generate();

				txtCardID.setText(result);
			}
		});

		// Ϊ��ť����¼���������֯��������֤
		btnGenerateCertID.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean flag = chkNumber.getSelection();
				BaseID certID = new CertID(flag);
				String result = certID.generate();
				txtCertID.setText(result);
			}
		});

		// Ϊ��ť����¼������ɴ����
		btnGenerateLoanNo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = comCountry2.getSelectionIndex();
				String area = counties2.get(index).getCode();

				boolean flag = chkNumber2.getSelection();
				BaseID loanNo = new LoanNo(area, flag);
				String result = loanNo.generate();

				txtLoanNo.setText(result);
			}
		});

		comProvince.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comCity.removeAll();
				comCountry.removeAll();

				int index = comProvince.getSelectionIndex();
				String provinceCode = provinces.get(index).getCode();
				cities = area.getCity(provinceCode);
				for (int i = 0; i < cities.size(); i++) {
					comCity.add(cities.get(i).getText(), i);
				}
			}
		});

		comCity.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comCountry.removeAll();

				int index = comCity.getSelectionIndex();
				String cityCode = cities.get(index).getCode();
				counties = area.getCounty(cityCode);
				for (int i = 0; i < counties.size(); i++) {
					comCountry.add(counties.get(i).getText(), i);
				}
			}
		});

		comProvince2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comCity2.removeAll();
				comCountry2.removeAll();

				int index = comProvince2.getSelectionIndex();
				String provinceCode = provinces.get(index).getCode();
				cities2 = area.getCity(provinceCode);
				for (int i = 0; i < cities2.size(); i++) {
					comCity2.add(cities2.get(i).getText(), i);
				}
			}
		});

		comCity2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comCountry2.removeAll();

				int index = comCity2.getSelectionIndex();
				String cityCode = cities2.get(index).getCode();
				counties2 = area.getCounty(cityCode);
				for (int i = 0; i < counties2.size(); i++) {
					comCountry2.add(counties2.get(i).getText(), i);
				}
			}
		});

		comYear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comMonth.removeAll();

				for (int i = 1; i <= 12; i++) {
					comMonth.add(String.valueOf(i));
				}
			}
		});

		comMonth.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comDay.removeAll();

				int year = Integer.parseInt(comYear.getText());
				int month = Integer.parseInt(comMonth.getText());
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.MONTH, month - 1);
				int max = calendar.getActualMaximum(Calendar.DATE);

				for (int i = 1; i <= max; i++) {
					comDay.add(String.valueOf(i));
				}
			}
		});
	}

	protected void initArea() {
		try {
			area = new AreaReader();
			provinces = area.getProvinces();
			for (int i = 0; i < provinces.size(); i++) {
				comProvince.add(provinces.get(i).getText(), i);
				comProvince2.add(provinces.get(i).getText(), i);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}

	protected void initBirthday() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String now = format.format(date);
		int year = Integer.parseInt(now.substring(0, 4));
		// int month = Integer.parseInt(now.substring(5, 7));
		// int day = Integer.parseInt(now.substring(8, 10));
		comYear.setText(String.valueOf(year));

		for (int j = year; j >= year - 100; j--) {
			comYear.add(String.valueOf(j));
		}
	}
}
