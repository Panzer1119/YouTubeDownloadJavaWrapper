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