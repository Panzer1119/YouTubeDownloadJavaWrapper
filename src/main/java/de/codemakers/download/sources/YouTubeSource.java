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

package de.codemakers.download.sources;

import de.codemakers.download.YouTubeDL;

import java.net.MalformedURLException;
import java.net.URL;

public class YouTubeSource implements Source {
    
    private final String id;
    private transient String url;
    
    protected YouTubeSource(String id) {
        this(id, null);
    }
    
    protected YouTubeSource(String id, String url) {
        this.id = id;
        this.url = url;
        System.out.println("I have been created: " + this);
    }
    
    @Override
    public String getId() {
        return id;
    }
    
    public URL toUrl() throws MalformedURLException {
        if (getUrl() == null) {
            return null;
        }
        return new URL(getUrl());
    }
    
    public String getUrl() {
        return url;
    }
    
    public YouTubeSource setUrl(String url) {
        this.url = url;
        return this;
    }
    
    @Override
    public String getSource() {
        final String temp = getUrl();
        if (temp == null) {
            return getId();
        }
        return temp;
    }
    
    @Override
    public String toString() {
        return "YouTubeSource{" + "id='" + id + '\'' + ", url='" + url + '\'' + '}';
    }
    
    public static YouTubeSource ofId(String id) {
        return new YouTubeSource(id);
    }
    
    public static YouTubeSource ofUrl(String url) {
        return new YouTubeSource(YouTubeDL.getIdFromYouTubeUrl(url), url);
    }
    
    public static YouTubeSource ofString(String source) {
        if (YouTubeDL.PATTERN_YOUTUBE_URL.matcher(source).matches()) {
            return ofUrl(source);
        }
        return ofId(source);
    }
    
}
