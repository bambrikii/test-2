package org.bambrikii.examples.commons_beanutils1.content2;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bambrikii.examples.commons_beanutils1.content.SubSubContent;

/**
 * Created by Alexander Arakelyan on 02/08/17 20:11.
 */
public class SubContent2 {
	private String name1;
	private SubSubContent subContent;

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public SubSubContent getSubContent() {
		return subContent;
	}

	public void setSubContent(SubSubContent subContent) {
		this.subContent = subContent;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
