CREATE TABLE IF NOT EXISTS videos
(
    id         VARCHAR(16) NOT NULL,
    uploader   VARCHAR(256),
    uploaderId VARCHAR(128),
    title      VARCHAR(1024),
    altTitle   VARCHAR(1024),
    duration   BIGINT,
    uploadDate BIGINT,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS mediaFiles
(
    videoId  VARCHAR(16)   NOT NULL,
    file     VARCHAR(1024) NOT NULL,
    fileType VARCHAR(1)    NOT NULL DEFAULT 'B',
    format   VARCHAR(128),
    vcodec   VARCHAR(32),
    acodec   VARCHAR(32),
    width    INT                    DEFAULT 0,
    height   INT                    DEFAULT 0,
    fps      INT                    DEFAULT 0,
    asr      INT                    DEFAULT 0,
    PRIMARY KEY (videoId, file)
);
CREATE TABLE IF NOT EXISTS extraFiles
(
    videoId  VARCHAR(16)   NOT NULL,
    file     VARCHAR(1024) NOT NULL,
    fileType VARCHAR(16)   NOT NULL,
    PRIMARY KEY (videoId, file)
);
CREATE TABLE IF NOT EXISTS playlists
(
    id         VARCHAR(64) NOT NULL,
    title      VARCHAR(256),
    playlist   VARCHAR(128),
    uploader   VARCHAR(256),
    uploaderId VARCHAR(128),
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS playlistVideos
(
    playlistId    VARCHAR(64) NOT NULL,
    videoId       VARCHAR(16) NOT NULL,
    playlistIndex INT DEFAULT -1,
    PRIMARY KEY (playlistId, videoId)
);
CREATE TABLE `videoQueue`
(
    `id`              int(11)     NOT NULL AUTO_INCREMENT,
    `videoId`         varchar(64) NOT NULL,
    `priority`        int(11)     NOT NULL DEFAULT 0,
    `requested`       timestamp   NOT NULL DEFAULT current_timestamp(),
    `arguments`       varchar(2048)        DEFAULT NULL,
    `configFile`      varchar(512)         DEFAULT NULL,
    `outputDirectory` varchar(512)         DEFAULT NULL,
    PRIMARY KEY (`id`)
);