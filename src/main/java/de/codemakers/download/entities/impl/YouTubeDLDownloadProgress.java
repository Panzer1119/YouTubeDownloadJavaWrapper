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

package de.codemakers.download.entities.impl;

import de.codemakers.download.entities.AbstractDownloadProgress;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouTubeDLDownloadProgress extends AbstractDownloadProgress<YouTubeDLDownloadProgress> {
    
    public static final String DOWNLOAD_LINE_PROGRESS_REG_EX = "\\[download\\]\\s*(\\d+(?:.\\d+)?)% of (\\d+(?:.\\d+)?)([a-zA-Z]+) at\\s*(\\d+(?:.\\d+)?)([a-zA-Z]+\\/s) ETA (\\d+:\\d+)";
    public static final Pattern PATTERN_DOWNLOAD_PROGRESS_LINE = Pattern.compile(DOWNLOAD_LINE_PROGRESS_REG_EX);
    public static final String DOWNLOAD_LINE_FINISHED_REG_EX = "\\[download\\] 100% of (\\d+(?:.\\d+)?)([a-zA-Z]+) in (\\d+:\\d+)";
    public static final Pattern PATTERN_DOWNLOAD_LINE_FINISHED = Pattern.compile(DOWNLOAD_LINE_FINISHED_REG_EX);
    
    public YouTubeDLDownloadProgress(int expectedDownloads) {
        super(expectedDownloads);
    }
    
    @Override
    public boolean nextLine(String line) {
        if (line == null) {
            return false;
        }
        if (PATTERN_DOWNLOAD_LINE_FINISHED.matcher(line).matches()) {
            return true;
        }
        final Matcher matcher = PATTERN_DOWNLOAD_PROGRESS_LINE.matcher(line);
        if (!matcher.matches()) {
            return false;
        }
        final int index = getNextProgressIndex();
        final float progress = Float.parseFloat(matcher.group(1));
        setProgress(index, progress); //TODO Maybe use "Math.max(progress, getProgress(index))" instead of just "progress"?
        //TODO Maybe save the ETA? So later we can give good ETAs overall?
        return true;
    }
    
    @Override
    public String toString() {
        return "YouTubeDLDownloadProgress{" + "progresses=" + Arrays.toString(progresses) + ", alive=" + alive + ", started=" + started + ", successful=" + successful + '}';
    }
    
}
