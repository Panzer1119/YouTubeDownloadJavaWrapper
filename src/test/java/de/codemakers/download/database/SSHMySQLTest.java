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
import de.codemakers.base.logger.LogLevel;
import de.codemakers.base.logger.Logger;
import de.codemakers.io.file.AdvancedFile;

public class SSHMySQLTest {
    
    public static void main(String[] args) {
        Logger.getDefaultAdvancedLeveledLogger().setMinimumLogLevel(LogLevel.FINE);
        final SSHMySQLConnector sshMySQLConnector = new SSHMySQLConnector(args[0], args[1]);
        Logger.logDebug("sshMySQLConnector=" + sshMySQLConnector);
        Logger.logDebug("Private Key File: " + new AdvancedFile(args[2]).getAbsolutePath());
        Logger.logDebug("Private Key File: " + new AdvancedFile(args[2]).exists());
        final boolean donePK = sshMySQLConnector.addPrivateKeyFile(new AdvancedFile(args[2]), args[3].getBytes());
        Logger.logDebug("donePK=" + donePK);
        Logger.logDebug("sshMySQLConnector=" + sshMySQLConnector);
        Logger.logDebug("Known Hosts File: " + new AdvancedFile(args[4]).getAbsolutePath());
        Logger.logDebug("Known Hosts File: " + new AdvancedFile(args[4]).exists());
        final boolean doneKH = sshMySQLConnector.setKnownHosts(new AdvancedFile(args[4]));
        Logger.logDebug("doneKH=" + doneKH);
        Logger.logDebug("sshMySQLConnector=" + sshMySQLConnector);
        sshMySQLConnector.createAndPrepareSession(args[5], args[6]);
        Logger.logDebug("sshMySQLConnector=" + sshMySQLConnector);
        final YouTubeDatabase<SSHMySQLConnector> youTubeDatabase = new YouTubeDatabase<>(sshMySQLConnector);
        Logger.logDebug("youTubeDatabase=" + youTubeDatabase);
        youTubeDatabase.start(args[7], args[8].getBytes());
        Logger.logDebug("youTubeDatabase=" + youTubeDatabase);
        Standard.silentError(() -> {
            Thread.sleep(5000);
            youTubeDatabase.stop();
            System.exit(0);
        });
    }
    
}
