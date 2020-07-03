package org.bambrikii.examples.xa;

import org.postgresql.xa.PGXADataSource;

import javax.sql.XAConnection;
import javax.transaction.SystemException;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

public class XaExample {
  public static void main(String[] args) throws SQLException, SystemException, ClassNotFoundException, XAException {
    String url = "jdbc:postgresql://localhost/test_db";
    final String user = "test_user";
    final String password = "test_user";

    Class.forName("org.postgresql.Driver");

    PGXADataSource ds = new PGXADataSource();
    ds.setUrl(url);
    ds.setUser(user);
    ds.setPassword(password);
//    /etc/postgresql.conf:max_prepared_transactions=1;

    XAConnection xaConn = ds.getXAConnection();
    Connection conn = xaConn.getConnection();
    XAResource res = xaConn.getXAResource();

    Xid xid = XidImpl.getUniqueXid(1);
    res.start(xid, XAResource.TMNOFLAGS);

    PreparedStatement stmt = conn.prepareStatement("select * from test_table1");
    ResultSet resultSet = stmt.executeQuery();
    while (resultSet.next()) {
      System.out.println(MessageFormat.format("{0} {1} ", resultSet.getLong("id"), resultSet.getString("name1")));
    }
    res.end(xid, XAResource.TMSUCCESS);
    int prepare = res.prepare(xid);
//    res.rollback(xid);
    res.commit(xid, false);

  }
}
