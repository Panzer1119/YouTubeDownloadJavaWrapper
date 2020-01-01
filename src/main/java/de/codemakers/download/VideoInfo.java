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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class VideoInfo implements Serializable {
    
    public static final String PREFIX_YOUTUBE_VIDEO_URL = "https://youtube.com/watch?v=";
    
    private String id;
    private String title;
    private long duration;
    //Temp
    private String url = null;
    private final List<String> files = new ArrayList<>();
    //Extra
    private String uploader = null;
    private ZonedDateTime uploadDate = null;
    private JsonObject information = null;
    
    public VideoInfo() {
        this(null);
    }
    
    public VideoInfo(String id) {
        this(id, null, -1);
    }
    
    public VideoInfo(String id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }
    
    public String getId() {
        return id;
    }
    
    public VideoInfo setId(String id) {
        this.id = id;
        return this;
    }
    
    public String getTitle() {
        return title;
    }
    
    public VideoInfo setTitle(String title) {
        this.title = title;
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
    
    public VideoInfo setDuration(String duration) {
        return setDuration(Misc.stringToDuration(duration));
    }
    
    public VideoInfo setDuration(Duration duration) {
        return setDuration(duration.toMillis());
    }
    
    public VideoInfo setDuration(long duration) {
        this.duration = duration;
        return this;
    }
    
    //Temp
    
    public String getUrl() {
        if (url == null) {
            return PREFIX_YOUTUBE_VIDEO_URL + getId();
        }
        return url;
    }
    
    public VideoInfo setUrl(String url) {
        this.url = url;
        return this;
    }
    
    public List<String> getFiles() {
        return files;
    }
    
    //Extra
    
    public String getUploader() {
        return uploader;
    }
    
    public VideoInfo setUploader(String uploader) {
        this.uploader = uploader;
        return this;
    }
    
    public ZonedDateTime getUploadDate() {
        return uploadDate;
    }
    
    public VideoInfo setUploadDate(ZonedDateTime uploadDate) {
        this.uploadDate = uploadDate;
        return this;
    }
    
    public JsonObject getInformation() {
        return information;
    }
    
    public VideoInfo setInformation(JsonObject information) {
        this.information = information;
        return this;
    }
    
    public String toJsonString() {
        return Misc.GSON.toJson(this);
    }
    
    public JsonObject toJsonObject() {
        return (JsonObject) Misc.GSON.toJsonTree(this);
    }
    
    @Override
    public String toString() {
        return "VideoInfo{" + "id='" + id + '\'' + ", title='" + title + '\'' + ", duration=" + duration + ", url='" + url + '\'' + ", files=" + files + ", uploader='" + uploader + '\'' + ", uploadDate=" + uploadDate + ", information=" + information + '}';
    }
    
    public static JsonArray toJsonArray(Collection<VideoInfo> videoInfos) {
        final JsonArray jsonArray = new JsonArray();
        videoInfos.stream().map(VideoInfo::toJsonObject).forEach(jsonArray::add);
        return jsonArray;
    }
    
    public static VideoInfo fromJsonObjectString(String json) {
        return Misc.GSON.fromJson(json, VideoInfo.class);
    }
    
    public static VideoInfo fromJsonElement(JsonElement jsonElement) {
        return Misc.GSON.fromJson(jsonElement, VideoInfo.class);
    }
    
    public static List<VideoInfo> fromJsonArrayString(String json) {
        return fromJsonArray(Misc.GSON.fromJson(json, JsonArray.class));
    }
    
    public static List<VideoInfo> fromJsonArray(JsonArray jsonArray) {
        return StreamSupport.stream(jsonArray.spliterator(), true).map(VideoInfo::fromJsonElement).collect(Collectors.toList()); //TODO is parallel=true good?
    }
    
}
