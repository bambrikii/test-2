package org.bambrikii.examples.jaxb1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Class1 {
	@XmlElement
	private String prop1;

	@XmlElement
	private String prop2;

	@XmlElement
	private int prop3;

	@XmlElement
	private Integer prop4;

	public String getProp1() {
		return prop1;
	}

	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}

	public String getProp2() {
		return prop2;
	}

	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}

	public int getProp3() {
		return prop3;
	}

	public void setProp3(int prop3) {
		this.prop3 = prop3;
	}

	public Integer getProp4() {
		return prop4;
	}

	public void setProp4(Integer prop4) {
		this.prop4 = prop4;
	}
}
