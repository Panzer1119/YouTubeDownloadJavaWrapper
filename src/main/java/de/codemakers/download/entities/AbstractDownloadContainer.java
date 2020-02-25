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

package de.codemakers.download.entities;

import de.codemakers.base.util.tough.ToughFunction;
import de.codemakers.download.database.AbstractDatabase;
import de.codemakers.download.database.entities.AbstractPlaylist;
import de.codemakers.download.database.entities.AbstractVideo;
import de.codemakers.download.sources.Source;

import java.util.NoSuchElementException;
import java.util.Objects;

public abstract class AbstractDownloadContainer<D extends AbstractDatabase, S extends Source, PR extends AbstractDownloadProgress, V extends AbstractVideo, P extends AbstractPlaylist> {
    
    protected final D database;
    protected final S source;
    protected final DownloadSettings downloadSettings;
    protected final PR downloadProgress;
    
    public AbstractDownloadContainer(S source, DownloadSettings downloadSettings) {
        this(null, source, downloadSettings, null);
    }
    
    public AbstractDownloadContainer(S source, DownloadSettings downloadSettings, PR downloadProgress) {
        this(null, source, downloadSettings, downloadProgress);
    }
    
    public AbstractDownloadContainer(D database, S source, DownloadSettings downloadSettings) {
        this(database, source, downloadSettings, null);
    }
    
    public AbstractDownloadContainer(D database, S source, DownloadSettings downloadSettings, PR downloadProgress) {
        this.database = database;
        this.source = Objects.requireNonNull(source, "source");
        this.downloadSettings = Objects.requireNonNull(downloadSettings, "downloadSettings");
        this.downloadProgress = downloadProgress;
    }
    
    public D getDatabase() {
        return database;
    }
    
    public S getSource() {
        return source;
    }
    
    public DownloadSettings getDownloadSettings() {
        return downloadSettings;
    }
    
    public boolean isUsingDownloadProgress() {
        return downloadProgress != null;
    }
    
    public PR getDownloadProgress() {
        return downloadProgress;
    }
    
    public V asVideo() {
        if (!getSource().isVideo()) {
            throw new NoSuchElementException(getClass().getSimpleName() + " is not a Video");
        }
        return (V) useDatabaseOrNull((database) -> database.getVideoByVideoId(source.getId()));
    }
    
    public P asPlaylist() {
        if (!getSource().isVideo()) {
            throw new NoSuchElementException(getClass().getSimpleName() + " is not a Playlist");
        }
        return (P) useDatabaseOrNull((database) -> database.getPlaylistByPlaylistId(source.getId()));
    }
    
    @Override
    public String toString() {
        return "AbstractDownloadContainer{" + "database=" + database + ", source=" + source + ", downloadSettings=" + downloadSettings + ", downloadProgress=" + downloadProgress + '}';
    }
    
    protected <R> R useDatabaseOrNull(ToughFunction<D, R> databaseFunction) {
        return useDatabase(databaseFunction, null);
    }
    
    protected boolean useDatabaseOrFalse(ToughFunction<D, Boolean> databaseFunction) {
        return useDatabase(databaseFunction, false);
    }
    
    protected <R> R useDatabase(ToughFunction<D, R> databaseFunction, R defaultValue) {
        final D database = getDatabase();
        if (database == null) {
            return defaultValue;
        }
        return databaseFunction.applyWithoutException(database);
    }
    
}
