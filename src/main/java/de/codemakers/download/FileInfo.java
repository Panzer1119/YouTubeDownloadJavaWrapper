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

package de.codemakers.download;

public class FileInfo {
    
    private VideoInfo videoInfo;
    private String format;
    private int width;
    private int height;
    private int fps;
    private int asr;
    private String playlist;
    private String playlistId;
    private String playlistTitle;
    private String playlistIndex;
    private String playlistUploader;
    private String playlistUploaderId;
    
    public FileInfo(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
    }
    
    public VideoInfo getVideoInfo() {
        return videoInfo;
    }
    
    public FileInfo setVideoInfo(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
        return this;
    }
    
    public String getFormat() {
        return format;
    }
    
    public FileInfo setFormat(String format) {
        this.format = format;
        return this;
    }
    
    public int getWidth() {
        return width;
    }
    
    public FileInfo setWidth(String width) {
        if (width == null || width.equals("NA") || width.isEmpty()) {
            return setWidth(-1);
        }
        return setWidth(Integer.parseInt(width));
    }
    
    public FileInfo setWidth(int width) {
        this.width = width;
        return this;
    }
    
    public int getHeight() {
        return height;
    }
    
    public FileInfo setHeight(String height) {
        if (height == null || height.equals("NA") || height.isEmpty()) {
            return setHeight(-1);
        }
        return setHeight(Integer.parseInt(height));
    }
    
    public FileInfo setHeight(int height) {
        this.height = height;
        return this;
    }
    
    public int getFps() {
        return fps;
    }
    
    public FileInfo setFps(String fps) {
        if (fps == null || fps.equals("NA") || fps.isEmpty()) {
            return setFps(-1);
        }
        return setFps(Integer.parseInt(fps));
    }
    
    public FileInfo setFps(int fps) {
        this.fps = fps;
        return this;
    }
    
    public int getAsr() {
        return asr;
    }
    
    public FileInfo setAsr(String asr) {
        if (asr == null || asr.equals("NA") || asr.isEmpty()) {
            return setAsr(-1);
        }
        return setAsr(Integer.parseInt(asr));
    }
    
    public FileInfo setAsr(int asr) {
        this.asr = asr;
        return this;
    }
    
    public String getPlaylist() {
        return playlist;
    }
    
    public FileInfo setPlaylist(String playlist) {
        this.playlist = playlist;
        return this;
    }
    
    public String getPlaylistId() {
        return playlistId;
    }
    
    public FileInfo setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
        return this;
    }
    
    public String getPlaylistTitle() {
        return playlistTitle;
    }
    
    public FileInfo setPlaylistTitle(String playlistTitle) {
        this.playlistTitle = playlistTitle;
        return this;
    }
    
    public String getPlaylistIndex() {
        return playlistIndex;
    }
    
    public FileInfo setPlaylistIndex(String playlistIndex) {
        this.playlistIndex = playlistIndex;
        return this;
    }
    
    public String getPlaylistUploader() {
        return playlistUploader;
    }
    
    public FileInfo setPlaylistUploader(String playlistUploader) {
        this.playlistUploader = playlistUploader;
        return this;
    }
    
    public String getPlaylistUploaderId() {
        return playlistUploaderId;
    }
    
    public FileInfo setPlaylistUploaderId(String playlistUploaderId) {
        this.playlistUploaderId = playlistUploaderId;
        return this;
    }
    
    @Override
    public String toString() {
        return "FileInfo{" + "videoInfo=" + videoInfo + ", format='" + format + '\'' + ", width=" + width + ", height=" + height + ", fps=" + fps + ", asr=" + asr + ", playlist='" + playlist + '\'' + ", playlistId='" + playlistId + '\'' + ", playlistTitle='" + playlistTitle + '\'' + ", playlistIndex='" + playlistIndex + '\'' + ", playlistUploader='" + playlistUploader + '\'' + ", playlistUploaderId='" + playlistUploaderId + '\'' + '}';
    }
    
}
