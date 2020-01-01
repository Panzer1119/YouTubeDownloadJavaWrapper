/*
 *    Copyright 2019-2020 Paul Hagedorn (Panzer1119)
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

package de.codemakers.download;

import de.codemakers.download.sources.Source;
import de.codemakers.download.sources.YouTubeSource;
import de.codemakers.io.file.AdvancedFile;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class DownloadInfo implements Serializable {
    
    private final AdvancedFile directory;
    private final Source source;
    private AdvancedFile logFile;
    //Temp
    private int expectedDownloads = 2;
    private boolean useConfig = true;
    private String[] arguments = null;
    
    public DownloadInfo(String source) {
        this(YouTubeSource.ofString(source));
    }
    
    public DownloadInfo(Source source) {
        this(YouTubeDL.getDirectory(), source, null);
    }
    
    public DownloadInfo(AdvancedFile directory, String source) {
        this(directory, YouTubeSource.ofString(source));
    }
    
    public DownloadInfo(AdvancedFile directory, Source source) {
        this(directory, source, null);
    }
    
    public DownloadInfo(String source, AdvancedFile logFile) {
        this(YouTubeSource.ofString(source), logFile);
    }
    
    public DownloadInfo(Source source, AdvancedFile logFile) {
        this(YouTubeDL.getDirectory(), source, logFile);
    }
    
    public DownloadInfo(AdvancedFile directory, String source, AdvancedFile logFile) {
        this(directory, YouTubeSource.ofString(source), logFile);
    }
    
    public DownloadInfo(AdvancedFile directory, Source source, AdvancedFile logFile) {
        this.directory = directory;
        this.source = source;
        this.logFile = logFile;
    }
    
    public AdvancedFile getDirectory() {
        return directory;
    }
    
    protected AdvancedFile getDirectoryAbsolute() {
        return directory.getAbsoluteFile();
    }
    
    protected String getDirectoryAbsolutePathAndEscaped() {
        return "\"" + directory.getAbsolutePath() + "\"";
    }
    
    public Source getSource() {
        return source;
    }
    
    public String getUrl() {
        return source.getSource();
    }
    
    public AdvancedFile getLogFile() {
        if (logFile == null) {
            setLogFile(YouTubeDL.createLogFile(getSource()));
        }
        return logFile;
    }
    
    public DownloadInfo setLogFile(AdvancedFile log) {
        this.logFile = log;
        return this;
    }
    
    public int getExpectedDownloads() {
        return expectedDownloads;
    }
    
    public DownloadInfo setExpectedDownloads(int expectedDownloads) {
        this.expectedDownloads = expectedDownloads;
        return this;
    }
    
    public boolean isUsingConfig() {
        return useConfig;
    }
    
    public DownloadInfo setUseConfig(boolean useConfig) {
        this.useConfig = useConfig;
        return this;
    }
    
    public boolean hasArguments() {
        return arguments != null && arguments.length > 0;
    }
    
    public String[] getArguments() {
        return arguments;
    }
    
    public DownloadInfo setArguments(String... arguments) {
        this.arguments = arguments;
        return this;
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final DownloadInfo that = (DownloadInfo) other;
        return expectedDownloads == that.expectedDownloads && useConfig == that.useConfig && Objects.equals(directory, that.directory) && Objects.equals(source, that.source) && Objects.equals(logFile, that.logFile) && Arrays.equals(arguments, that.arguments);
    }
    
    @Override
    public int hashCode() {
        int result = Objects.hash(directory, source, logFile, expectedDownloads, useConfig);
        result = 31 * result + Arrays.hashCode(arguments);
        return result;
    }
    
    @Override
    public String toString() {
        return "DownloadInfo{" + "directory=" + directory + ", source=" + source + ", logFile=" + logFile + ", expectedDownloads=" + expectedDownloads + ", useConfig=" + useConfig + ", arguments=" + Arrays.toString(arguments) + '}';
    }
    
}
