package org.bambrikii.examples.hsqldb1;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 02.10.16 11:01.
 */
@Table
@Entity
public class Entity1 {

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
