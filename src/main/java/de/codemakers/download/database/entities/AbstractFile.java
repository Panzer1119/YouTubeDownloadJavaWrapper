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

import java.util.Objects;

public abstract class AbstractFile<T> extends AbstractEntity<T> {
    
    protected String videoId;
    protected String file;
    protected String fileType;
    
    public AbstractFile() {
        this(null, null, null);
    }
    
    public AbstractFile(String videoId, String file, String fileType) {
        this.videoId = videoId;
        this.file = file;
        this.fileType = fileType;
    }
    
    public String getVideoId() {
        return videoId;
    }
    
    public T setVideoId(String videoId) {
        this.videoId = videoId;
        return (T) this;
    }
    
    public String getFile() {
        return file;
    }
    
    public T setFile(String file) {
        this.file = file;
        return (T) this;
    }
    
    public String getFileType() {
        return fileType;
    }
    
    public T setFileType(String fileType) {
        this.fileType = fileType;
        return (T) this;
    }
    
    @Override
    public String toString() {
        return "AbstractFile{" + "videoId='" + videoId + '\'' + ", file='" + file + '\'' + ", fileType='" + fileType + '\'' + '}';
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final AbstractFile<?> that = (AbstractFile<?>) other;
        return Objects.equals(videoId, that.videoId) && Objects.equals(file, that.file) && Objects.equals(fileType, that.fileType);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(videoId, file, fileType);
    }
    
}
