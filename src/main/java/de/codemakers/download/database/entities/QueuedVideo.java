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

package de.codemakers.download.database.entities;

import java.sql.Timestamp;
import java.util.Objects;

public class QueuedVideo extends AbstractEntity<QueuedVideo> {
    
    protected int id;
    protected String videoId;
    protected int priority;
    protected Timestamp requested;
    protected String arguments;
    protected String configFile;
    protected String outputDirectory;
    
    public QueuedVideo() {
        this(0, null, 0, null, null, null, null);
    }
    
    public QueuedVideo(int id, String videoId, int priority, Timestamp requested, String arguments, String configFile, String outputDirectory) {
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
    
    public QueuedVideo setId(int id) {
        this.id = id;
        return this;
    }
    
    public String getVideoId() {
        return videoId;
    }
    
    public QueuedVideo setVideoId(String videoId) {
        this.videoId = videoId;
        return this;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public QueuedVideo setPriority(int priority) {
        this.priority = priority;
        return this;
    }
    
    public Timestamp getRequested() {
        return requested;
    }
    
    public QueuedVideo setRequested(Timestamp requested) {
        this.requested = requested;
        return this;
    }
    
    public String getArguments() {
        return arguments;
    }
    
    public QueuedVideo setArguments(String arguments) {
        this.arguments = arguments;
        return this;
    }
    
    public String getConfigFile() {
        return configFile;
    }
    
    public QueuedVideo setConfigFile(String configFile) {
        this.configFile = configFile;
        return this;
    }
    
    public String getOutputDirectory() {
        return outputDirectory;
    }
    
    public QueuedVideo setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
        return this;
    }
    
    public Video asVideo() {
        return getDatabase().getVideoById(getVideoId());
    }
    
    @Override
    public String toString() {
        return "QueuedVideo{" + "id=" + id + ", videoId='" + videoId + '\'' + ", priority=" + priority + ", requested=" + requested + ", arguments='" + arguments + '\'' + ", configFile='" + configFile + '\'' + ", outputDirectory='" + outputDirectory + '\'' + '}';
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        if (!super.equals(other)) {
            return false;
        }
        final QueuedVideo that = (QueuedVideo) other;
        return priority == that.priority && Objects.equals(requested, that.requested) && Objects.equals(arguments, that.arguments) && Objects.equals(configFile, that.configFile) && Objects.equals(outputDirectory, that.outputDirectory);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), priority, requested, arguments, configFile, outputDirectory);
    }
    
    @Override
    protected QueuedVideo getFromDatabase() {
        return getDatabase().getQueuedVideo(getId());
    }
    
    @Override
    public boolean save() {
        return getDatabase().saveQueuedVideo(this);
    }
    
    @Override
    public void set(QueuedVideo queuedVideo) {
        if (queuedVideo == null) {
            return;
        }
        setId(queuedVideo.getId());
        setVideoId(queuedVideo.getVideoId());
        setPriority(queuedVideo.getPriority());
        setRequested(queuedVideo.getRequested());
        setArguments(queuedVideo.getArguments());
        setConfigFile(queuedVideo.getConfigFile());
        setOutputDirectory(queuedVideo.getOutputDirectory());
    }
    
}
