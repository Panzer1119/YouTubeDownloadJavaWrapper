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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum YouTubeSourceType {
    INVALID(null),
    UNKNOWN(YouTubeDL.PATTERN_YOUTUBE_URL),
    VIDEO(YouTubeDL.PATTERN_YOUTUBE_VIDEO_URL) {
        @Override
        public boolean providesVideoId() {
            return true;
        }
        
        @Override
        public String getVideoId(String url) {
            final Matcher matcher = matcher(url);
            if (!matcher.matches()) {
                return null;
            }
            return matcher.group(1);
        }
    },
    VIDEO_IN_PLAYLIST(YouTubeDL.PATTERN_YOUTUBE_VIDEO_IN_PLAYLIST_URL) {
        @Override
        public boolean providesVideoId() {
            return true;
        }
        
        @Override
        public boolean providesPlaylistId() {
            return true;
        }
        
        @Override
        public String getVideoId(String url) {
            final Matcher matcher = matcher(url);
            if (!matcher.matches()) {
                return null;
            }
            return matcher.group(1);
        }
        
        @Override
        public String getPlaylistId(String url) {
            final Matcher matcher = matcher(url);
            if (!matcher.matches()) {
                return null;
            }
            return matcher.group(2);
        }
    
        @Override
        public boolean providesMultipleVideos() {
            return true;
        }
    },
    PLAYLIST(YouTubeDL.PATTERN_YOUTUBE_PLAYLIST_URL) {
        @Override
        public boolean providesPlaylistId() {
            return true;
        }
        
        @Override
        public String getPlaylistId(String url) {
            final Matcher matcher = matcher(url);
            if (!matcher.matches()) {
                return null;
            }
            return matcher.group(1);
        }
    
        @Override
        public boolean providesMultipleVideos() {
            return true;
        }
    },
    USER(YouTubeDL.PATTERN_YOUTUBE_USER_URL) {
        @Override
        public boolean providesUsername() {
            return true;
        }
        
        @Override
        public String getUsername(String url) {
            final Matcher matcher = matcher(url);
            if (!matcher.matches()) {
                return null;
            }
            return matcher.group(1);
        }
    
        @Override
        public boolean providesMultipleVideos() {
            return true;
        }
    },
    CHANNEL(YouTubeDL.PATTERN_YOUTUBE_CHANNEL_URL) {
        @Override
        public boolean providesChannelId() {
            return true;
        }
        
        @Override
        public String getChannelId(String url) {
            final Matcher matcher = matcher(url);
            if (!matcher.matches()) {
                return null;
            }
            return matcher.group(1);
        }
    
        @Override
        public boolean providesMultipleVideos() {
            return true;
        }
    };
    
    private final Pattern pattern;
    
    YouTubeSourceType(Pattern pattern) {
        this.pattern = pattern;
    }
    
    public Pattern getPattern() {
        return pattern;
    }
    
    public Matcher matcher(String text) {
        return pattern.matcher(text);
    }
    
    public boolean providesMultipleVideos() {
        return false;
    }
    
    public boolean providesVideoId() {
        return false;
    }
    
    public boolean providesPlaylistId() {
        return false;
    }
    
    public boolean providesUsername() {
        return false;
    }
    
    public boolean providesChannelId() {
        return false;
    }
    
    public String getVideoId(String url) {
        return null;
    }
    
    public String getPlaylistId(String url) {
        return null;
    }
    
    public String getUsername(String url) {
        return null;
    }
    
    public String getChannelId(String url) {
        return null;
    }
    
    public static final YouTubeSourceType ofURL(String url) {
        if (USER.matcher(url).matches()) {
            return USER;
        }
        if (CHANNEL.matcher(url).matches()) {
            return CHANNEL;
        }
        if (PLAYLIST.matcher(url).matches()) {
            return PLAYLIST;
        }
        if (VIDEO_IN_PLAYLIST.matcher(url).matches()) {
            return VIDEO_IN_PLAYLIST;
        }
        if (VIDEO.matcher(url).matches()) {
            return VIDEO;
        }
        if (UNKNOWN.matcher(url).matches()) {
            return UNKNOWN;
        }
        return INVALID;
    }
}
