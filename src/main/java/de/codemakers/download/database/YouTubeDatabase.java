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

import de.codemakers.download.database.entities.impl.ExtraFile;
import de.codemakers.download.database.entities.impl.MediaFile;
import de.codemakers.download.database.entities.impl.YouTubePlaylist;
import de.codemakers.download.database.entities.impl.YouTubeVideo;

import java.sql.PreparedStatement;
import java.util.List;

public class YouTubeDatabase<C extends AbstractConnector> extends AbstractDatabase<YouTubeDatabase, MediaFile, ExtraFile, YouTubeVideo, YouTubePlaylist, C> {
    
    // // Selects / Gets
    // Table: Channels
    private transient PreparedStatement preparedStatement_getAllChannels = null;
    private transient PreparedStatement preparedStatement_getChannelByChannelId = null;
    // Table: Extra Files
    // Table: Media Files
    // Table: Playlists
    // Table: Playlist Videos
    // Table: Uploaders
    private transient PreparedStatement preparedStatement_getAllUploaders = null;
    private transient PreparedStatement preparedStatement_getUploaderByUploaderId = null;
    // Table: Video Queue
    // Table: Videos
     private transient PreparedStatement preparedStatement_getAllVideos = null;
    private transient PreparedStatement preparedStatement_getVideoByVideoId = null;
    private transient PreparedStatement preparedStatement_getVideosByChannelId = null;
    //
    // // Inserts / Adds
    // Table: Channels
    private transient PreparedStatement preparedStatement_addChannel = null;
    // Table: Extra Files
    // Table: Media Files
    // Table: Playlists
    // Table: Playlist Videos
    // Table: Uploaders
    private transient PreparedStatement preparedStatement_addUploader = null;
    // Table: Video Queue
    // Table: Videos
    private transient PreparedStatement preparedStatement_addVideo = null;
    //
    // // Updates / Sets
    // Table: Channels
    private transient PreparedStatement preparedStatement_setChannelByChannelId = null;
    // Table: Extra Files
    // Table: Media Files
    // Table: Playlists
    // Table: Playlist Videos
    // Table: Uploaders
    private transient PreparedStatement preparedStatement_setUploaderByUploaderId = null;
    // Table: Video Queue
    // Table: Videos
    private transient PreparedStatement preparedStatement_setVideoByVideoId = null;
    private transient PreparedStatement preparedStatement_setVideosByChannelId = null;
    //
    // // Deletes / Removes
    // Table: Channels
    private transient PreparedStatement preparedStatement_removeAllChannels = null;
    private transient PreparedStatement preparedStatement_removeChannelByChannelId = null;
    // Table: Extra Files
    // Table: Media Files
    // Table: Playlists
    // Table: Playlist Videos
    // Table: Uploaders
    private transient PreparedStatement preparedStatement_removeAllUploaders = null;
    private transient PreparedStatement preparedStatement_removeUploaderByUploaderId = null;
    // Table: Video Queue
    // Table: Videos
    private transient PreparedStatement preparedStatement_removeAllVideos = null;
    private transient PreparedStatement preparedStatement_removeVideoByVideoId = null;
    private transient PreparedStatement preparedStatement_removeVideosByChannelId = null;
    //
    // //
    
    public YouTubeDatabase(C connector) {
        super(connector);
    }
    
    private void createStatements() {
        // // Selects / Gets
        // Table: Channels
        preparedStatement_getAllChannels = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_CHANNELS_SELECT_ALL);
        preparedStatement_getChannelByChannelId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_CHANNELS_SELECT_BY_CHANNEL_ID);
        // Table: Extra Files
        // Table: Media Files
        // Table: Playlists
        // Table: Playlist Videos
        // Table: Uploaders
        preparedStatement_getAllUploaders = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_UPLOADERS_SELECT_ALL);
        preparedStatement_getUploaderByUploaderId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_UPLOADERS_SELECT_BY_UPLOADER_ID);
        // Table: Video Queue
        // Table: Videos
        preparedStatement_getAllVideos = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEOS_SELECT_ALL);
        preparedStatement_getVideoByVideoId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEOS_SELECT_BY_VIDEO_ID);
        preparedStatement_getVideosByChannelId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEOS_SELECT_ALL_BY_CHANNEL_ID);
        //
        // // Inserts / Adds
        // Table: Channels
        preparedStatement_addChannel = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_CHANNELS_INSERT);
        // Table: Extra Files
        // Table: Media Files
        // Table: Playlists
        // Table: Playlist Videos
        // Table: Uploaders
        preparedStatement_addUploader = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_UPLOADERS_INSERT);
        // Table: Video Queue
        // Table: Videos
        preparedStatement_addVideo = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEOS_INSERT);
        //
        // // Updates / Sets
        // Table: Channels
        preparedStatement_setChannelByChannelId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_CHANNELS_UPDATE_BY_CHANNEL_ID);
        // Table: Extra Files
        // Table: Media Files
        // Table: Playlists
        // Table: Playlist Videos
        // Table: Uploaders
        preparedStatement_setUploaderByUploaderId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_UPLOADERS_UPDATE_BY_UPLOADER_ID);
        // Table: Video Queue
        // Table: Videos
        preparedStatement_setVideoByVideoId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEOS_UPDATE_BY_VIDEO_ID);
        preparedStatement_setVideosByChannelId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEOS_UPDATE_ALL_BY_CHANNEL_ID);
        //
        // // Deletes / Removes
        // Table: Channels
        preparedStatement_removeAllChannels = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_CHANNELS_DELETE_ALL);
        preparedStatement_removeChannelByChannelId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_CHANNELS_DELETE_BY_CHANNEL_ID);
        // Table: Extra Files
        // Table: Media Files
        // Table: Playlists
        // Table: Playlist Videos
        // Table: Uploaders
        preparedStatement_removeAllUploaders = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_UPLOADERS_DELETE_ALL);
        preparedStatement_removeUploaderByUploaderId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_UPLOADERS_DELETE_BY_CHANNEL_ID);
        // Table: Video Queue
        // Table: Videos
        preparedStatement_removeAllVideos = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEOS_DELETE_ALL);
        preparedStatement_removeVideoByVideoId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEOS_DELETE_BY_VIDEO_ID);
        preparedStatement_removeVideosByChannelId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEOS_DELETE_ALL_BY_CHANNEL_ID);
        //
        // //
    }
    
    private PreparedStatement createPreparedStatement(String sql) {
        return createPreparedStatement(connector, sql);
    }
    
    @Override
    public YouTubeVideo getVideoByVideoId(String videoId) {
        return null; //TODO
    }
    
    @Override
    public List<YouTubeVideo> getVideosByPlaylistId(String playlistId) {
        return null; //TODO
    }
    
    @Override
    public List<String> getVideoIdsByPlaylistId(String playlistId) {
        return null; //TODO
    }
    
    @Override
    public YouTubePlaylist getPlaylistByPlaylistId(String playlistId) {
        return null; //TODO
    }
    
    @Override
    public List<YouTubePlaylist> getPlaylistsByVideoId(String videoId) {
        return null; //TODO
    }
    
    @Override
    public List<String> getPlaylistIdsByVideoId(String videoId) {
        return null; //TODO
    }
    
    @Override
    public boolean playlistContainsVideo(String playlistId, String videoId) {
        return false; //TODO
    }
    
    @Override
    public List<MediaFile> getMediaFilesByVideoId(String videoId) {
        return null; //TODO
    }
    
    @Override
    public List<ExtraFile> getExtraFilesByVideoId(String videoId) {
        return null; //TODO
    }
    
    @Override
    public boolean setVideoByVideoId(YouTubeVideo video, String videoId) {
        return false; //TODO
    }
    
    @Override
    public boolean setVideosByPlaylistId(List<YouTubeVideo> videos, String playlistId) {
        return false; //TODO
    }
    
    @Override
    public boolean setPlaylistByPlaylistId(YouTubePlaylist playlist, String playlistId) {
        return false; //TODO
    }
    
    @Override
    public boolean setPlaylistsByVideoId(List<YouTubePlaylist> playlists, String videoId) {
        return false; //TODO
    }
    
    @Override
    public boolean setMediaFilesByVideoId(List<MediaFile> mediaFiles, String videoId) {
        return false; //TODO
    }
    
    @Override
    public boolean setExtraFilesByVideoId(List<ExtraFile> extraFiles, String videoId) {
        return false; //TODO
    }
    
    @Override
    public String toString() {
        return "SQLDatabase{" + "connector=" + connector + '}';
    }
    
    public static PreparedStatement createPreparedStatement(AbstractConnector abstractConnector, String sql) {
        try {
            return abstractConnector.prepareStatement(sql);
        } catch (Exception ex) {
            return null;
        }
    }
    
}
