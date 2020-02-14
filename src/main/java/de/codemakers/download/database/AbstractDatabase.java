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

import de.codemakers.download.database.entities.AbstractFile;
import de.codemakers.download.database.entities.AbstractPlaylist;
import de.codemakers.download.database.entities.AbstractVideo;
import de.codemakers.download.database.entities.impl.ExtraFile;
import de.codemakers.download.database.entities.impl.MediaFile;

import java.util.List;

public abstract class AbstractDatabase<T extends AbstractDatabase, M extends AbstractFile, E extends AbstractFile, V extends AbstractVideo, P extends AbstractPlaylist, C extends AbstractConnector> {
    
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
    
    public abstract V getVideoByVideoId(String videoId);
    
    public abstract List<V> getVideosByPlaylistId(String playlistId);
    
    public abstract List<String> getVideoIdsByPlaylistId(String playlistId);
    
    public abstract P getPlaylistByPlaylistId(String playlistId);
    
    public abstract List<P> getPlaylistsByVideoId(String videoId);
    
    public abstract List<String> getPlaylistIdsByVideoId(String videoId);
    
    public abstract int getIndexInPlaylist(String playlistId, String videoId);
    
    public abstract boolean playlistContainsVideo(String playlistId, String videoId);
    
    public abstract M getMediaFileByVideoIdAndFile(String videoId, String file);
    
    public abstract List<M> getMediaFilesByVideoId(String videoId);
    
    public abstract E getExtraFileByVideoIdAndFile(String videoId, String file);
    
    public abstract List<E> getExtraFilesByVideoId(String videoId);
    
    // Adds
    
    // Sets
    
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
    
    // Removes
    
    //
    
    @Override
    public String toString() {
        return "AbstractDatabase{" + "connector=" + connector + '}';
    }
    
}
