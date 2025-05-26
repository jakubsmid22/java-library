package org.jakubsmid22.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    private static final Logger logger = LoggerFactory.getLogger(DataSource.class);

    static {

        final Properties prop = new Properties();

        try {
            prop.load(DataSource.class.getResourceAsStream("/application.properties"));
        }
        catch (Exception e) {
            logger.error("Error: ",e);
        }

        config.setJdbcUrl( "jdbc:mysql://localhost:3306/library" );
        config.setUsername(prop.getProperty("db.username") );
        config.setPassword( prop.getProperty("db.password") );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
    }

    private DataSource() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
