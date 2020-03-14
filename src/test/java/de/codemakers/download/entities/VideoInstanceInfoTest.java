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

package de.codemakers.download.entities;/*
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

import de.codemakers.base.logger.LogLevel;
import de.codemakers.base.logger.Logger;
import de.codemakers.download.YouTubeDL;
import de.codemakers.download.sources.YouTubeSource;

public class VideoInstanceInfoTest {
    
    public static void main(String[] args) {
        Logger.getDefaultAdvancedLeveledLogger().setMinimumLogLevel(LogLevel.FINE);
        /*
        final String input = args[0];
        final Matcher matcher = VideoInstanceInfo.PATTERN_OUTPUT_FORMAT_EVERYTHING.matcher(input);
        Logger.log("matcher.matches()=" + matcher.matches());
        */
        final String videoId = args[0];
        Logger.log("videoId=" + videoId);
        final YouTubeSource source = YouTubeSource.ofId(videoId);
        Logger.log("source=" + source);
        final VideoInstanceInfo videoInstanceInfo = YouTubeDL.downloadVideoInstanceInfo(source);
        Logger.log("videoInstanceInfo=" + videoInstanceInfo);
    }
    
}
