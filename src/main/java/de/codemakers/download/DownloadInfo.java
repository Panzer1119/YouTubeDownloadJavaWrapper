/*
 *    Copyright 2019 Paul Hagedorn (Panzer1119)
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

import de.codemakers.io.file.AdvancedFile;

import java.util.Arrays;
import java.util.Objects;

public class DownloadInfo {
    
    private final AdvancedFile directory;
    private final String url;
    //Temp
    private boolean useConfig = true;
    private String[] arguments = null;
    
    public DownloadInfo(AdvancedFile directory, String url) {
        this.directory = directory;
        this.url = url;
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
    
    public String getUrl() {
        return url;
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
    
    public DownloadInfo setArguments(String[] arguments) {
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
        return useConfig == that.useConfig && Objects.equals(directory, that.directory) && Objects.equals(url, that.url) && Arrays.equals(arguments, that.arguments);
    }
    
    @Override
    public int hashCode() {
        int result = Objects.hash(directory, url, useConfig);
        result = 31 * result + Arrays.hashCode(arguments);
        return result;
    }
    
    @Override
    public String toString() {
        return "DownloadInfo{" + "directory=" + directory + ", url='" + url + '\'' + ", useConfig=" + useConfig + ", arguments=" + Arrays.toString(arguments) + '}';
    }
    
}
