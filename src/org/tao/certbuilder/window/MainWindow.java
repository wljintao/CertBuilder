package org.tao.certbuilder.window;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.tao.certbuilder.xml.AreaReader;
import org.tao.certbuilder.xml.area.City;
import org.tao.certbuilder.xml.area.County;
import org.tao.certbuilder.xml.area.Province;

public class MainWindow {

	protected Shell shlCertbuilder;
	private Text text;
	private Button btnGenerate;
	private Combo cbProvince;
	private Combo cbCity;
	private Combo cbCounty;
	private AreaReader area = null;

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
		shlCertbuilder = new Shell();
		shlCertbuilder.setSize(451, 180);
		shlCertbuilder.setText("CertBuilder");

		text = new Text(shlCertbuilder, SWT.BORDER | SWT.READ_ONLY);
		text.setBounds(10, 64, 415, 34);
		FontData data = new FontData("宋体", 20, SWT.NORMAL);
		Font font = new Font(shlCertbuilder.getDisplay(), data);
		text.setFont(font);

		btnGenerate = new Button(shlCertbuilder, SWT.NONE);
		btnGenerate.setBounds(345, 104, 80, 27);
		btnGenerate.setText("generate");

		cbProvince = new Combo(shlCertbuilder, SWT.READ_ONLY);
		cbProvince.setBounds(10, 23, 116, 25);

		cbCity = new Combo(shlCertbuilder, SWT.READ_ONLY);
		cbCity.setBounds(162, 23, 116, 25);

		cbCounty = new Combo(shlCertbuilder, SWT.READ_ONLY);
		cbCounty.setBounds(309, 23, 116, 25);

		try {
			area = new AreaReader();
			List<Province> provinces = area.getProvinces();
			for (Province province : provinces) {
				cbProvince.add(province.getText());
				cbProvince.setData(province.getText(), province.getCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}

	protected void listen() {
		btnGenerate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent selectionevent) {
				// 随机产生证件号码
			}
		});

		cbProvince.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cbCity.removeAll();
				cbCounty.removeAll();

				String sProvince = cbProvince.getText();
				String sProvinceCode = (String) cbProvince.getData(sProvince);
				List<City> cities = area.getCity(sProvinceCode);
				for (City city : cities) {
					cbCity.add(city.getText());
					cbCity.setData(city.getText(), city.getCode());
				}
			}
		});

		cbCity.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cbCounty.removeAll();

				String sCity = cbCity.getText();
				String sCityCode = (String) cbCity.getData(sCity);
				List<County> counties = area.getCounty(sCityCode);
				for (County county : counties) {
					cbCounty.add(county.getText());
					cbCounty.setData(county.getText(), county.getCode());
				}
			}
		});
	}
}
