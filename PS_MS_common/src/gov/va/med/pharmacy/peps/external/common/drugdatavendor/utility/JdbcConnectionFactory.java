package gov.va.med.pharmacy.peps.external.common.drugdatavendor.utility;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import firstdatabank.database.JDBCConnectionFactory;


/**
 * <p>Title: Create a custom JDBCConnectionFactory to allow the DIFWK to use
 *  connections from a DataSource.  Must define getConnection() and returnConnection()</p>  
 */
public class JdbcConnectionFactory implements JDBCConnectionFactory {
    private DataSource dataSource;

    /**
     * Return a usable JDBC Connection from this factory.
     * 
     * @return Connection to the database
     * @throws SQLException trouble with the database
     */
    public final Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Return the connection through the factory.
     * 
     * @param sqlConnection the connection to close
     * @throws SQLException if unable to close connection
     */
    public final void returnConnection(Connection sqlConnection) throws SQLException {
        sqlConnection.close();
    }

    /**
     * Sets the datasource.
     * 
     * @param dataSource property
     */
    public final void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
