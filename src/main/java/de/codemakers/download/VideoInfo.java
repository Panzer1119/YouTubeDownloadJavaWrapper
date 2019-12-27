/*
 *    Copyright 2019 Paul Hagedorn (Panzer1119)
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

import java.time.Duration;

public class VideoInfo {
    
    private String id;
    private String title;
    private long duration;
    //Temp
    private transient String url = null;
    
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
    
    public long getDuration() {
        return duration;
    }
    
    public VideoInfo setDuration(String duration) {
        final String[] split = duration.split(":");
        Duration duration_ = Duration.ZERO;
        if (split.length > 0) {
            duration_ = duration_.plusSeconds(Long.parseLong(split[split.length - 1]));
        }
        if (split.length > 1) {
            duration_ = duration_.plusMinutes(Long.parseLong(split[split.length - 2]));
        }
        if (split.length > 2) {
            duration_ = duration_.plusHours(Long.parseLong(split[split.length - 3]));
        }
        return setDuration(duration_.toMillis());
    }
    
    public VideoInfo setDuration(long duration) {
        this.duration = duration;
        return this;
    }
    
    public String getUrl() {
        return url;
    }
    
    public VideoInfo setUrl(String url) {
        this.url = url;
        return this;
    }
    
    @Override
    public String toString() {
        return "VideoInfo{" + "id='" + id + '\'' + ", title='" + title + '\'' + ", duration=" + duration + ", url='" + url + '\'' + '}';
    }
    
}
