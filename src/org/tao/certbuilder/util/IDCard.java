package org.tao.certbuilder.util;

public class IDCard {
	private String regionCode;

	public IDCard(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String build() throws Exception {
	}
	
	private String generateBirth(){
	}
}
