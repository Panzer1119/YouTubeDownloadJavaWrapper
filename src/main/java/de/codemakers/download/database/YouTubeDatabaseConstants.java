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

package de.codemakers.download.database;

public class YouTubeDatabaseConstants {
    
    // // // Identifiers
    // // Tables
    // Database: YouTube
    public static final String IDENTIFIER_TABLE_CHANNELS = "channels";
    public static final String IDENTIFIER_TABLE_EXTRA_FILES = "extraFiles";
    public static final String IDENTIFIER_TABLE_MEDIA_FILES = "mediaFiles";
    public static final String IDENTIFIER_TABLE_PLAYLISTS = "playlists";
    public static final String IDENTIFIER_TABLE_PLAYLIST_VIDEOS = "playlistVideos";
    public static final String IDENTIFIER_TABLE_UPLOADERS = "uploaders";
    public static final String IDENTIFIER_TABLE_VIDEO_QUEUE = "videoQueue";
    public static final String IDENTIFIER_TABLE_VIDEOS = "videos";
    //
    // // Columns
    // Table: Channels
    public static final String IDENTIFIER_TABLE_CHANNELS_COLUMN_ID = "id";
    public static final String IDENTIFIER_TABLE_CHANNELS_COLUMN_NAME = "name";
    // Table: Extra Files
    public static final String IDENTIFIER_TABLE_EXTRA_FILES_COLUMN_VIDEO_ID = "videoId";
    public static final String IDENTIFIER_TABLE_EXTRA_FILES_COLUMN_FILE = "file";
    public static final String IDENTIFIER_TABLE_EXTRA_FILES_COLUMN_FILE_TYPE = "fileType";
    // Table: Media Files
    public static final String IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_VIDEO_ID = "videoId";
    public static final String IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_FILE = "file";
    public static final String IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_FILE_TYPE = "fileType";
    public static final String IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_FORMAT = "format";
    public static final String IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_VCODEC = "vcodec";
    public static final String IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_ACODEC = "acodec";
    public static final String IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_WIDTH = "width";
    public static final String IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_HEIGHT = "height";
    public static final String IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_FPS = "fps";
    public static final String IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_ASR = "asr";
    // Table: Playlists
    public static final String IDENTIFIER_TABLE_PLAYLISTS_COLUMN_ID = "id";
    public static final String IDENTIFIER_TABLE_PLAYLISTS_COLUMN_TITLE = "title";
    public static final String IDENTIFIER_TABLE_PLAYLISTS_COLUMN_PLAYLIST = "playlist";
    public static final String IDENTIFIER_TABLE_PLAYLISTS_COLUMN_UPLOADER_ID = "uploaderId";
    // Table: Playlist Videos
    public static final String IDENTIFIER_TABLE_PLAYLIST_VIDEOS_COLUMN_PLAYLIST_ID = "playlistId";
    public static final String IDENTIFIER_TABLE_PLAYLIST_VIDEOS_COLUMN_VIDEO_ID = "videoId";
    public static final String IDENTIFIER_TABLE_PLAYLIST_VIDEOS_COLUMN_PLAYLIST_INDEX = "playlistIndex";
    // Table: Uploaders
    public static final String IDENTIFIER_TABLE_UPLOADERS_COLUMN_ID = "id";
    public static final String IDENTIFIER_TABLE_UPLOADERS_COLUMN_NAME = "name";
    // Table: Video Queue
    public static final String IDENTIFIER_TABLE_VIDEO_QUEUE_COLUMN_ID = "id";
    public static final String IDENTIFIER_TABLE_VIDEO_QUEUE_COLUMN_VIDEO_ID = "videoId";
    public static final String IDENTIFIER_TABLE_VIDEO_QUEUE_COLUMN_PRIORITY = "priority";
    public static final String IDENTIFIER_TABLE_VIDEO_QUEUE_COLUMN_REQUESTED = "requested";
    public static final String IDENTIFIER_TABLE_VIDEO_QUEUE_COLUMN_ARGUMENTS = "arguments";
    public static final String IDENTIFIER_TABLE_VIDEO_QUEUE_COLUMN_CONFIG_FILE = "configFile";
    public static final String IDENTIFIER_TABLE_VIDEO_QUEUE_COLUMN_OUTPUT_DIRECTORY = "outputDirectory";
    // Table: Videos
    public static final String IDENTIFIER_TABLE_VIDEOS_COLUMN_ID = "id";
    public static final String IDENTIFIER_TABLE_VIDEOS_COLUMN_CHANNEL_ID = "channelId";
    public static final String IDENTIFIER_TABLE_VIDEOS_COLUMN_TITLE = "title";
    public static final String IDENTIFIER_TABLE_VIDEOS_COLUMN_ALT_TITLE = "altTitle";
    public static final String IDENTIFIER_TABLE_VIDEOS_COLUMN_DURATION = "duration";
    public static final String IDENTIFIER_TABLE_VIDEOS_COLUMN_UPLOAD_DATE = "uploadDate";
    //
    // //
    // // // Queries
    // // Selects
    // Table: Channels
    /**
     * No arguments
     */
    public static final String QUERY_TABLE_CHANNELS_SELECT_ALL = String.format("SELECT * FROM %s;", IDENTIFIER_TABLE_CHANNELS);
    /**
     * 1. Argument: Channel ID
     */
    public static final String QUERY_TABLE_CHANNELS_SELECT_BY_CHANNEL_ID = String.format("SELECT * FROM %s WHERE %s = ?;", IDENTIFIER_TABLE_CHANNELS, IDENTIFIER_TABLE_CHANNELS_COLUMN_ID);
    // Table: Extra Files
    // Table: Media Files
    // Table: Playlists
    // Table: Playlist Videos
    // Table: Uploaders
    /**
     * No arguments
     */
    public static final String QUERY_TABLE_UPLOADERS_SELECT_ALL = String.format("SELECT * FROM %s;", IDENTIFIER_TABLE_UPLOADERS);
    /**
     * 1. Argument: Uploader ID
     */
    public static final String QUERY_TABLE_UPLOADERS_SELECT_BY_UPLOADER_ID = String.format("SELECT * FROM %s WHERE %s = ?;", IDENTIFIER_TABLE_UPLOADERS, IDENTIFIER_TABLE_UPLOADERS_COLUMN_ID);
    // Table: Video Queue
    // Table: Videos
    /**
     * No arguments
     */
    public static final String QUERY_TABLE_VIDEOS_SELECT_ALL = String.format("SELECT * FROM %s;", IDENTIFIER_TABLE_VIDEOS);
    /**
     * 1. Argument: Video ID
     */
    public static final String QUERY_TABLE_VIDEOS_SELECT_BY_VIDEO_ID = String.format("SELECT * FROM %s WHERE %s = ?;", IDENTIFIER_TABLE_VIDEOS, IDENTIFIER_TABLE_VIDEOS_COLUMN_ID);
    //
    // // Inserts
    // Table: Channels
    /**
     * 1. Argument: Channel ID
     * <br>
     * 2. Argument: Name
     */
    public static final String QUERY_TABLE_CHANNELS_INSERT = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?);", IDENTIFIER_TABLE_CHANNELS, IDENTIFIER_TABLE_CHANNELS_COLUMN_ID, IDENTIFIER_TABLE_CHANNELS_COLUMN_NAME);
    // Table: Extra Files
    // Table: Media Files
    // Table: Playlists
    // Table: Playlist Videos
    // Table: Uploaders
    /**
     * 1. Argument: Uploader ID
     * <br>
     * 2. Argument: Name
     */
    public static final String QUERY_TABLE_UPLOADERS_INSERT = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?);", IDENTIFIER_TABLE_UPLOADERS, IDENTIFIER_TABLE_UPLOADERS_COLUMN_ID, IDENTIFIER_TABLE_UPLOADERS_COLUMN_NAME);
    // Table: Video Queue
    // Table: Videos
    /**
     * 1. Argument: Video ID
     * <br>
     * 2. Argument: Channel ID
     * <br>
     * 3. Argument: Title
     * <br>
     * 4. Argument: Alt Title
     * <br>
     * 5. Argument: Duration
     * <br>
     * 6. Argument: Upload Date
     */
    public static final String QUERY_TABLE_VIDEOS_INSERT = String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?);", IDENTIFIER_TABLE_VIDEOS, IDENTIFIER_TABLE_VIDEOS_COLUMN_ID, IDENTIFIER_TABLE_VIDEOS_COLUMN_CHANNEL_ID, IDENTIFIER_TABLE_VIDEOS_COLUMN_TITLE, IDENTIFIER_TABLE_VIDEOS_COLUMN_ALT_TITLE, IDENTIFIER_TABLE_VIDEOS_COLUMN_DURATION, IDENTIFIER_TABLE_VIDEOS_COLUMN_UPLOAD_DATE);
    //
    // // Updates
    // Table: Channels
    /**
     * 1. Argument: Name
     * <br>
     * 2. Argument: Channel ID
     */
    public static final String QUERY_TABLE_CHANNELS_UPDATE_BY_CHANNEL_ID = String.format("UPDATE %s SET %s = ? WHERE %s = ?;", IDENTIFIER_TABLE_CHANNELS, IDENTIFIER_TABLE_CHANNELS_COLUMN_NAME, IDENTIFIER_TABLE_CHANNELS_COLUMN_ID);
    // Table: Extra Files
    // Table: Media Files
    // Table: Playlists
    // Table: Playlist Videos
    // Table: Uploaders
    /**
     * 1. Argument: Name
     * <br>
     * 2. Argument: Uploader ID
     */
    public static final String QUERY_TABLE_UPLOADERS_UPDATE_BY_UPLOADER_ID = String.format("UPDATE %s SET %s = ? WHERE %s = ?;", IDENTIFIER_TABLE_UPLOADERS, IDENTIFIER_TABLE_UPLOADERS_COLUMN_NAME, IDENTIFIER_TABLE_UPLOADERS_COLUMN_ID);
    // Table: Video Queue
    // Table: Videos
    /**
     * 1. Argument: (Old) Video ID
     * <br>
     * 2. Argument: Channel ID
     * <br>
     * 3. Argument: Title
     * <br>
     * 4. Argument: Alt Title
     * <br>
     * 5. Argument: Duration
     * <br>
     * 6. Argument: Upload Date
     * <br>
     * 7. Argument: (New) Video ID
     */
    public static final String QUERY_TABLE_VIDEOS_UPDATE_BY_VIDEO_ID = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?;", IDENTIFIER_TABLE_VIDEOS, IDENTIFIER_TABLE_VIDEOS_COLUMN_ID, IDENTIFIER_TABLE_VIDEOS_COLUMN_CHANNEL_ID, IDENTIFIER_TABLE_VIDEOS_COLUMN_TITLE, IDENTIFIER_TABLE_VIDEOS_COLUMN_ALT_TITLE, IDENTIFIER_TABLE_VIDEOS_COLUMN_DURATION, IDENTIFIER_TABLE_VIDEOS_COLUMN_UPLOAD_DATE, IDENTIFIER_TABLE_VIDEOS_COLUMN_ID);
    //
    // // Deletes
    // Table: Channels
    /**
     * No arguments
     */
    public static final String QUERY_TABLE_CHANNELS_DELETE_ALL = String.format("DELETE FROM %s;", IDENTIFIER_TABLE_CHANNELS);
    /**
     * 1. Argument: Channel ID
     */
    public static final String QUERY_TABLE_CHANNELS_DELETE_BY_CHANNEL_ID = String.format("DELETE FROM %s WHERE %s = ?;", IDENTIFIER_TABLE_CHANNELS, IDENTIFIER_TABLE_CHANNELS_COLUMN_ID);
    // Table: Extra Files
    // Table: Media Files
    // Table: Playlists
    // Table: Playlist Videos
    // Table: Uploaders
    /**
     * No arguments
     */
    public static final String QUERY_TABLE_UPLOADERS_DELETE_ALL = String.format("DELETE FROM %s;", IDENTIFIER_TABLE_UPLOADERS);
    /**
     * 1. Argument: Uploader ID
     */
    public static final String QUERY_TABLE_UPLOADERS_DELETE_BY_CHANNEL_ID = String.format("DELETE FROM %s WHERE %s = ?;", IDENTIFIER_TABLE_UPLOADERS, IDENTIFIER_TABLE_UPLOADERS_COLUMN_ID);
    // Table: Video Queue
    // Table: Videos
    /**
     * No arguments
     */
    public static final String QUERY_TABLE_VIDEOS_DELETE_ALL = String.format("DELETE FROM %s;", IDENTIFIER_TABLE_VIDEOS);
    /**
     * 1. Argument: Video ID
     */
    public static final String QUERY_TABLE_VIDEOS_DELETE_BY_VIDEO_ID = String.format("DELETE FROM %s WHERE %s = ?;", IDENTIFIER_TABLE_VIDEOS, IDENTIFIER_TABLE_VIDEOS_COLUMN_ID);
    /**
     * 1. Argument: Channel ID
     */
    public static final String QUERY_TABLE_VIDEOS_DELETE_BY_CHANNEL_ID = String.format("DELETE FROM %s WHERE %s = ?;", IDENTIFIER_TABLE_VIDEOS, IDENTIFIER_TABLE_VIDEOS_COLUMN_CHANNEL_ID);
    //
    // //
    // // //
    
}
