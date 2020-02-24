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

package de.codemakers.download.database.entities.impl;

import de.codemakers.download.database.YouTubeDatabase;
import de.codemakers.download.database.entities.AbstractQueuedVideo;

import java.sql.Timestamp;
import java.time.Instant;

public class QueuedYouTubeVideo extends AbstractQueuedVideo<QueuedYouTubeVideo, YouTubeDatabase, YouTubeVideo> {
    
    public QueuedYouTubeVideo() {
        super();
    }
    
    public QueuedYouTubeVideo(int id, String videoId, int priority, Timestamp requested, String arguments, String configFile, String outputDirectory) {
        super(id, videoId, priority, requested, arguments, configFile, outputDirectory);
    }
    
    public QueuedYouTubeVideo(int id, String videoId, int priority, Instant requested, String arguments, String configFile, String outputDirectory) {
        super(id, videoId, priority, requested, arguments, configFile, outputDirectory);
    }
    
    @Override
    public void set(QueuedYouTubeVideo queuedYouTubeVideo) {
        if (queuedYouTubeVideo == null) {
            //TODO Maybe just set every value in this object to null?
            return;
        }
        setId(queuedYouTubeVideo.getId());
        setVideoId(queuedYouTubeVideo.getVideoId());
        setPriority(queuedYouTubeVideo.getPriority());
        setRequested(queuedYouTubeVideo.getRequested());
        setArguments(queuedYouTubeVideo.getArguments());
        setConfigFile(queuedYouTubeVideo.getConfigFile());
        setOutputDirectory(queuedYouTubeVideo.getOutputDirectory());
    }
    
    @Override
    public String toString() {
        return "QueuedYouTubeVideo{" + "id=" + id + ", videoId='" + videoId + '\'' + ", priority=" + priority + ", requested=" + requested + ", arguments='" + arguments + '\'' + ", configFile='" + configFile + '\'' + ", outputDirectory='" + outputDirectory + '\'' + '}';
    }
    
}
