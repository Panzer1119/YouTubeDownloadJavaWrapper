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
import de.codemakers.base.logger.Logger;
import de.codemakers.base.util.tough.ToughFunction;
import de.codemakers.base.util.tough.ToughSupplier;
import de.codemakers.download.database.entities.impl.ExtraFile;
import de.codemakers.download.database.entities.impl.MediaFile;
import de.codemakers.download.database.entities.impl.YouTubePlaylist;
import de.codemakers.download.database.entities.impl.YouTubeVideo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class YouTubeDatabase<C extends AbstractConnector> extends AbstractDatabase<YouTubeDatabase, MediaFile, ExtraFile, YouTubeVideo, YouTubePlaylist, C> {
    
    // // Selects / Gets
    // Table: Channels
    private transient PreparedStatement preparedStatement_getAllChannels = null;
    private transient PreparedStatement preparedStatement_getChannelByChannelId = null;
    // Table: Extra Files
    private transient PreparedStatement preparedStatement_getAllExtraFiles = null;
    private transient PreparedStatement preparedStatement_getExtraFilesByVideoId = null;
    private transient PreparedStatement preparedStatement_getExtraFilesByFileType = null;
    private transient PreparedStatement preparedStatement_getExtraFileByVideoIdAndFile = null;
    // Table: Media Files
    private transient PreparedStatement preparedStatement_getAllMediaFiles = null;
    private transient PreparedStatement preparedStatement_getMediaFilesByVideoId = null;
    private transient PreparedStatement preparedStatement_getMediaFilesByFileType = null;
    private transient PreparedStatement preparedStatement_getMediaFileByVideoIdAndFile = null;
    // Table: Playlists
    private transient PreparedStatement preparedStatement_getAllPlaylists = null;
    private transient PreparedStatement preparedStatement_getPlaylistByPlaylistId = null;
    private transient PreparedStatement preparedStatement_getPlaylistsByUploaderId = null;
    // Table: Playlist Videos
    private transient PreparedStatement preparedStatement_getAllPlaylistVideos = null;
    private transient PreparedStatement preparedStatement_getPlaylistVideosByVideoId = null;
    private transient PreparedStatement preparedStatement_getPlaylistVideosByPlaylistId = null;
    private transient PreparedStatement preparedStatement_getPlaylistVideoByPlaylistIdAndVideoId = null;
    // Table: Uploaders
    private transient PreparedStatement preparedStatement_getAllUploaders = null;
    private transient PreparedStatement preparedStatement_getUploaderByUploaderId = null;
    // Table: Video Queue
    private transient PreparedStatement preparedStatement_getAllQueuedVideos = null;
    private transient PreparedStatement preparedStatement_getQueuedVideoById = null;
    private transient PreparedStatement preparedStatement_getQueuedVideosByVideoId = null;
    private transient PreparedStatement preparedStatement_getNextQueuedVideos = null;
    private transient PreparedStatement preparedStatement_getNextQueuedVideo = null;
    // Table: Videos
    private transient PreparedStatement preparedStatement_getAllVideos = null;
    private transient PreparedStatement preparedStatement_getVideoByVideoId = null;
    private transient PreparedStatement preparedStatement_getVideosByChannelId = null;
    private transient PreparedStatement preparedStatement_getVideosByUploaderId = null;
    //
    // // Inserts / Adds
    // Table: Channels
    private transient PreparedStatement preparedStatement_addChannel = null;
    // Table: Extra Files
    private transient PreparedStatement preparedStatement_addExtraFile = null;
    // Table: Media Files
    private transient PreparedStatement preparedStatement_addMediaFile = null;
    // Table: Playlists
    private transient PreparedStatement preparedStatement_addPlaylist = null;
    // Table: Playlist Videos
    private transient PreparedStatement preparedStatement_addPlaylistVideo = null;
    // Table: Uploaders
    private transient PreparedStatement preparedStatement_addUploader = null;
    // Table: Video Queue
    private transient PreparedStatement preparedStatement_addQueuedVideo = null;
    // Table: Videos
    private transient PreparedStatement preparedStatement_addVideo = null;
    //
    // // Updates / Sets
    // Table: Channels
    private transient PreparedStatement preparedStatement_setChannelByChannelId = null;
    // Table: Extra Files
    private transient PreparedStatement preparedStatement_setExtraFileByVideoIdAndFile = null;
    // Table: Media Files
    private transient PreparedStatement preparedStatement_setMediaFileByVideoIdAndFile = null;
    // Table: Playlists
    private transient PreparedStatement preparedStatement_setPlaylistByPlaylistId = null;
    // Table: Playlist Videos
    private transient PreparedStatement preparedStatement_setPlaylistVideoByPlaylistIdAndVideoId = null;
    // Table: Uploaders
    private transient PreparedStatement preparedStatement_setUploaderByUploaderId = null;
    // Table: Video Queue
    private transient PreparedStatement preparedStatement_setQueuedVideoById = null;
    // Table: Videos
    private transient PreparedStatement preparedStatement_setVideoByVideoId = null;
    //
    // // Deletes / Removes
    // Table: Channels
    private transient PreparedStatement preparedStatement_removeAllChannels = null;
    private transient PreparedStatement preparedStatement_removeChannelByChannelId = null;
    // Table: Extra Files
    private transient PreparedStatement preparedStatement_removeAllExtraFiles = null;
    private transient PreparedStatement preparedStatement_removeExtraFilesByVideoId = null;
    private transient PreparedStatement preparedStatement_removeExtraFileByVideoIdAndFile = null;
    // Table: Media Files
    private transient PreparedStatement preparedStatement_removeAllMediaFiles = null;
    private transient PreparedStatement preparedStatement_removeMediaFilesByVideoId = null;
    private transient PreparedStatement preparedStatement_removeMediaFileByVideoIdAndFile = null;
    // Table: Playlists
    private transient PreparedStatement preparedStatement_removeAllPlaylists = null;
    private transient PreparedStatement preparedStatement_removePlaylistByPlaylistId = null;
    private transient PreparedStatement preparedStatement_removePlaylistsByUploaderId = null;
    // Table: Playlist Videos
    private transient PreparedStatement preparedStatement_removeAllPlaylistVideos = null;
    private transient PreparedStatement preparedStatement_removePlaylistVideosByPlaylistId = null;
    private transient PreparedStatement preparedStatement_removePlaylistVideosByVideoId = null;
    private transient PreparedStatement preparedStatement_removePlaylistVideoByPlaylistIdAndVideoId = null;
    // Table: Uploaders
    private transient PreparedStatement preparedStatement_removeAllUploaders = null;
    private transient PreparedStatement preparedStatement_removeUploaderByUploaderId = null;
    // Table: Video Queue
    private transient PreparedStatement preparedStatement_removeAllQueuedVideos = null;
    private transient PreparedStatement preparedStatement_removeQueuedVideoById = null;
    private transient PreparedStatement preparedStatement_removeQueuedVideosByVideoId = null;
    // Table: Videos
    private transient PreparedStatement preparedStatement_removeAllVideos = null;
    private transient PreparedStatement preparedStatement_removeVideoByVideoId = null;
    private transient PreparedStatement preparedStatement_removeVideosByChannelId = null;
    private transient PreparedStatement preparedStatement_removeVideosByUploaderId = null;
    //
    // //
    
    public YouTubeDatabase(C connector) {
        super(connector);
    }
    
    @Override
    public boolean isConnected() {
        return false; //TODO
    }
    
    @Override
    public boolean start(String username, byte[] password) {
        return false; //TODO
    }
    
    @Override
    public boolean stop() {
        return false; //TODO
    }
    
    private void createStatements() {
        // // Selects / Gets
        // Table: Channels
        preparedStatement_getAllChannels = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_CHANNELS_SELECT_ALL);
        preparedStatement_getChannelByChannelId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_CHANNELS_SELECT_BY_CHANNEL_ID);
        // Table: Extra Files
        preparedStatement_getAllExtraFiles = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_EXTRA_FILES_SELECT_ALL);
        preparedStatement_getExtraFilesByVideoId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_EXTRA_FILES_SELECT_ALL_BY_VIDEO_ID);
        preparedStatement_getExtraFilesByFileType = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_EXTRA_FILES_SELECT_ALL_BY_FILE_TYPE);
        preparedStatement_getExtraFileByVideoIdAndFile = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_EXTRA_FILES_SELECT_BY_VIDEO_ID_AND_FILE);
        // Table: Media Files
        preparedStatement_getAllMediaFiles = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_MEDIA_FILES_SELECT_ALL);
        preparedStatement_getMediaFilesByVideoId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_MEDIA_FILES_SELECT_ALL_BY_VIDEO_ID);
        preparedStatement_getMediaFilesByFileType = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_MEDIA_FILES_SELECT_ALL_BY_FILE_TYPE);
        preparedStatement_getMediaFileByVideoIdAndFile = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_MEDIA_FILES_SELECT_BY_VIDEO_ID_AND_FILE);
        // Table: Playlists
        preparedStatement_getAllPlaylists = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_PLAYLISTS_SELECT_ALL);
        preparedStatement_getPlaylistByPlaylistId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_PLAYLISTS_SELECT_BY_PLAYLIST_ID);
        preparedStatement_getPlaylistsByUploaderId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_PLAYLISTS_SELECT_ALL_UPLOADER_ID);
        // Table: Playlist Videos
        preparedStatement_getAllPlaylistVideos = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_PLAYLIST_VIDEOS_SELECT_ALL);
        preparedStatement_getPlaylistVideosByVideoId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_PLAYLIST_VIDEOS_SELECT_ALL_BY_VIDEO_ID);
        preparedStatement_getPlaylistVideosByPlaylistId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_PLAYLIST_VIDEOS_SELECT_ALL_BY_PLAYLIST_ID);
        preparedStatement_getPlaylistVideoByPlaylistIdAndVideoId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_PLAYLIST_VIDEOS_SELECT_BY_PLAYLIST_ID_AND_VIDEO_ID);
        // Table: Uploaders
        preparedStatement_getAllUploaders = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_UPLOADERS_SELECT_ALL);
        preparedStatement_getUploaderByUploaderId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_UPLOADERS_SELECT_BY_UPLOADER_ID);
        // Table: Video Queue
        preparedStatement_getAllQueuedVideos = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEO_QUEUE_SELECT_ALL);
        preparedStatement_getQueuedVideoById = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEO_QUEUE_SELECT_BY_ID);
        preparedStatement_getQueuedVideosByVideoId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEO_QUEUE_SELECT_ALL_BY_VIDEO_ID);
        preparedStatement_getNextQueuedVideos = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEO_QUEUE_SELECT_ALL_NEXT);
        preparedStatement_getNextQueuedVideo = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEO_QUEUE_SELECT_NEXT);
        // Table: Videos
        preparedStatement_getAllVideos = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEOS_SELECT_ALL);
        preparedStatement_getVideoByVideoId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEOS_SELECT_BY_VIDEO_ID);
        preparedStatement_getVideosByChannelId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEOS_SELECT_ALL_BY_CHANNEL_ID);
        preparedStatement_getVideosByUploaderId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEOS_SELECT_ALL_BY_UPLOADER_ID);
        //
        // // Inserts / Adds
        // Table: Channels
        preparedStatement_addChannel = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_CHANNELS_INSERT);
        // Table: Extra Files
        preparedStatement_addExtraFile = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_EXTRA_FILES_INSERT);
        // Table: Media Files
        preparedStatement_addMediaFile = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_MEDIA_FILES_INSERT);
        // Table: Playlists
        preparedStatement_addPlaylist = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_PLAYLISTS_INSERT);
        // Table: Playlist Videos
        preparedStatement_addPlaylistVideo = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_PLAYLIST_VIDEOS_INSERT);
        // Table: Uploaders
        preparedStatement_addUploader = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_UPLOADERS_INSERT);
        // Table: Video Queue
        preparedStatement_addQueuedVideo = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEO_QUEUE_INSERT);
        // Table: Videos
        preparedStatement_addVideo = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEOS_INSERT);
        //
        // // Updates / Sets
        // Table: Channels
        preparedStatement_setChannelByChannelId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_CHANNELS_UPDATE_BY_CHANNEL_ID);
        // Table: Extra Files
        preparedStatement_setExtraFileByVideoIdAndFile = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_EXTRA_FILES_UPDATE_BY_VIDEO_ID_AND_FILE);
        // Table: Media Files
        preparedStatement_setMediaFileByVideoIdAndFile = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_MEDIA_FILES_UPDATE_BY_VIDEO_ID_AND_FILE);
        // Table: Playlists
        preparedStatement_setPlaylistByPlaylistId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_PLAYLISTS_UPDATE_BY_PLAYLIST_ID);
        // Table: Playlist Videos
        preparedStatement_setPlaylistVideoByPlaylistIdAndVideoId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_PLAYLIST_VIDEOS_UPDATE_BY_PLAYLIST_ID_AND_VIDEO_ID);
        // Table: Uploaders
        preparedStatement_setUploaderByUploaderId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_UPLOADERS_UPDATE_BY_UPLOADER_ID);
        // Table: Video Queue
        preparedStatement_setQueuedVideoById = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEO_QUEUE_UPDATE_BY_ID);
        // Table: Videos
        preparedStatement_setVideoByVideoId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEOS_UPDATE_BY_VIDEO_ID);
        //
        // // Deletes / Removes
        // Table: Channels
        preparedStatement_removeAllChannels = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_CHANNELS_DELETE_ALL);
        preparedStatement_removeChannelByChannelId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_CHANNELS_DELETE_BY_CHANNEL_ID);
        // Table: Extra Files
        preparedStatement_removeAllExtraFiles = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_EXTRA_FILES_DELETE_ALL);
        preparedStatement_removeExtraFilesByVideoId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_EXTRA_FILES_DELETE_ALL_BY_VIDEO_ID);
        preparedStatement_removeExtraFileByVideoIdAndFile = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_EXTRA_FILES_DELETE_BY_VIDEO_ID_AND_FILE);
        // Table: Media Files
        preparedStatement_removeAllMediaFiles = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_MEDIA_FILES_DELETE_ALL);
        preparedStatement_removeMediaFilesByVideoId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_MEDIA_FILES_DELETE_ALL_BY_VIDEO_ID);
        preparedStatement_removeMediaFileByVideoIdAndFile = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_MEDIA_FILES_DELETE_BY_VIDEO_ID_AND_FILE);
        // Table: Playlists
        preparedStatement_removeAllPlaylists = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_PLAYLISTS_DELETE_ALL);
        preparedStatement_removePlaylistByPlaylistId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_PLAYLISTS_DELETE_BY_PLAYLIST_ID);
        preparedStatement_removePlaylistsByUploaderId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_PLAYLISTS_DELETE_ALL_BY_UPLOADER_ID);
        // Table: Playlist Videos
        preparedStatement_removeAllPlaylistVideos = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_PLAYLIST_VIDEOS_DELETE_ALL);
        preparedStatement_removePlaylistVideosByPlaylistId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_PLAYLIST_VIDEOS_DELETE_ALL_BY_PLAYLIST_ID);
        preparedStatement_removePlaylistVideosByVideoId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_PLAYLIST_VIDEOS_DELETE_ALL_BY_VIDEO_ID);
        preparedStatement_removePlaylistVideoByPlaylistIdAndVideoId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_PLAYLIST_VIDEOS_DELETE_BY_PLAYLIST_ID_AND_VIDEO_ID);
        // Table: Uploaders
        preparedStatement_removeAllUploaders = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_UPLOADERS_DELETE_ALL);
        preparedStatement_removeUploaderByUploaderId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_UPLOADERS_DELETE_BY_CHANNEL_ID);
        // Table: Video Queue
        preparedStatement_removeAllQueuedVideos = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEO_QUEUE_DELETE_ALL);
        preparedStatement_removeQueuedVideoById = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEO_QUEUE_DELETE_BY_ID);
        preparedStatement_removeQueuedVideosByVideoId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEO_QUEUE_DELETE_ALL_BY_VIDEO_ID);
        // Table: Videos
        preparedStatement_removeAllVideos = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEOS_DELETE_ALL);
        preparedStatement_removeVideoByVideoId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEOS_DELETE_BY_VIDEO_ID);
        preparedStatement_removeVideosByChannelId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEOS_DELETE_ALL_BY_CHANNEL_ID);
        preparedStatement_removeVideosByUploaderId = createPreparedStatement(YouTubeDatabaseConstants.QUERY_TABLE_VIDEOS_DELETE_ALL_BY_UPLOADER_ID);
        //
        // //
    }
    
    private void closeStatements() {
        //TODO
        //FIXME
        //IMPORTANT
    }
    
    private PreparedStatement createPreparedStatement(String sql) {
        return createPreparedStatement(connector, sql);
    }
    
    @Override
    public YouTubeVideo getVideoByVideoId(String videoId) {
        synchronized (preparedStatement_getVideoByVideoId) {
            if (!setPreparedStatement(preparedStatement_getVideoByVideoId, videoId)) {
                return null;
            }
            return useResultSetAndClose(preparedStatement_getVideoByVideoId::executeQuery, YouTubeDatabase::resultSetToYouTubeVideo);
        }
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
    public int getIndexInPlaylist(String playlistId, String videoId) {
        return -1; //TODO
    }
    
    @Override
    public boolean playlistContainsVideo(String playlistId, String videoId) {
        return false; //TODO
    }
    
    @Override
    public MediaFile getMediaFileByVideoIdAndFile(String videoId, String file) {
        synchronized (preparedStatement_getMediaFileByVideoIdAndFile) {
            if (!setPreparedStatement(preparedStatement_getMediaFileByVideoIdAndFile, videoId)) {
                return null; //TODO Hmm Should this be an empty list?
            }
            return useResultSetAndClose(preparedStatement_getMediaFileByVideoIdAndFile::executeQuery, YouTubeDatabase::resultSetToMediaFile);
        }
    }
    
    @Override
    public List<MediaFile> getMediaFilesByVideoId(String videoId) {
        synchronized (preparedStatement_getMediaFilesByVideoId) {
            if (!setPreparedStatement(preparedStatement_getMediaFilesByVideoId, videoId)) {
                return null; //TODO Hmm Should this be an empty list?
            }
            return useResultSetAndClose(preparedStatement_getMediaFilesByVideoId::executeQuery, YouTubeDatabase::resultSetToMediaFiles);
        }
    }
    
    @Override
    public ExtraFile getExtraFileByVideoIdAndFile(String videoId, String file) {
        synchronized (preparedStatement_getExtraFileByVideoIdAndFile) {
            if (!setPreparedStatement(preparedStatement_getExtraFileByVideoIdAndFile, videoId)) {
                return null; //TODO Hmm Should this be an empty list?
            }
            return useResultSetAndClose(preparedStatement_getExtraFileByVideoIdAndFile::executeQuery, YouTubeDatabase::resultSetToExtraFile);
        }
    }
    
    @Override
    public List<ExtraFile> getExtraFilesByVideoId(String videoId) {
        synchronized (preparedStatement_getExtraFilesByVideoId) {
            if (!setPreparedStatement(preparedStatement_getExtraFilesByVideoId, videoId)) {
                return null; //TODO Hmm Should this be an empty list?
            }
            return useResultSetAndClose(preparedStatement_getExtraFilesByVideoId::executeQuery, YouTubeDatabase::resultSetToExtraFiles);
        }
    }
    
    @Override
    public boolean setVideoByVideoId(YouTubeVideo video, String videoId) {
        if (video == null) {
            return false;
        }
        synchronized (preparedStatement_setVideoByVideoId) {
            if (!setPreparedStatement(preparedStatement_setVideoByVideoId, video.getVideoId(), video.getChannelId(), video.getUploaderId(), video.getTitle(), video.getAltTitle(), video.getDurationMillis(), video.getUploadDateAsString(), videoId)) {
                return false;
            }
            return Standard.silentError(() -> preparedStatement_setVideoByVideoId.executeUpdate()) > 0;
        }
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
    public boolean setMediaFileByVideoIdAndFile(MediaFile mediaFile, String videoId, String file) {
        return false; //TODO
    }
    
    @Override
    public boolean setMediaFilesByVideoId(List<MediaFile> mediaFiles, String videoId) {
        return false; //TODO
    }
    
    @Override
    public boolean setExtraFileByVideoIdAndFile(ExtraFile extraFile, String videoId, String file) {
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
    
    public static boolean setPreparedStatement(PreparedStatement preparedStatement, Object... arguments) {
        try {
            int offset = 0;
            for (int i = 0; i < arguments.length - offset; i++) {
                final Object object = arguments[i + offset];
                final int index = i + 1 + offset;
                if (object == null) {
                    offset++;
                    preparedStatement.setNull(index, (Integer) arguments[i + offset + 1]);
                } else if (object instanceof String) {
                    preparedStatement.setString(index, (String) object);
                } else if (object instanceof Integer) {
                    preparedStatement.setInt(index, (Integer) object);
                } else if (object instanceof Long) {
                    preparedStatement.setLong(index, (Long) object);
                } else if (object instanceof Boolean) {
                    preparedStatement.setBoolean(index, (Boolean) object);
                } else {
                    throw new IllegalArgumentException(String.format("The Class \"%s\" is not yet supported by \"setPreparedStatement\"!", object.getClass().getName()));
                }
            }
            return true;
        } catch (Exception ex) {
            Logger.handleError(ex);
            return false;
        }
    }
    
    public static <R> R useResultSetAndClose(ToughSupplier<ResultSet> toughSupplier, ToughFunction<ResultSet, R> toughFunction) {
        if (toughSupplier == null || toughFunction == null) {
            return null;
        }
        final ResultSet resultSet = toughSupplier.getWithoutException();
        if (resultSet == null || !Standard.silentError(resultSet::next)) {
            return null;
        }
        final R r = toughFunction.applyWithoutException(resultSet);
        Standard.silentError(resultSet::close);
        return r;
    }
    
    public static YouTubeVideo resultSetToYouTubeVideo(ResultSet resultSet) {
        if (resultSet == null) {
            return null;
        }
        return Standard.silentError(() -> new YouTubeVideo(resultSet.getString(YouTubeDatabaseConstants.IDENTIFIER_TABLE_VIDEOS_COLUMN_ID), resultSet.getString(YouTubeDatabaseConstants.IDENTIFIER_TABLE_VIDEOS_COLUMN_CHANNEL_ID), resultSet.getString(YouTubeDatabaseConstants.IDENTIFIER_TABLE_VIDEOS_COLUMN_UPLOADER_ID), resultSet.getString(YouTubeDatabaseConstants.IDENTIFIER_TABLE_VIDEOS_COLUMN_TITLE), resultSet.getString(YouTubeDatabaseConstants.IDENTIFIER_TABLE_VIDEOS_COLUMN_ALT_TITLE), resultSet.getLong(YouTubeDatabaseConstants.IDENTIFIER_TABLE_VIDEOS_COLUMN_DURATION), resultSet.getString(YouTubeDatabaseConstants.IDENTIFIER_TABLE_VIDEOS_COLUMN_UPLOAD_DATE)));
    }
    
    public static List<YouTubeVideo> resultSetToYouTubeVideos(ResultSet resultSet) {
        if (resultSet == null) {
            return null; //TODO Hmm Should this be an empty list?
        }
        final List<YouTubeVideo> youTubeVideos = new ArrayList<>();
        do {
            final YouTubeVideo youTubeVideo = resultSetToYouTubeVideo(resultSet);
            if (youTubeVideo != null) {
                youTubeVideos.add(youTubeVideo);
            }
        } while (Standard.silentError(resultSet::next));
        return youTubeVideos;
    }
    
    public static YouTubePlaylist resultSetToYouTubePlaylist(ResultSet resultSet) {
        if (resultSet == null) {
            return null;
        }
        return Standard.silentError(() -> new YouTubePlaylist(resultSet.getString(YouTubeDatabaseConstants.IDENTIFIER_TABLE_PLAYLISTS_COLUMN_ID), resultSet.getString(YouTubeDatabaseConstants.IDENTIFIER_TABLE_PLAYLISTS_COLUMN_TITLE), resultSet.getString(YouTubeDatabaseConstants.IDENTIFIER_TABLE_PLAYLISTS_COLUMN_PLAYLIST), resultSet.getString(YouTubeDatabaseConstants.IDENTIFIER_TABLE_PLAYLISTS_COLUMN_UPLOADER_ID)));
    }
    
    public static List<YouTubePlaylist> resultSetToYouTubePlaylists(ResultSet resultSet) {
        if (resultSet == null) {
            return null; //TODO Hmm Should this be an empty list?
        }
        final List<YouTubePlaylist> youTubePlaylists = new ArrayList<>();
        do {
            final YouTubePlaylist youTubePlaylist = resultSetToYouTubePlaylist(resultSet);
            if (youTubePlaylist != null) {
                youTubePlaylists.add(youTubePlaylist);
            }
        } while (Standard.silentError(resultSet::next));
        return youTubePlaylists;
    }
    
    public static MediaFile resultSetToMediaFile(ResultSet resultSet) {
        if (resultSet == null) {
            return null;
        }
        return Standard.silentError(() -> new MediaFile(resultSet.getString(YouTubeDatabaseConstants.IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_VIDEO_ID), resultSet.getString(YouTubeDatabaseConstants.IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_FILE), resultSet.getString(YouTubeDatabaseConstants.IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_FILE_TYPE), resultSet.getString(YouTubeDatabaseConstants.IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_FORMAT), resultSet.getString(YouTubeDatabaseConstants.IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_VCODEC), resultSet.getString(YouTubeDatabaseConstants.IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_ACODEC), resultSet.getInt(YouTubeDatabaseConstants.IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_WIDTH), resultSet.getInt(YouTubeDatabaseConstants.IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_HEIGHT), resultSet.getInt(YouTubeDatabaseConstants.IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_FPS), resultSet.getInt(YouTubeDatabaseConstants.IDENTIFIER_TABLE_MEDIA_FILES_COLUMN_ASR)));
    }
    
    public static List<MediaFile> resultSetToMediaFiles(ResultSet resultSet) {
        if (resultSet == null) {
            return null; //TODO Hmm Should this be an empty list?
        }
        final List<MediaFile> mediaFiles = new ArrayList<>();
        do {
            final MediaFile mediaFile = resultSetToMediaFile(resultSet);
            if (mediaFile != null) {
                mediaFiles.add(mediaFile);
            }
        } while (Standard.silentError(resultSet::next));
        return mediaFiles;
    }
    
    public static ExtraFile resultSetToExtraFile(ResultSet resultSet) {
        if (resultSet == null) {
            return null;
        }
        return Standard.silentError(() -> new ExtraFile(resultSet.getString(YouTubeDatabaseConstants.IDENTIFIER_TABLE_EXTRA_FILES_COLUMN_VIDEO_ID), resultSet.getString(YouTubeDatabaseConstants.IDENTIFIER_TABLE_EXTRA_FILES_COLUMN_FILE), resultSet.getString(YouTubeDatabaseConstants.IDENTIFIER_TABLE_EXTRA_FILES_COLUMN_FILE_TYPE)));
    }
    
    public static List<ExtraFile> resultSetToExtraFiles(ResultSet resultSet) {
        if (resultSet == null) {
            return null; //TODO Hmm Should this be an empty list?
        }
        final List<ExtraFile> extraFiles = new ArrayList<>();
        do {
            final ExtraFile extraFile = resultSetToExtraFile(resultSet);
            if (extraFile != null) {
                extraFiles.add(extraFile);
            }
        } while (Standard.silentError(resultSet::next));
        return extraFiles;
    }
    
    public static void createTables(YouTubeDatabase youTubeDatabase) {
        //TODO Use the "database_create_tables.sql" to create (if not existing) tables...
    }
    
}
