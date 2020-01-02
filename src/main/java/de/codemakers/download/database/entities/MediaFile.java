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

public class MediaFile extends AbstractFile<MediaFile> {
    
    private String format;
    private String vcodec;
    private String acodec;
    private int width;
    private int height;
    private int fps;
    private int asr;
    
    public MediaFile() {
        this(null, null, null, null, null, null, -1, -1, -1, -1);
    }
    
    public MediaFile(String videoId, String file, String fileType, String format, String vcodec, String acodec, int width, int height, int fps, int asr) {
        super(videoId, file, fileType);
        this.format = format;
        this.vcodec = vcodec;
        this.acodec = acodec;
        this.width = width;
        this.height = height;
        this.fps = fps;
        this.asr = asr;
    }
    
    public String getFormat() {
        return format;
    }
    
    public MediaFile setFormat(String format) {
        this.format = format;
        return this;
    }
    
    public String getVcodec() {
        return vcodec;
    }
    
    public MediaFile setVcodec(String vcodec) {
        this.vcodec = vcodec;
        return this;
    }
    
    public String getAcodec() {
        return acodec;
    }
    
    public MediaFile setAcodec(String acodec) {
        this.acodec = acodec;
        return this;
    }
    
    public int getWidth() {
        return width;
    }
    
    public MediaFile setWidth(int width) {
        this.width = width;
        return this;
    }
    
    public int getHeight() {
        return height;
    }
    
    public MediaFile setHeight(int height) {
        this.height = height;
        return this;
    }
    
    public int getFps() {
        return fps;
    }
    
    public MediaFile setFps(int fps) {
        this.fps = fps;
        return this;
    }
    
    public int getAsr() {
        return asr;
    }
    
    public MediaFile setAsr(int asr) {
        this.asr = asr;
        return this;
    }
    
    @Override
    public String toString() {
        return "MediaFile{" + "format='" + format + '\'' + ", vcodec='" + vcodec + '\'' + ", acodec='" + acodec + '\'' + ", width=" + width + ", height=" + height + ", fps=" + fps + ", asr=" + asr + ", videoId='" + videoId + '\'' + ", file='" + file + '\'' + ", fileType='" + fileType + '\'' + '}';
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
        final MediaFile mediaFile = (MediaFile) other;
        return width == mediaFile.width && height == mediaFile.height && fps == mediaFile.fps && asr == mediaFile.asr && Objects.equals(format, mediaFile.format) && Objects.equals(vcodec, mediaFile.vcodec) && Objects.equals(acodec, mediaFile.acodec);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), format, vcodec, acodec, width, height, fps, asr);
    }
    
    @Override
    protected MediaFile getFromDatabase() {
        return getDatabase().getMediaFileByVideoIdAndFile(getVideoId(), getFile());
    }
    
    @Override
    public boolean save() {
        return getDatabase().saveMediaFile(this);
    }
    
    @Override
    public void set(MediaFile mediaFile) {
        if (mediaFile == null) {
            return;
        }
        setVideoId(mediaFile.getVideoId());
        setFile(mediaFile.getFile());
        setFileType(mediaFile.getFileType());
        setFormat(mediaFile.getFormat());
        setVcodec(mediaFile.getVcodec());
        setAcodec(mediaFile.getAcodec());
        setWidth(mediaFile.getWidth());
        setHeight(mediaFile.getHeight());
        setFps(mediaFile.getFps());
        setAsr(mediaFile.getAsr());
    }
    
}
