package org.bambrikii.examples.jaxb1;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.jupiter.api.Test;

public class Class1SerializationTest {
	@Test
	public void test1() throws JAXBException {
		Class1 class1 = new Class1();
		class1.setProp1("prop1");
		class1.setProp2("prop2");
		class1.setProp3(1);
		class1.setProp4(1);
		JAXBContext context = JAXBContext.newInstance(Class1.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(class1, System.out);

		class1.setProp2("");
		class1.setProp3(0);
		class1.setProp4(0);
		marshaller.marshal(class1, System.out);

		class1.setProp2(null);
		class1.setProp3(0);
		class1.setProp4(null);
		marshaller.marshal(class1, System.out);
	}
}
