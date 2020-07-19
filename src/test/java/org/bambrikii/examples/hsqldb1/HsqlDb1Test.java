package org.bambrikii.examples.hsqldb1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Alexander Arakelyan on 02.10.16 11:03.
 */
public class HsqlDb1Test {
	private static final Logger logger = LoggerFactory.getLogger(HsqlDb1Test.class);

	@Test
	public void testConnection1() throws SQLException, ClassNotFoundException {
		Class.forName("org.hsqldb.jdbcDriver");
		try (Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:test2", "sa", "")) {
			try (PreparedStatement stmt = c
					.prepareStatement("create table t1 (id integer  IDENTITY PRIMARY KEY, col1 int,  dt TIMESTAMP)")) {
				stmt.execute();
			}
			for (int i = 0; i < 50; i++) {
				try (PreparedStatement statement = c
						.prepareStatement("insert into t1 (col1, dt) values (EXTRACT (second from now), now)")) {
					statement.execute();
				}
			}
			try (PreparedStatement statement = c.prepareStatement("select id, col1, dt from t1");
					ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					logger.debug("t1 row: id={}, col1={}, dt={}", rs.getInt("id"), rs.getInt("col1"),
							rs.getTimestamp("dt"));
				}
			}
		}
	}

	@Test
	public void test1() throws ClassNotFoundException {
		Class.forName("org.hsqldb.jdbcDriver");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Entity1 entity1 = new Entity1();
		entity1.setName("name1");
		session.save(entity1);

		Query query = session.createQuery("from " + Entity1.class.getName() + " e1");
		List<Entity1> list = query.list();
		for (Entity1 e1 : list) {
			logger.info("{}", e1);
		}
	}
}
