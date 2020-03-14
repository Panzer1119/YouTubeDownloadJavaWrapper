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

package de.codemakers.download.entities.impl;

import de.codemakers.download.entities.AbstractDownloadContainer;
import de.codemakers.download.entities.DownloadSettings;
import de.codemakers.download.sources.YouTubeSource;

public class YouTubeDownloadContainer<D, V, P> extends AbstractDownloadContainer<D, YouTubeSource, YouTubeDLDownloadProgress, V, P> {
    
    public YouTubeDownloadContainer(YouTubeSource source, DownloadSettings downloadSettings) {
        super(source, downloadSettings, downloadSettings.getExpectedDownloads() > 0 ? new YouTubeDLDownloadProgress(downloadSettings.getExpectedDownloads()) : null);
    }
    
    public YouTubeDownloadContainer(YouTubeSource source, DownloadSettings downloadSettings, YouTubeDLDownloadProgress downloadProgress) {
        super(source, downloadSettings, downloadProgress);
    }
    
    public YouTubeDownloadContainer(D database, YouTubeSource source, DownloadSettings downloadSettings) {
        super(database, source, downloadSettings, downloadSettings.getExpectedDownloads() > 0 ? new YouTubeDLDownloadProgress(downloadSettings.getExpectedDownloads()) : null);
    }
    
    public YouTubeDownloadContainer(D database, YouTubeSource source, DownloadSettings downloadSettings, YouTubeDLDownloadProgress downloadProgress) {
        super(database, source, downloadSettings, downloadProgress);
    }
    
    @Override
    public String toString() {
        return "YouTubeDownloadContainer{" + "database=" + database + ", source=" + source + ", downloadSettings=" + downloadSettings + ", downloadProgress=" + downloadProgress + '}';
    }
    
}
