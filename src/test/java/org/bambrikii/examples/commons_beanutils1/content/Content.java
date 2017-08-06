package org.bambrikii.examples.commons_beanutils1.content;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * Created by Alexander Arakelyan on 02/08/17 20:10.
 */
public class Content {
	private String str1;
	private Date date1;
	private SubContent subContent;

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public SubContent getSubContent() {
		return subContent;
	}

	public void setSubContent(SubContent subContent) {
		this.subContent = subContent;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
