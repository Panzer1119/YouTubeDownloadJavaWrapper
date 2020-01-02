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

import de.codemakers.base.Standard;
import de.codemakers.download.database.entities.ExtraFile;
import de.codemakers.download.database.entities.MediaFile;
import de.codemakers.download.database.entities.Playlist;
import de.codemakers.download.database.entities.Video;
import de.codemakers.io.IOUtil;
import de.codemakers.io.file.AdvancedFile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {
    
    // // // SQL
    // // Tables
    public static final String TABLE_VIDEOS = "videos";
    public static final String TABLE_PLAYLISTS = "playlists";
    public static final String TABLE_PLAYLIST_VIDEOS = "playlistVideos";
    public static final String TABLE_MEDIA_FILES = "mediaFiles";
    public static final String TABLE_EXTRA_FILES = "extraFiles";
    // // Columns
    // Table: videos
    public static final String TABLE_VIDEOS_COLUMN_ID = "id";
    public static final String TABLE_VIDEOS_COLUMN_UPLOADER = "uploader";
    public static final String TABLE_VIDEOS_COLUMN_UPLOADER_ID = "uploaderId";
    public static final String TABLE_VIDEOS_COLUMN_TITLE = "title";
    public static final String TABLE_VIDEOS_COLUMN_ALT_TITLE = "altTitle";
    public static final String TABLE_VIDEOS_COLUMN_DURATION = "duration";
    public static final String TABLE_VIDEOS_COLUMN_UPLOAD_DATE = "uploadDate";
    // Table: playlists
    public static final String TABLE_PLAYLISTS_COLUMN_ID = "id";
    public static final String TABLE_PLAYLISTS_COLUMN_TITLE = "title";
    public static final String TABLE_PLAYLISTS_COLUMN_PLAYLIST = "playlist";
    public static final String TABLE_PLAYLISTS_COLUMN_UPLOADER = "uploader";
    public static final String TABLE_PLAYLISTS_COLUMN_UPLOADER_ID = "uploaderId";
    // Table: playlistVideos
    public static final String TABLE_PLAYLIST_VIDEOS_COLUMN_PLAYLIST_ID = "playlistId";
    public static final String TABLE_PLAYLIST_VIDEOS_COLUMN_VIDEO_ID = "videoId";
    public static final String TABLE_PLAYLIST_VIDEOS_COLUMN_INDEX = "index";
    // Table: mediaFiles
    public static final String TABLE_MEDIA_FILES_COLUMN_VIDEO_ID = "videoId";
    public static final String TABLE_MEDIA_FILES_COLUMN_FILE = "file";
    public static final String TABLE_MEDIA_FILES_COLUMN_FILE_TYPE = "fileType";
    public static final String TABLE_MEDIA_FILES_COLUMN_FORMAT = "format";
    public static final String TABLE_MEDIA_FILES_COLUMN_VCODEC = "vcodec";
    public static final String TABLE_MEDIA_FILES_COLUMN_ACODEC = "acodec";
    public static final String TABLE_MEDIA_FILES_COLUMN_WIDTH = "width";
    public static final String TABLE_MEDIA_FILES_COLUMN_HEIGHT = "height";
    public static final String TABLE_MEDIA_FILES_COLUMN_FPS = "fps";
    public static final String TABLE_MEDIA_FILES_COLUMN_ASR = "asr";
    // Table: extraFiles
    public static final String TABLE_EXTRA_FILES_COLUMN_VIDEO_ID = "videoId";
    public static final String TABLE_EXTRA_FILES_COLUMN_FILE = "file";
    public static final String TABLE_EXTRA_FILES_COLUMN_FILE_TYPE = "fileType";
    // // Queries
    // Table: videos
    public static final String TABLE_VIDEOS_QUERY_GET_ALL = String.format("SELECT * FROM %s;", TABLE_VIDEOS);
    public static final String TABLE_VIDEOS_QUERY_GET_BY_ID = String.format("SELECT * FROM %s WHERE %s = ?;", TABLE_VIDEOS, TABLE_VIDEOS_COLUMN_ID);
    // Table: playlists
    public static final String TABLE_PLAYLISTS_QUERY_GET_ALL = String.format("SELECT * FROM %s;", TABLE_PLAYLISTS);
    public static final String TABLE_PLAYLISTS_QUERY_GET_BY_ID = String.format("SELECT * FROM %s WHERE %s = ?;", TABLE_PLAYLISTS, TABLE_PLAYLISTS_COLUMN_ID);
    // Table: playlistVideos
    public static final String TABLE_PLAYLIST_VIDEOS_QUERY_GET_ALL = String.format("SELECT * FROM %s;", TABLE_PLAYLIST_VIDEOS);
    public static final String TABLE_PLAYLIST_VIDEOS_QUERY_GET_ALL_BY_VIDEO_ID = String.format("SELECT * FROM %s WHERE %s = ?;", TABLE_PLAYLIST_VIDEOS, TABLE_PLAYLIST_VIDEOS_COLUMN_VIDEO_ID);
    public static final String TABLE_PLAYLIST_VIDEOS_QUERY_GET_ALL_BY_PLAYLIST_ID = String.format("SELECT * FROM %s WHERE %s = ?;", TABLE_PLAYLIST_VIDEOS, TABLE_PLAYLIST_VIDEOS_COLUMN_PLAYLIST_ID);
    public static final String TABLE_PLAYLIST_VIDEOS_QUERY_GET_BY_VIDEO_ID_AND_PLAYLIST_ID = String.format("SELECT * FROM %s WHERE %s = ? AND %s = ?;", TABLE_PLAYLIST_VIDEOS, TABLE_PLAYLIST_VIDEOS_COLUMN_VIDEO_ID, TABLE_PLAYLIST_VIDEOS_COLUMN_PLAYLIST_ID);
    // Table: mediaFiles
    public static final String TABLE_MEDIA_FILES_QUERY_GET_ALL = String.format("SELECT * FROM %s;", TABLE_MEDIA_FILES);
    public static final String TABLE_MEDIA_FILES_QUERY_GET_ALL_BY_VIDEO_ID = String.format("SELECT * FROM %s WHERE %s = ?;", TABLE_MEDIA_FILES, TABLE_MEDIA_FILES_COLUMN_VIDEO_ID);
    // Table: extraFiles
    public static final String TABLE_EXTRA_FILES_QUERY_GET_ALL = String.format("SELECT * FROM %s;", TABLE_EXTRA_FILES);
    public static final String TABLE_EXTRA_FILES_QUERY_GET_ALL_BY_VIDEO_ID = String.format("SELECT * FROM %s WHERE %s = ?;", TABLE_EXTRA_FILES, TABLE_EXTRA_FILES_COLUMN_VIDEO_ID);
    // // //
    
    private final Connector connector;
    // // Temp
    // Videos
    private transient PreparedStatement preparedStatement_getAllVideos = null;
    private transient PreparedStatement preparedStatement_getVideoById = null;
    // Playlists
    private transient PreparedStatement preparedStatement_getAllPlaylists = null;
    private transient PreparedStatement preparedStatement_getPlaylistByPlaylistId = null;
    // Playlists and Videos
    private transient PreparedStatement preparedStatement_getAllPlaylistVideos = null;
    private transient PreparedStatement preparedStatement_getVideosByPlaylistId = null;
    private transient PreparedStatement preparedStatement_getPlaylistsByVideoId = null;
    private transient PreparedStatement preparedStatement_getPlaylistVideoByVideoIdAndPlaylistId = null;
    // MediaFiles
    private transient PreparedStatement preparedStatement_getAllMediaFiles = null;
    private transient PreparedStatement preparedStatement_getMediaFilesByVideoId = null;
    // ExtraFiles
    private transient PreparedStatement preparedStatement_getAllExtraFiles = null;
    private transient PreparedStatement preparedStatement_getExtraFilesByVideoId = null;
    // //
    
    public Database(AdvancedFile databaseDirectory) {
        this(new Connector(databaseDirectory));
    }
    
    public Database(Connector connector) {
        this.connector = connector;
    }
    
    public Connector getConnector() {
        return connector;
    }
    
    public boolean isRunning() {
        return connector.isConnected();
    }
    
    public boolean start() {
        if (isRunning()) {
            return false;
        }
        if (!connector.createConnection()) {
            return false;
        }
        initStatements();
        return true;
    }
    
    private void initStatements() {
        // Videos
        Standard.silentError(() -> preparedStatement_getAllVideos = connector.prepareStatement(TABLE_VIDEOS_QUERY_GET_ALL));
        Standard.silentError(() -> preparedStatement_getVideoById = connector.prepareStatement(TABLE_VIDEOS_QUERY_GET_BY_ID));
        // Playlists
        Standard.silentError(() -> preparedStatement_getAllPlaylists = connector.prepareStatement(TABLE_PLAYLISTS_QUERY_GET_ALL));
        Standard.silentError(() -> preparedStatement_getPlaylistByPlaylistId = connector.prepareStatement(TABLE_PLAYLISTS_QUERY_GET_BY_ID));
        // Playlists and Videos
        Standard.silentError(() -> preparedStatement_getAllPlaylistVideos = connector.prepareStatement(TABLE_PLAYLIST_VIDEOS_QUERY_GET_ALL));
        Standard.silentError(() -> preparedStatement_getVideosByPlaylistId = connector.prepareStatement(TABLE_PLAYLIST_VIDEOS_QUERY_GET_ALL_BY_VIDEO_ID));
        Standard.silentError(() -> preparedStatement_getPlaylistsByVideoId = connector.prepareStatement(TABLE_PLAYLIST_VIDEOS_QUERY_GET_ALL_BY_PLAYLIST_ID));
        Standard.silentError(() -> preparedStatement_getPlaylistVideoByVideoIdAndPlaylistId = connector.prepareStatement(TABLE_PLAYLIST_VIDEOS_QUERY_GET_BY_VIDEO_ID_AND_PLAYLIST_ID));
        // MediaFiles
        Standard.silentError(() -> preparedStatement_getAllMediaFiles = connector.prepareStatement(TABLE_MEDIA_FILES_QUERY_GET_ALL));
        Standard.silentError(() -> preparedStatement_getMediaFilesByVideoId = connector.prepareStatement(TABLE_MEDIA_FILES_QUERY_GET_ALL_BY_VIDEO_ID));
        // ExtraFiles
        Standard.silentError(() -> preparedStatement_getAllExtraFiles = connector.prepareStatement(TABLE_EXTRA_FILES_QUERY_GET_ALL));
        Standard.silentError(() -> preparedStatement_getExtraFilesByVideoId = connector.prepareStatement(TABLE_EXTRA_FILES_QUERY_GET_ALL_BY_VIDEO_ID));
    }
    
    public boolean stop() {
        if (!isRunning()) {
            return false;
        }
        closeStatements();
        return connector.closeConnection();
    }
    
    private void closeStatements() {
        // Videos
        IOUtil.closeQuietly(preparedStatement_getAllVideos);
        IOUtil.closeQuietly(preparedStatement_getVideoById);
        // Playlists
        IOUtil.closeQuietly(preparedStatement_getAllPlaylists);
        IOUtil.closeQuietly(preparedStatement_getPlaylistByPlaylistId);
        // Playlists and Videos
        IOUtil.closeQuietly(preparedStatement_getAllPlaylistVideos);
        IOUtil.closeQuietly(preparedStatement_getVideosByPlaylistId);
        IOUtil.closeQuietly(preparedStatement_getPlaylistsByVideoId);
        IOUtil.closeQuietly(preparedStatement_getPlaylistVideoByVideoIdAndPlaylistId);
        // MediaFiles
        IOUtil.closeQuietly(preparedStatement_getAllMediaFiles);
        IOUtil.closeQuietly(preparedStatement_getMediaFilesByVideoId);
        // ExtraFiles
        IOUtil.closeQuietly(preparedStatement_getAllExtraFiles);
        IOUtil.closeQuietly(preparedStatement_getExtraFilesByVideoId);
    }
    
    public List<Video> getAllVideos() {
        if (!isRunning()) {
            return null;
        }
        try (final ResultSet resultSet = preparedStatement_getAllVideos.executeQuery()) {
            return videosFromResultSet(resultSet);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public List<Playlist> getAllPlaylists() {
        if (!isRunning()) {
            return null;
        }
        try (final ResultSet resultSet = preparedStatement_getAllPlaylists.executeQuery()) {
            return playlistsFromResultSet(resultSet);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public List<MediaFile> getMediaFilesForVideo(String videoId) {
        if (!isRunning()) {
            return null;
        }
        //TODO !
        return null;
    }
    
    public List<ExtraFile> getExtraFilesForVideo(String videoId) {
        if (!isRunning()) {
            return null;
        }
        //TODO !
        return null;
    }
    
    public List<Video> getVideosInPlaylist(String playlistId) {
        if (!isRunning()) {
            return null;
        }
        //TODO !!!
        return null;
    }
    
    public List<Playlist> getPlaylistsContainingVideo(String videoId) {
        if (!isRunning()) {
            return null;
        }
        //TODO !!!
        return null;
    }
    
    public boolean isVideoInPlaylist(String videoId) {
        if (!isRunning() || videoId == null || videoId.isEmpty()) {
            return false;
        }
        //TODO !!
        return false;
    }
    
    public int getIndexOfVideoInPlaylist(String videoId) {
        if (!isRunning() || videoId == null || videoId.isEmpty()) {
            return -1;
        }
        //TODO !!
        return -1;
    }
    
    public static List<Video> videosFromResultSet(ResultSet resultSet) throws SQLException {
        final List<Video> videos = new ArrayList<>();
        while (resultSet.next()) {
            videos.add(videoFromResultSet(resultSet));
        }
        return videos;
    }
    
    public static Video videoFromResultSet(ResultSet resultSet) throws SQLException {
        return new Video(resultSet.getString(TABLE_VIDEOS_COLUMN_ID), resultSet.getString(TABLE_VIDEOS_COLUMN_UPLOADER), resultSet.getString(TABLE_VIDEOS_COLUMN_UPLOADER_ID), resultSet.getString(TABLE_VIDEOS_COLUMN_TITLE), resultSet.getString(TABLE_VIDEOS_COLUMN_ALT_TITLE), resultSet.getLong(TABLE_VIDEOS_COLUMN_DURATION), resultSet.getLong(TABLE_VIDEOS_COLUMN_UPLOAD_DATE));
    }
    
    public static List<Playlist> playlistsFromResultSet(ResultSet resultSet) throws SQLException {
        final List<Playlist> playlists = new ArrayList<>();
        while (resultSet.next()) {
            playlists.add(playlistFromResultSet(resultSet));
        }
        return playlists;
    }
    
    public static Playlist playlistFromResultSet(ResultSet resultSet) throws SQLException {
        return new Playlist(resultSet.getString(TABLE_PLAYLISTS_COLUMN_ID), resultSet.getString(TABLE_PLAYLISTS_COLUMN_TITLE), resultSet.getString(TABLE_PLAYLISTS_COLUMN_PLAYLIST), resultSet.getString(TABLE_PLAYLISTS_COLUMN_UPLOADER), resultSet.getString(TABLE_PLAYLISTS_COLUMN_UPLOADER_ID));
    }
    
    @Override
    public String toString() {
        return "Database{" + "connector=" + connector + '}';
    }
    
    public static void createTables(Database database) {
        //TODO Use the "database_create_tables.sql" to create (if not existing) tables...
    }
    
}
