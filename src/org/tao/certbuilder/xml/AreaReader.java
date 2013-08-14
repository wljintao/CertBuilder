package org.tao.certbuilder.xml;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.tao.certbuilder.xml.area.City;
import org.tao.certbuilder.xml.area.County;
import org.tao.certbuilder.xml.area.Province;

public class AreaReader extends XMLReader {

	public AreaReader() throws Exception {
		super();
	}

	public List<Province> getProvinces() {
		List<Province> list = new ArrayList<Province>();
		Province province = null;

		List<Element> provinces = root.getChildren();
		for (Element element : provinces) {
			province = new Province();
			province.setText(element.getAttributeValue("text"));
			province.setCode(element.getAttributeValue("code"));
			province.setCityCount(element.getChildren().size());
			list.add(province);
		}

		return list;
	}

	public List<City> getCity(String provinceCode) {
		List<City> list = new ArrayList<City>();
		City city = null;

		Element province = getProvinceByCode(provinceCode);
		if (province != null) {
			List<Element> cities = province.getChildren();
			for (Element element : cities) {
				city = new City();
				city.setText(element.getAttributeValue("text"));
				city.setCode(element.getAttributeValue("code"));
				city.setCountyCount(element.getChildren().size());
				list.add(city);
			}
		}

		return list;
	}

	public List<County> getCounty(String cityCode) {
		List<County> list = new ArrayList<County>();
		County county = null;

		Element city = getCityByCode(cityCode);
		List<Element> counties = city.getChildren();
		for (Element element : counties) {
			county = new County();
			county.setText(element.getAttributeValue("text"));
			county.setCode(element.getAttributeValue("code"));
			list.add(county);
		}

		return list;
	}

	private Element getProvinceByCode(String code) {
		List<Element> provinces = root.getChildren();
		Element eProvince = null;
		for (Element element : provinces) {
			if (element.getAttributeValue("code").equals(code)) {
				eProvince = element;
				break;
			}
		}
		return eProvince;
	}

	private Element getCityByCode(String code) {
		List<Element> provinces = root.getChildren();
		Element eCity = null;
		String sIndex = code.substring(0, 2);
		for (Element element : provinces) {
			if (element.getAttributeValue("code").startsWith(sIndex)) {
				List<Element> cities = element.getChildren();
				for (Element element2 : cities) {
					if (element2.getAttributeValue("code").equals(code)) {
						eCity = element2;
						break;
					}
				}
			}
			if (eCity != null)
				break;
		}
		return eCity;
	}
}
