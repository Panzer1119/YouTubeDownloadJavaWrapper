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

public class JsonTest {
    
    public static void main(String[] args) {
        final VideoInfo videoInfo_1 = new VideoInfo(args[0], "title", 0);
        System.out.println("videoInfo_1=" + videoInfo_1);
        System.out.println("videoInfo_1.getUrl()=" + videoInfo_1.getUrl());
        final String jsonString_1 = videoInfo_1.toJsonString();
        System.out.println("jsonString_1=" + jsonString_1);
        final JsonObject jsonObject_1 = videoInfo_1.toJsonObject();
        System.out.println("jsonObject_1=" + jsonObject_1);
        System.out.println("jsonObject_1.get(\"title\")=" + jsonObject_1.get("title"));
    }
    
}
