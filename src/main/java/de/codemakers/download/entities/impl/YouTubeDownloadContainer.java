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

import de.codemakers.download.DownloadSettings;
import de.codemakers.download.entities.DownloadContainer;
import de.codemakers.download.sources.YouTubeSource;

public class YouTubeDownloadContainer extends DownloadContainer<YouTubeSource, YouTubeDLDownloadProgress> {
    
    public YouTubeDownloadContainer(YouTubeSource source, DownloadSettings downloadSettings) {
        super(source, downloadSettings, new YouTubeDLDownloadProgress(downloadSettings.getExpectedDownloads()));
    }
    
    @Override
    public String toString() {
        return "YouTubeDownloadContainer{" + "source=" + source + ", downloadSettings=" + downloadSettings + ", downloadProgress=" + downloadProgress + '}';
    }
    
}
