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

package de.codemakers.download.database.entities;

import java.util.List;

public abstract class AbstractDatabase<T extends AbstractDatabase, M, E, V extends AbstractVideo, P extends AbstractPlaylist> {
    
    public abstract V getVideoByVideoId(String videoId);
    
    public abstract List<V> getVideosByPlaylistId(String playlistId);
    
    public abstract List<String> getVideoIdsByPlaylistId(String playlistId);
    
    public abstract P getPlaylistByPlaylistId(String playlistId);
    
    public abstract List<P> getPlaylistsByVideoId(String videoId);
    
    public abstract List<String> getPlaylistIdsByVideoId(String videoId);
    
    public abstract boolean playlistContainsVideo(String playlistId, String videoId);
    
    public abstract List<M> getMediaFilesByVideoId(String videoId);
    
    public abstract List<E> getExtraFilesByVideoId(String videoId);
    
}
