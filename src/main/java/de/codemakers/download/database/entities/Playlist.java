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

import java.util.List;
import java.util.Objects;

public class Playlist extends AbstractEntity<Playlist> {
    
    private String id;
    private String title;
    private String playlist;
    private String uploader;
    private String uploaderId;
    
    public Playlist() {
        this(null, null, null, null, null);
    }
    
    public Playlist(String id, String title, String playlist, String uploader, String uploaderId) {
        this.id = id;
        this.title = title;
        this.playlist = playlist;
        this.uploader = uploader;
        this.uploaderId = uploaderId;
    }
    
    public String getId() {
        return id;
    }
    
    public Playlist setId(String id) {
        this.id = id;
        return this;
    }
    
    public String getTitle() {
        return title;
    }
    
    public Playlist setTitle(String title) {
        this.title = title;
        return this;
    }
    
    public String getPlaylist() {
        return playlist;
    }
    
    public Playlist setPlaylist(String playlist) {
        this.playlist = playlist;
        return this;
    }
    
    public String getUploader() {
        return uploader;
    }
    
    public Playlist setUploader(String uploader) {
        this.uploader = uploader;
        return this;
    }
    
    public String getUploaderId() {
        return uploaderId;
    }
    
    public Playlist setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
        return this;
    }
    
    public List<Video> getVideos() {
        return getDatabase().getVideosInPlaylist(getId());
    }
    
    public boolean containsVideo(String videoId) {
        return getDatabase().isVideoInPlaylist(videoId, getId());
    }
    
    public int getIndexOfVideo(String videoId) {
        return getDatabase().getIndexOfVideoInPlaylist(videoId, getId());
    }
    
    @Override
    public String toString() {
        return "Playlist{" + "id='" + id + '\'' + ", title='" + title + '\'' + ", playlist='" + playlist + '\'' + ", uploader='" + uploader + '\'' + ", uploaderId='" + uploaderId + '\'' + '}';
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final Playlist playlist1 = (Playlist) other;
        return Objects.equals(id, playlist1.id) && Objects.equals(title, playlist1.title) && Objects.equals(playlist, playlist1.playlist) && Objects.equals(uploader, playlist1.uploader) && Objects.equals(uploaderId, playlist1.uploaderId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, title, playlist, uploader, uploaderId);
    }
    
    @Override
    protected Playlist getFromDatabase() {
        return getDatabase().getPlaylistById(getId());
    }
    
    @Override
    public boolean save() {
        return getDatabase().savePlaylist(this);
    }
    
    @Override
    public void set(Playlist playlist) {
        if (playlist == null) {
            return;
        }
        setId(playlist.getId());
        setTitle(playlist.getTitle());
        setPlaylist(playlist.getPlaylist());
        setUploader(playlist.getUploader());
        setUploaderId(playlist.getUploaderId());
    }
    
}
