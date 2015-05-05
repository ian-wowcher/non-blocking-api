package uk.co.wowcher.threejava.ratpack;

import oracle.jdbc.pool.OracleDataSource;
import org.skife.jdbi.v2.DBI;
import ratpack.server.RatpackServer;
import ratpack.server.ServerConfig;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.net.URI;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

    public static final DataSource dataSource;

    static {
        OracleDataSource oracleDataSource = null;
        try {
            oracleDataSource = new OracleDataSource();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        oracleDataSource.setURL(System.getProperty("db.url"));
        oracleDataSource.setUser(System.getProperty("db.username"));
        oracleDataSource.setPassword(System.getProperty("db.password"));
        dataSource = oracleDataSource;
    }

    public static void main(String[] args) throws Exception {


        DBI dbi = new DBI(dataSource);

        RatpackServer.start(server -> server
                        .serverConfig(ServerConfig.embedded().publicAddress(new URI("http://localhost:8080")))
                        .registryOf(registry -> registry.add("World!"))
                        .handlers(chain -> chain
                                        .get(ctx -> ctx.render("Hello " + ctx.get(String.class)))
                                        .get(":name", ctx -> ctx.render("Hello " + ctx.getPathTokens().get("name") + "!"))
                        )
        );
    }


}