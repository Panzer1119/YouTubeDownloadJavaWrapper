/*
 *    Copyright 2019 Paul Hagedorn (Panzer1119)
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

package de.codemakers.download;

import de.codemakers.base.logger.LogLevel;
import de.codemakers.base.logger.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class H2Test {
    
    public static void main(String[] args) throws Exception {
        Logger.getDefaultAdvancedLeveledLogger().setMinimumLogLevel(LogLevel.FINE);
        Misc.DEFAULT_APP_DATA_DIRECTORY.exists();
        Class.forName("org.h2.Driver");
        final Connection connection = DriverManager.getConnection("jdbc:h2:" + args[0], "", "");
        final Statement statement = connection.createStatement();
        try {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM videos;");
            while (resultSet.next()) {
                final String id = resultSet.getString("id");
                final String title = resultSet.getString("title");
                final long duration = resultSet.getLong("duration");
                final long uploadDate = resultSet.getLong("uploadDate");
                final String uploader = resultSet.getString("uploader");
                System.out.println(String.format("id=\"%s\", title=\"%s\", duration=%s, uploadDate=%s, uploader=\"%s\"", id, title, duration, uploadDate, uploader));
            }
        } catch (Exception ex) {
            Logger.handleError(ex);
        }
        statement.close();
        connection.close();
    }
    
}
