/*
 *    Copyright 2019 - 2020 Paul Hagedorn (Panzer1119)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package de.codemakers.download.database;

import de.codemakers.base.Standard;
import de.codemakers.base.action.ClosingAction;
import de.codemakers.base.logger.Logger;
import de.codemakers.io.file.AdvancedFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Connector {
    
    public static final String CLASS_H2_DRIVER = "org.h2.Driver";
    public static final String TEMPLATE_CONNECTION_STRING = "jdbc:h2:%s";
    
    static {
        try {
            Class.forName(CLASS_H2_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.handleError(ex);
        }
    }
    
    private AdvancedFile databaseDirectory;
    //Temp
    private transient Connection connection;
    
    public Connector(AdvancedFile databaseDirectory) {
        this.databaseDirectory = Objects.requireNonNull(databaseDirectory, "databaseDirectory");
    }
    
    public AdvancedFile getDatabaseDirectory() {
        return databaseDirectory;
    }
    
    public Connector setDatabaseDirectory(AdvancedFile databaseDirectory) {
        this.databaseDirectory = databaseDirectory;
        return this;
    }
    
    public boolean isConnected() {
        return connection != null;
    }
    
    public boolean createConnection() {
        if (connection != null) {
            return true;
        }
        connection = createConnection(databaseDirectory.getAbsolutePath());
        return connection != null;
    }
    
    public boolean closeConnection() {
        if (connection == null) {
            return true;
        }
        return Standard.silentError(connection::close) == null;
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public Statement createStatement() throws SQLException {
        if (!isConnected()) {
            return null;
        }
        return connection.createStatement();
    }
    
    public ClosingAction<Statement> createAutoClosingStatement() {
        if (!isConnected()) {
            return null;
        }
        return new ClosingAction<>(connection::createStatement);
    }
    
    @Override
    public String toString() {
        return "Connector{" + "databaseDirectory=" + databaseDirectory + ", connection=" + connection + '}';
    }
    
    protected static Connection createConnection(String path) {
        return createConnection(path, null, null);
    }
    
    protected static Connection createConnection(String path, String username, String password) {
        final String connectionString = String.format(TEMPLATE_CONNECTION_STRING, path);
        try {
            return DriverManager.getConnection(connectionString, username == null ? "" : username, password == null ? "" : password);
        } catch (Exception ex) {
            Logger.handleError(ex);
            return null;
        }
    }
    
}
