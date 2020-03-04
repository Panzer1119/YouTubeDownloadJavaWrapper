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

package de.codemakers.download.sources;

import java.io.Serializable;

@FunctionalInterface
public interface Source extends Serializable {
    
    String getSource();
    
    default String getSourceQuoted() {
        final String source = getSource();
        if (source == null) {
            return null;
        }
        return "\"" + source + "\"";
    }
    
    default String getId() {
        return null;
    }
    
    default boolean isVideo() {
        return true;
    }
    
    default boolean isPlaylist() {
        return !isVideo();
    }
    
    default boolean providesMultipleVideos() {
        return isPlaylist();
    }
    
}
