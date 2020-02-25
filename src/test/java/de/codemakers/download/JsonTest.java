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

package de.codemakers.download;

import com.google.gson.JsonObject;
import de.codemakers.base.logger.LogLevel;
import de.codemakers.base.logger.Logger;
import de.codemakers.base.multiplets.Doublet;
import de.codemakers.download.sources.YouTubeSource;
import de.codemakers.download.util.Misc;

import java.util.List;
import java.util.concurrent.Future;

public class JsonTest {
    
    public static void main(String[] args) throws Exception {
        Logger.getDefaultAdvancedLeveledLogger().setMinimumLogLevel(LogLevel.FINE);
        /*
        final VideoInfo videoInfo_1 = new VideoInfo(args[0], "title", 0);
        System.out.println("videoInfo_1=" + videoInfo_1);
        //System.out.println("videoInfo_1.getUrl()=" + videoInfo_1.getUrl());
        final String jsonString_1 = videoInfo_1.toJsonString();
        System.out.println("jsonString_1=" + jsonString_1);
        final JsonObject jsonObject_1 = videoInfo_1.toJsonObject();
        System.out.println("jsonObject_1=" + jsonObject_1);
        System.out.println("jsonObject_1.get(\"title\")=" + jsonObject_1.get("title"));
        */
        final JsonObject jsonObject = YouTubeDL.downloadInfoEverything(args[0]);
        System.out.println();
        System.out.println();
        System.out.println("jsonObject=" + jsonObject);
        System.out.println();
        System.out.println();
        System.out.println("Pretty: " + Misc.GSON_PRETTY.toJson(jsonObject));
        final Doublet<List<FileInfo>, Future<List<FileInfo>>> doublet = YouTubeDL.downloadFileInfosAndThenAsync(YouTubeSource.ofId(args[0]));
        doublet.getB().get();
        System.out.println(doublet.getA());
    }
    
}
