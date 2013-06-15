package org.tao.certbuilder.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class XMLReader {
	protected static final String ETC_AREA_CHINA = "etc/etc_area_china.xml";
	protected SAXBuilder builder = null;
	protected Document document = null;
	protected Element root = null;

	public XMLReader() throws Exception {
		init();
		loadXML();
	}

	private void init() throws Exception {
		builder = new SAXBuilder();
		document = builder.build(ETC_AREA_CHINA);
	}

	private void loadXML() {
		root = document.getRootElement();
	}
}
