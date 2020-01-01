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

CREATE TABLE IF NOT EXISTS videos (
    id VARCHAR(16) NOT NULL,
    uploader VARCHAR(256),
    uploaderId VARCHAR(128),
    title VARCHAR(1024),
    altTitle VARCHAR(1024),
    duration BIGINT,
    uploadDate BIGINT,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS files (
    videoId VARCHAR(16) NOT NULL,
    file VARCHAR(1024) NOT NULL,
    format VARCHAR(128),
    width INT,
    height INT,
    fps INT,
    PRIMARY KEY (videoId, file)
);
CREATE TABLE IF NOT EXISTS playlists (
    id VARCHAR(64) NOT NULL,
    title VARCHAR(256),
    name VARCHAR(128),
    uploader VARCHAR(256),
    uploaderId VARCHAR(128),
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS playlistVideos (
    playlistId VARCHAR(64) NOT NULL,
    videoId VARCHAR(16) NOT NULL,
    index INT DEFAULT -1,
    PRIMARY KEY (playlistId, videoId)
);