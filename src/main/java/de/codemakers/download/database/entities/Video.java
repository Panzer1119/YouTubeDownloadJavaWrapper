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

import de.codemakers.download.Misc;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class Video extends AbstractEntity<Video> {
    
    public static final DateTimeFormatter DATE_TIME_FORMATTER_UPLOAD_DATE = DateTimeFormatter.ofPattern("yyyyMMdd");
    
    protected String id;
    protected String uploader;
    protected String uploaderId;
    protected String title;
    protected String altTitle;
    protected long duration;
    protected LocalDate uploadDate;
    
    public Video() {
        this(null, null, null, null, null, -1, null);
    }
    
    public Video(String id, String uploader, String uploaderId, String title, String altTitle, long duration, long uploadDate) {
        this(id, uploader, uploaderId, title, altTitle, duration, null);
        setUploadDate(uploadDate);
    }
    
    public Video(String id, String uploader, String uploaderId, String title, String altTitle, long duration, LocalDate uploadDate) {
        this.id = id;
        this.uploader = uploader;
        this.uploaderId = uploaderId;
        this.title = title;
        this.altTitle = altTitle;
        this.duration = duration;
        this.uploadDate = uploadDate;
    }
    
    public String getId() {
        return id;
    }
    
    public Video setId(String id) {
        this.id = id;
        return this;
    }
    
    public String getUploader() {
        return uploader;
    }
    
    public Video setUploader(String uploader) {
        this.uploader = uploader;
        return this;
    }
    
    public String getUploaderId() {
        return uploaderId;
    }
    
    public Video setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
        return this;
    }
    
    public String getTitle() {
        return title;
    }
    
    public Video setTitle(String title) {
        this.title = title;
        return this;
    }
    
    public String getAltTitle() {
        return altTitle;
    }
    
    public Video setAltTitle(String altTitle) {
        this.altTitle = altTitle;
        return this;
    }
    
    public String getDurationAsString() {
        return Misc.durationToString(getDuration());
    }
    
    public Duration getDuration() {
        return Duration.ofMillis(getDurationAsMillis());
    }
    
    public long getDurationAsMillis() {
        return duration;
    }
    
    public Video setDuration(String duration) {
        return setDuration(Misc.stringToDuration(duration));
    }
    
    public Video setDuration(Duration duration) {
        return setDuration(duration.toMillis());
    }
    
    public Video setDuration(long duration) {
        this.duration = duration;
        return this;
    }
    
    public long getUploadDateAsLong() {
        if (uploadDate == null) {
            return -1;
        }
        return Long.parseLong(getUploadDate().format(DATE_TIME_FORMATTER_UPLOAD_DATE));
    }
    
    public LocalDate getUploadDate() {
        return uploadDate;
    }
    
    public Video setUploadDate(long uploadDate) {
        if (uploadDate < 0) {
            return setUploadDate((LocalDate) null);
        }
        return setUploadDate("" + uploadDate);
    }
    
    public Video setUploadDate(String uploadDate) {
        return setUploadDate(LocalDate.parse(uploadDate, DATE_TIME_FORMATTER_UPLOAD_DATE));
    }
    
    public Video setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
        return this;
    }
    
    public List<Playlist> getPlaylists() {
        return getDatabase().getPlaylistsContainingVideo(getId());
    }
    
    public boolean isInPlaylist(String playlistId) {
        return getDatabase().isVideoInPlaylist(getId(), playlistId);
    }
    
    public int getIndexInPlaylist(String playlistId) {
        return getDatabase().getIndexOfVideoInPlaylist(getId(), playlistId);
    }
    
    public List<MediaFile> getMediaFiles() {
        return getDatabase().getMediaFilesForVideo(getId());
    }
    
    public List<ExtraFile> getExtraFiles() {
        return getDatabase().getExtraFilesForVideo(getId());
    }
    
    @Override
    public String toString() {
        return "Video{" + "id='" + id + '\'' + ", uploader='" + uploader + '\'' + ", uploaderId='" + uploaderId + '\'' + ", title='" + title + '\'' + ", altTitle='" + altTitle + '\'' + ", duration=" + duration + ", uploadDate=" + uploadDate + '}';
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final Video video = (Video) other;
        return duration == video.duration && Objects.equals(id, video.id) && Objects.equals(uploader, video.uploader) && Objects.equals(uploaderId, video.uploaderId) && Objects.equals(title, video.title) && Objects.equals(altTitle, video.altTitle) && Objects.equals(uploadDate, video.uploadDate);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, uploader, uploaderId, title, altTitle, duration, uploadDate);
    }
    
    @Override
    protected Video getFromDatabase() {
        return getDatabase().getVideoById(getId());
    }
    
    @Override
    public boolean save() {
        return getDatabase().saveVideo(this);
    }
    
    @Override
    public void set(Video video) {
        if (video == null) {
            return;
        }
        setId(video.getId());
        setUploader(video.getUploader());
        setUploaderId(video.getUploaderId());
        setTitle(video.getTitle());
        setAltTitle(video.getAltTitle());
        setDuration(video.getDurationAsMillis());
        setUploadDate(video.getUploadDate());
    }
    
}
