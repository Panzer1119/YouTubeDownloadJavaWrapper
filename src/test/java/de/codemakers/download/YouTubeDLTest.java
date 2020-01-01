/*
 *    Copyright 2019-2020 Paul Hagedorn (Panzer1119)
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

import de.codemakers.io.file.AdvancedFile;

import java.util.concurrent.TimeUnit;

public class YouTubeDLTest {
    
    public static final AdvancedFile DIRECTORY_YOUTUBE = new AdvancedFile("test", "youtube");
    
    public static void main(String[] args) {
        final AdvancedFile logFile = new AdvancedFile(DIRECTORY_YOUTUBE, YouTubeDL.DEFAULT_LOG_NAME);
        YouTubeDL.setLogFile(logFile);
        final AdvancedFile logsDirectory = new AdvancedFile(DIRECTORY_YOUTUBE, YouTubeDL.DEFAULT_LOGS_NAME);
        YouTubeDL.setLogsDirectory(logsDirectory);
        final DownloadInfo downloadInfo_1 = new DownloadInfo(DIRECTORY_YOUTUBE, args[0]);
        System.out.println("downloadInfo_1=" + downloadInfo_1);
        final DownloadProgress downloadProgress_1 = DownloadManager.getInstance().submitDownload(downloadInfo_1);
        System.out.println("downloadProgress_1=" + downloadProgress_1);
        final DownloadInfo downloadInfo_2 = new DownloadInfo(DIRECTORY_YOUTUBE, args[0]);
        System.out.println("downloadInfo_2=" + downloadInfo_2);
        downloadInfo_2.setUseConfig(false);
        downloadInfo_2.setArguments("-v");
        System.out.println("downloadInfo_2=" + downloadInfo_2);
        final DownloadProgress downloadProgress_2 = DownloadManager.getInstance().submitDownload(downloadInfo_2);
        System.out.println("downloadProgress_2=" + downloadProgress_2);
        DownloadManager.getInstance().stop(3, TimeUnit.MINUTES);
    }
    
}
