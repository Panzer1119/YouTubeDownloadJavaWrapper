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

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public abstract class AbstractVideo<T extends AbstractVideo, M extends AbstractFile, E extends AbstractFile, D extends AbstractDatabase, P extends AbstractPlaylist> extends AbstractEntity<T, D> {
    
    protected String videoId;
    protected String channelId;
    protected String title;
    protected String altTitle;
    protected long durationMillis;
    protected LocalDate uploadDate;
    
    public AbstractVideo() {
        this(null, null, null, null, -1, null);
    }
    
    public AbstractVideo(String videoId, String channelId, String title, String altTitle, long durationMillis, LocalDate uploadDate) {
        this.videoId = videoId;
        this.channelId = channelId;
        this.title = title;
        this.altTitle = altTitle;
        this.durationMillis = durationMillis;
        this.uploadDate = uploadDate;
    }
    
    public String getVideoId() {
        return videoId;
    }
    
    public T setVideoId(String videoId) {
        this.videoId = videoId;
        return (T) this;
    }
    
    public String getChannelId() {
        return channelId;
    }
    
    public T setChannelId(String channelId) {
        this.channelId = channelId;
        return (T) this;
    }
    
    public String getTitle() {
        return title;
    }
    
    public T setTitle(String title) {
        this.title = title;
        return (T) this;
    }
    
    public String getAltTitle() {
        return altTitle;
    }
    
    public T setAltTitle(String altTitle) {
        this.altTitle = altTitle;
        return (T) this;
    }
    
    public long getDurationMillis() {
        return durationMillis;
    }
    
    public Duration getDuration() {
        return Duration.ofMillis(getDurationMillis());
    }
    
    public T setDurationMillis(long durationMillis) {
        this.durationMillis = durationMillis;
        return (T) this;
    }
    
    public T setDuration(Duration duration) {
        return setDurationMillis(duration.toMillis());
    }
    
    public LocalDate getUploadDate() {
        return uploadDate;
    }
    
    public T setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
        return (T) this;
    }
    
    public List<P> getPlaylists() {
        return useDatabaseOrNull((database) -> database.getPlaylistsByVideoId(getVideoId()));
    }
    
    public List<String> getPlaylistIds() {
        return useDatabaseOrNull((database) -> database.getPlaylistIdsByVideoId(getVideoId()));
    }
    
    public boolean isInPlaylist(P playlist) {
        if (playlist == null) {
            return false;
        }
        return isInPlaylist(playlist.getPlaylistId());
    }
    
    public boolean isInPlaylist(final String playlistId) {
        return useDatabase((database) -> database.playlistContainsVideo(playlistId, getVideoId()), false);
    }
    
    public int getIndexInPlaylist(P playlist) {
        if (playlist == null) {
            return -1;
        }
        return getIndexInPlaylist(playlist.getPlaylistId());
    }
    
    public abstract int getIndexInPlaylist(String playlistId);
    
    public List<M> getMediaFiles() {
        return useDatabaseOrNull((database) -> database.getMediaFilesByVideoId(getVideoId()));
    }
    
    public List<E> getExtraFiles() {
        return useDatabaseOrNull((database) -> database.getExtraFilesByVideoId(getVideoId()));
    }
    
}
