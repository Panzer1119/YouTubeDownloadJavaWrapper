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
import de.codemakers.download.database.entities.impl.YouTubeChannel;
import de.codemakers.download.database.entities.impl.YouTubeUploader;
import de.codemakers.download.database.entities.impl.YouTubeVideo;

import java.util.List;

public class Dev2Test {
    
    public static void main(String[] args) {
        Logger.getDefaultAdvancedLeveledLogger().setMinimumLogLevel(LogLevel.FINE);
        final YouTubeDatabase<MySQLConnector> youTubeDatabase = new YouTubeDatabase<>(new MySQLConnector(args[0], args[1]));
        Logger.logDebug("youTubeDatabase=" + youTubeDatabase);
        youTubeDatabase.start(args[2], args[3].getBytes());
        Logger.logDebug("youTubeDatabase=" + youTubeDatabase);
        final List<YouTubeVideo> youTubeVideos = youTubeDatabase.getAllVideos();
        if (youTubeVideos == null) {
            Logger.logWarning("youTubeVideos is null");
        } else {
            Logger.log("youTubeVideos.size()=" + youTubeVideos.size());
            Logger.log("youTubeVideos.subList(0, Math.min(youTubeVideos.size(), 10)=" + youTubeVideos.subList(0, Math.min(youTubeVideos.size(), 10)));
        }
        final List<YouTubeChannel> youTubeChannels = youTubeDatabase.getAllChannels();
        Logger.log("youTubeChannels=" + youTubeChannels);
        final List<YouTubeUploader> youTubeUploaders = youTubeDatabase.getAllUploaders();
        Logger.log("youTubeUploaders=" + youTubeUploaders);
        Standard.async(() -> {
            Thread.sleep(5000);
            Logger.log("Stopping Program");
            youTubeDatabase.stop();
            Thread.sleep(1000);
            Logger.log("Exiting Program");
            System.exit(0);
        });
    }
    
}
