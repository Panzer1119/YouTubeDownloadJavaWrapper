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
    //
    // // Deletes / Removes
    // Table: Channels
    private transient PreparedStatement preparedStatement_removeChannelByChannelId = null;
    // Table: Extra Files
    // Table: Media Files
    // Table: Playlists
    // Table: Playlist Videos
    // Table: Uploaders
    private transient PreparedStatement preparedStatement_removeUploaderByUploaderId = null;
    // Table: Video Queue
    // Table: Videos
    //
    // //
    
    public YouTubeDatabase(C connector) {
        super(connector);
    }
    
    private void createStatements() {
        // // Selects / Gets
        // Table: Channels
        // Table: Extra Files
        // Table: Media Files
        // Table: Playlists
        // Table: Playlist Videos
        // Table: Uploaders
        // Table: Video Queue
        // Table: Videos
        //
        // // Inserts / Adds
        // Table: Channels
        // Table: Extra Files
        // Table: Media Files
        // Table: Playlists
        // Table: Playlist Videos
        // Table: Uploaders
        // Table: Video Queue
        // Table: Videos
        //
        // // Updates / Sets
        // Table: Channels
        // Table: Extra Files
        // Table: Media Files
        // Table: Playlists
        // Table: Playlist Videos
        // Table: Uploaders
        // Table: Video Queue
        // Table: Videos
        //
        // // Deletes / Removes
        // Table: Channels
        // Table: Extra Files
        // Table: Media Files
        // Table: Playlists
        // Table: Playlist Videos
        // Table: Uploaders
        // Table: Video Queue
        // Table: Videos
        //
        // //
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
    public String toString() {
        return "SQLDatabase{" + "connector=" + connector + '}';
    }
    
}
