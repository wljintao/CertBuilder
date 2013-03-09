package org.tao.certbuilder.window;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MainWindow {

	protected Shell shlCertbuilder;
	private Text text;
	private Button btnGenerate;

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
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlCertbuilder = new Shell();
		shlCertbuilder.setSize(451, 126);
		shlCertbuilder.setText("CertBuilder");
		
		text = new Text(shlCertbuilder, SWT.BORDER | SWT.READ_ONLY);
		text.setBounds(10, 10, 415, 34);
		FontData data = new FontData("宋体", 20, SWT.NORMAL);
		Font font = new Font(shlCertbuilder.getDisplay(), data);
		text.setFont(font);
		
		btnGenerate = new Button(shlCertbuilder, SWT.NONE);
		btnGenerate.setBounds(345, 51, 80, 27);
		btnGenerate.setText("generate");
	}
	
	protected void listen(){
		btnGenerate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent selectionevent) {
				// 随机产生证件号码
			}
		});
	}
}
