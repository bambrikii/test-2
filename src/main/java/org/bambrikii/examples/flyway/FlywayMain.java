package org.bambrikii.examples.flyway;

import org.flywaydb.core.Flyway;

/**
 * Created by Alexander Arakelyan on 14/09/18 23:19.
 */
public class FlywayMain {
    public static void main(String[] args) {
        Flyway flyway = new Flyway(Flyway.configure());
        flyway.migrate();
        flyway.validate();
        flyway.baseline();
        flyway.clean();
        flyway.undo();
    }
}
