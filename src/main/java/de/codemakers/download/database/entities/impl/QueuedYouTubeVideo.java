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

import de.codemakers.download.YouTubeDL;
import de.codemakers.download.database.YouTubeDatabase;
import de.codemakers.download.database.entities.AbstractEntity;
import de.codemakers.io.file.AdvancedFile;

import java.sql.Timestamp;
import java.time.Instant;

public class QueuedYouTubeVideo extends AbstractEntity<QueuedYouTubeVideo, YouTubeDatabase> {
    
    protected int id = -1;
    protected String videoId = null;
    protected int priority = Integer.MIN_VALUE;
    protected Instant requested = null;
    protected String arguments = null;
    protected String configFile = null;
    protected String outputDirectory = null;
    
    public QueuedYouTubeVideo() {
        super();
    }
    
    public QueuedYouTubeVideo(int id, String videoId, int priority, Timestamp requested, String arguments, String configFile, String outputDirectory) {
        this(id, videoId, priority, requested.toInstant(), arguments, configFile, outputDirectory);
    }
    
    public QueuedYouTubeVideo(int id, String videoId, int priority, Instant requested, String arguments, String configFile, String outputDirectory) {
        super();
        this.id = id;
        this.videoId = videoId;
        this.priority = priority;
        this.requested = requested;
        this.arguments = arguments;
        this.configFile = configFile;
        this.outputDirectory = outputDirectory;
    }
    
    public int getId() {
        return id;
    }
    
    public QueuedYouTubeVideo setId(int id) {
        this.id = id;
        return this;
    }
    
    public String getVideoId() {
        return videoId;
    }
    
    public QueuedYouTubeVideo setVideoId(String videoId) {
        this.videoId = videoId;
        return this;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public QueuedYouTubeVideo setPriority(int priority) {
        this.priority = priority;
        return this;
    }
    
    public Timestamp getRequestedAsTimestamp() {
        final Instant requested = getRequested();
        if (requested == null) {
            return null;
        }
        return Timestamp.from(requested);
    }
    
    public Instant getRequested() {
        return requested;
    }
    
    public QueuedYouTubeVideo setRequested(Instant requested) {
        this.requested = requested;
        return this;
    }
    
    public String getArgumentsOrEmptyString() {
        final String arguments = getArguments();
        if (arguments == null) {
            return "";
        }
        return " " + arguments;
    }
    
    public String getArguments() {
        return arguments;
    }
    
    public QueuedYouTubeVideo setArguments(String arguments) {
        this.arguments = arguments;
        return this;
    }
    
    public AdvancedFile resolveConfigFile() {
        final String configFile = getConfigFile();
        if (configFile == null) {
            return YouTubeDL.getConfigFile();
        }
        return new AdvancedFile(configFile);
    }
    
    public String getConfigFile() {
        return configFile;
    }
    
    public QueuedYouTubeVideo setConfigFile(String configFile) {
        this.configFile = configFile;
        return this;
    }
    
    public AdvancedFile resolveOutputDirectory() {
        final String outputDirectory = getOutputDirectory();
        if (outputDirectory == null) {
            return YouTubeDL.getDirectory();
        }
        return new AdvancedFile(outputDirectory);
    }
    
    public String getOutputDirectory() {
        return outputDirectory;
    }
    
    public QueuedYouTubeVideo setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
        return this;
    }
    
    public YouTubeVideo asYouTubeVideo() {
        return useDatabaseOrNull((database) -> database.getVideoByVideoId(getVideoId()));
    }
    
    @Override
    protected QueuedYouTubeVideo getFromDatabase() {
        return useDatabaseOrNull((database) -> database.getQueuedYouTubeVideoById(getId()));
    }
    
    @Override
    public boolean save() {
        return useDatabaseOrNull((database) -> database.setQueuedYouTubeVideoById(this, getId()));
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
