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

import de.codemakers.base.logger.LogLevel;
import de.codemakers.base.logger.Logger;
import de.codemakers.download.database.entities.QueuedVideo;

public class MySQLDatabaseTest {
    
    public static void main(String[] args) throws Exception {
        Logger.getDefaultAdvancedLeveledLogger().setMinimumLogLevel(LogLevel.FINE);
        final MySQLConnector mySQLConnector = new MySQLConnector(args[0], args[1]);
        Logger.log("mySQLConnector=" + mySQLConnector);
        //mySQLConnector.createConnection(args[2], args[3].getBytes());
        //Logger.log("mySQLConnector=" + mySQLConnector);
        final Database database = new Database(mySQLConnector);
        Logger.log("database=" + database);
        database.start(args[2], args[3].getBytes());
        Logger.log("database=" + database);
        final QueuedVideo queuedVideo_1 = database.getNextQueuedVideo();
        Logger.log("queuedVideo_1=" + queuedVideo_1);
        Thread.sleep(1000);
        database.stop();
        Logger.log("database=" + database);
    }
    
}
