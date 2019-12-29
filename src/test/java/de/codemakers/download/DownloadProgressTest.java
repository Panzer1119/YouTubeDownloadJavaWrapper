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

import de.codemakers.base.Standard;
import de.codemakers.base.logger.LogLevel;
import de.codemakers.base.logger.Logger;
import de.codemakers.io.file.AdvancedFile;

import java.util.Arrays;

public class DownloadProgressTest {
    
    public static final AdvancedFile DIRECTORY_YOUTUBE = new AdvancedFile("test", "youtube");
    
    static {
        Logger.getDefaultAdvancedLeveledLogger().setMinimumLogLevel(LogLevel.FINE);
        Logger.getDefaultAdvancedLeveledLogger().createLogFormatBuilder().appendSource().appendThread().appendLogLevel().appendText(": ").appendObject().finishWithoutException();
        YouTubeDL.setDirectory(DIRECTORY_YOUTUBE);
        final AdvancedFile logFile = new AdvancedFile(DIRECTORY_YOUTUBE, YouTubeDL.DEFAULT_LOG_NAME);
        YouTubeDL.setLogFile(logFile);
        final AdvancedFile logsDirectory = new AdvancedFile(DIRECTORY_YOUTUBE, YouTubeDL.DEFAULT_LOGS_NAME);
        YouTubeDL.setLogsDirectory(logsDirectory);
    }
    
    public static void main(String[] args) {
        final String url = args[0];
        Logger.log("url=" + url);
        final DownloadProgress downloadProgress = DownloadManager.getInstance().submitDownload(new DownloadInfo(url).setUseConfig(false));
        Standard.async(() -> {
            while (downloadProgress.isAlive() || !downloadProgress.isStarted()) {
                final double progressOverall = downloadProgress.getProgressOverall();
                Logger.log(String.format("progressOverall=%.2f%%, progresses=%s", progressOverall * 100.0, Arrays.toString(downloadProgress.getProgresses())));
                Thread.sleep(500);
            }
            DownloadManager.getInstance().stop();
        });
    }
    
}
