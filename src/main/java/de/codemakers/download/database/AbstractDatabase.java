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

import de.codemakers.download.database.entities.*;
import de.codemakers.download.database.entities.impl.ExtraFile;
import de.codemakers.download.database.entities.impl.MediaFile;

import java.util.List;

public abstract class AbstractDatabase<T extends AbstractDatabase, M extends AbstractFile, E extends AbstractFile, V extends AbstractVideo, P extends AbstractPlaylist, Q extends AbstractQueuedVideo, CH extends AbstractChannel, U extends AbstractUploader, R extends AbstractRequester, C extends AbstractConnector> {
    
    protected C connector;
    
    public AbstractDatabase(C connector) {
        this.connector = connector;
    }
    
    public C getConnector() {
        return connector;
    }
    
    public T setConnector(C connector) {
        this.connector = connector;
        return (T) this;
    }
    
    public abstract boolean isConnected();
    
    public boolean start() {
        return start(null, null);
    }
    
    public abstract boolean start(String username, byte[] password);
    
    public abstract boolean stop();
    
    // Gets
    
    public abstract List<AuthorizationToken> getAllAuthorizationTokens();
    
    public abstract AuthorizationToken getAuthorizationTokenByToken(String token);
    
    public boolean hasAuthorizationToken(AuthorizationToken authorizationToken) {
        return authorizationToken != null && hasAuthorizationToken(authorizationToken.getToken());
    }
    
    public abstract boolean hasAuthorizationToken(String token);
    
    public abstract V getVideoByVideoId(String videoId);
    
    public abstract List<V> getAllVideos();
    
    public abstract List<V> getVideosByPlaylistId(String playlistId);
    
    public abstract List<String> getAllVideoIds();
    
    public abstract List<String> getVideoIdsByPlaylistId(String playlistId);
    
    public abstract P getPlaylistByPlaylistId(String playlistId);
    
    public abstract List<P> getAllPlaylists();
    
    public abstract List<P> getPlaylistsByVideoId(String videoId);
    
    public abstract List<String> getAllPlaylistIds();
    
    public abstract List<String> getPlaylistIdsByVideoId(String videoId);
    
    public abstract int getIndexInPlaylist(String playlistId, String videoId);
    
    public abstract boolean playlistContainsVideo(String playlistId, String videoId);
    
    public abstract M getMediaFileByVideoIdAndFile(String videoId, String file);
    
    public abstract List<M> getMediaFilesByVideoId(String videoId);
    
    public abstract E getExtraFileByVideoIdAndFile(String videoId, String file);
    
    public abstract List<E> getExtraFilesByVideoId(String videoId);
    
    public abstract List<CH> getAllChannels();
    
    public abstract List<String> getAllChannelIds();
    
    public abstract CH getChannelByChannelId(String channelId);
    
    public abstract List<V> getVideosByChannelId(String channelId);
    
    public abstract List<String> getVideoIdsByChannelId(String channelId);
    
    public abstract boolean hasVideoOnChannel(String channelId, String videoId);
    
    public abstract List<U> getAllUploaders();
    
    public abstract List<String> getAllUploaderIds();
    
    public abstract U getUploaderByUploaderId(String uploaderId);
    
    public abstract List<V> getVideosByUploaderId(String uploaderId);
    
    public abstract List<String> getVideoIdsByUploaderId(String uploaderId);
    
    public abstract boolean hasVideoUploaded(String uploaderId, String videoId);
    
    public abstract List<P> getPlaylistsByUploaderId(String uploaderId);
    
    public abstract List<String> getPlaylistIdsByUploaderId(String uploaderId);
    
    public abstract Q getQueuedVideoById(int id);
    
    public abstract List<Q> getAllQueuedVideos();
    
    public abstract List<Q> getQueuedVideosByVideoId(String videoId);
    
    public abstract List<Q> getQueuedVideosByRequesterId(int requesterId);
    
    public abstract List<String> getQueuedVideoIdsByRequesterId(int requesterId);
    
    public abstract Q getNextQueuedVideo();
    
    public abstract List<Q> getNextQueuedVideos();
    
    public abstract List<R> getAllRequesters();
    
    public abstract List<Integer> getAllRequesterIds();
    
    public abstract R getRequesterByRequesterId(int requesterId);
    
    public abstract R getRequesterByTag(String tag);
    
    // Adds
    
    public abstract boolean addAuthorizationToken(AuthorizationToken authorizationToken);
    
    // Sets
    
    public abstract boolean setAuthorizationTokenByToken(AuthorizationToken authorizationToken, String oldToken);
    
    public abstract boolean setAuthorizationTokenTimesUsedByToken(String token, int timesUsed);
    
    public abstract boolean setVideoByVideoId(V video, String videoId);
    
    public abstract boolean setVideosByPlaylistId(List<V> videos, String playlistId);
    
    //public abstract boolean setVideoIdsByPlaylistId(List<String> videoIds, String playlistId);
    
    public abstract boolean setPlaylistByPlaylistId(P playlist, String playlistId);
    
    public abstract boolean setPlaylistsByVideoId(List<P> playlists, String videoId);
    
    //public abstract boolean setPlaylistIdsByVideoId(List<String> playlistIds, String videoId);
    
    //public abstract boolean playlistContainsVideo(String playlistId, String videoId);
    
    public abstract boolean setMediaFileByVideoIdAndFile(MediaFile mediaFile, String videoId, String file);
    
    public abstract boolean setMediaFilesByVideoId(List<M> mediaFiles, String videoId);
    
    public abstract boolean setExtraFileByVideoIdAndFile(ExtraFile extraFile, String videoId, String file);
    
    public abstract boolean setExtraFilesByVideoId(List<E> extraFiles, String videoId);
    
    public abstract boolean setChannelByChannelId(CH channel, String channelId);
    
    public abstract boolean setUploaderByUploaderId(U uploader, String uploaderId);
    
    public abstract boolean setQueuedVideoById(Q queuedVideo, int id);
    
    public abstract boolean setRequesterByRequesterId(R requester, int requesterId);
    
    public abstract boolean setRequesterByRequesterId(R requester, String tag);
    
    // Removes
    
    public abstract boolean removeAuthorizationTokenByToken(String token);
    
    //
    
    public boolean isValidAuthorizationToken(AuthorizationToken authorizationToken) {
        return authorizationToken != null && isValidAuthorizationToken(authorizationToken.getToken());
    }
    
    public boolean isValidAuthorizationToken(String token) {
        if (token == null || token.isEmpty() || !isConnected()) {
            return false;
        }
        final AuthorizationToken authorizationToken = getAuthorizationTokenByToken(token);
        if (authorizationToken == null || !authorizationToken.isValidNow()) {
            return false;
        }
        return authorizationToken.getLevel().hasUnlimitedUses() || authorizationToken.getUsed() < authorizationToken.getLevel().getUses();
    }
    
    public boolean useAuthorizationToken(AuthorizationToken authorizationToken) {
        return authorizationToken != null && useAuthorizationToken(authorizationToken.getToken());
    }
    
    public boolean useAuthorizationToken(String token) {
        if (token == null || token.isEmpty() || !isConnected()) {
            return false;
        }
        final AuthorizationToken authorizationToken = getAuthorizationTokenByToken(token);
        if (authorizationToken == null || !authorizationToken.isValidNow()) {
            return false;
        }
        return setAuthorizationTokenTimesUsedByToken(token, authorizationToken.getUsed() + 1);
    }
    
    @Override
    public String toString() {
        return "AbstractDatabase{" + "connector=" + connector + '}';
    }
    
}
