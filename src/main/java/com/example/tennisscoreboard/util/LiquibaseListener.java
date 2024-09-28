package com.example.tennisscoreboard.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import javax.sql.DataSource;
import java.sql.Connection;

@WebListener
public class LiquibaseListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = getDataSource().getConnection()) {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new Liquibase("db/changelog/changelog-master.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DataSource getDataSource() {
        org.h2.jdbcx.JdbcDataSource dataSource = new org.h2.jdbcx.JdbcDataSource();
        dataSource.setURL("jdbc:h2:file:~/tennis_score_board_db");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        return dataSource;
    }
}