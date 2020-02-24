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
import de.codemakers.io.file.AdvancedFile;

import java.util.regex.Pattern;

public class VideoInstanceInfo {
    
    protected static final String OUTPUT_FORMAT_EVERYTHING = new String(new AdvancedFile(YouTubeDL.INTERN_FOLDER, "youtube-dl_output_format_everything.txt").readBytesWithoutException());
    protected static final String OUTPUT_FORMAT_EVERYTHING_REGEX = new String(new AdvancedFile(YouTubeDL.INTERN_FOLDER, "youtube-dl_output_format_everything_regex.txt").readBytesWithoutException());
    protected static final Pattern PATTERN_OUTPUT_FORMAT_EVERYTHING = Pattern.compile(OUTPUT_FORMAT_EVERYTHING_REGEX);
    
    protected String id = null;
    protected String title = null;
    protected String url = null;
    protected String ext = null;
    protected String alt_title = null;
    protected String display_id = null;
    protected String uploader = null;
    protected String license = null;
    protected String creator = null;
    protected String release_date = null;
    protected Long timestamp = null;
    protected String upload_date = null;
    protected String uploader_id = null;
    protected String channel = null;
    protected String channel_id = null;
    protected String location = null;
    protected Long duration = null;
    protected Long view_count = null;
    protected Long like_count = null;
    protected Long dislike_count = null;
    protected Long repost_count = null;
    protected Long average_rating = null;
    protected Long comment_count = null;
    protected Long age_limit = null;
    protected Boolean is_live = null;
    protected Long start_time = null;
    protected Long end_time = null;
    protected String format = null;
    protected String format_id = null;
    protected String format_note = null;
    protected Long width = null;
    protected Long height = null;
    protected String resolution = null;
    protected Long tbr = null;
    protected Long abr = null;
    protected String acodec = null;
    protected Long asr = null;
    protected Long vbr = null;
    protected Long fps = null;
    protected String vcodec = null;
    protected String container = null;
    protected Long filesize = null;
    protected Long filesize_approx = null;
    protected String protocol = null;
    protected String extractor = null;
    protected String extractor_key = null;
    protected Long epoch = null;
    protected Long autonumber = null;
    protected String playlist = null;
    protected Long playlist_index = null;
    protected String playlist_id = null;
    protected String playlist_title = null;
    protected String playlist_uploader = null;
    protected String playlist_uploader_id = null;
    protected String chapter = null;
    protected Long chapter_number = null;
    protected String chapter_id = null;
    protected String series = null;
    protected String season = null;
    protected Long season_number = null;
    protected String season_id = null;
    protected String episode = null;
    protected Long episode_number = null;
    protected String episode_id = null;
    protected String track = null;
    protected Long track_number = null;
    protected String track_id = null;
    protected String artist = null;
    protected String genre = null;
    protected String album = null;
    protected String album_type = null;
    protected String album_artist = null;
    protected Long disc_number = null;
    protected Long release_year = null;
    
    public VideoInstanceInfo() {
    }
    
    public VideoInstanceInfo(String id, String title, String url, String ext, String alt_title, String display_id, String uploader, String license, String creator, String release_date, Long timestamp, String upload_date, String uploader_id, String channel, String channel_id, String location, Long duration, Long view_count, Long like_count, Long dislike_count, Long repost_count, Long average_rating, Long comment_count, Long age_limit, Boolean is_live, Long start_time, Long end_time, String format, String format_id, String format_note, Long width, Long height, String resolution, Long tbr, Long abr, String acodec, Long asr, Long vbr, Long fps, String vcodec, String container, Long filesize, Long filesize_approx, String protocol, String extractor, String extractor_key, Long epoch, Long autonumber, String playlist, Long playlist_index, String playlist_id, String playlist_title, String playlist_uploader, String playlist_uploader_id, String chapter, Long chapter_number, String chapter_id, String series, String season, Long season_number, String season_id, String episode, Long episode_number, String episode_id, String track, Long track_number, String track_id, String artist, String genre, String album, String album_type, String album_artist, Long disc_number, Long release_year) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.ext = ext;
        this.alt_title = alt_title;
        this.display_id = display_id;
        this.uploader = uploader;
        this.license = license;
        this.creator = creator;
        this.release_date = release_date;
        this.timestamp = timestamp;
        this.upload_date = upload_date;
        this.uploader_id = uploader_id;
        this.channel = channel;
        this.channel_id = channel_id;
        this.location = location;
        this.duration = duration;
        this.view_count = view_count;
        this.like_count = like_count;
        this.dislike_count = dislike_count;
        this.repost_count = repost_count;
        this.average_rating = average_rating;
        this.comment_count = comment_count;
        this.age_limit = age_limit;
        this.is_live = is_live;
        this.start_time = start_time;
        this.end_time = end_time;
        this.format = format;
        this.format_id = format_id;
        this.format_note = format_note;
        this.width = width;
        this.height = height;
        this.resolution = resolution;
        this.tbr = tbr;
        this.abr = abr;
        this.acodec = acodec;
        this.asr = asr;
        this.vbr = vbr;
        this.fps = fps;
        this.vcodec = vcodec;
        this.container = container;
        this.filesize = filesize;
        this.filesize_approx = filesize_approx;
        this.protocol = protocol;
        this.extractor = extractor;
        this.extractor_key = extractor_key;
        this.epoch = epoch;
        this.autonumber = autonumber;
        this.playlist = playlist;
        this.playlist_index = playlist_index;
        this.playlist_id = playlist_id;
        this.playlist_title = playlist_title;
        this.playlist_uploader = playlist_uploader;
        this.playlist_uploader_id = playlist_uploader_id;
        this.chapter = chapter;
        this.chapter_number = chapter_number;
        this.chapter_id = chapter_id;
        this.series = series;
        this.season = season;
        this.season_number = season_number;
        this.season_id = season_id;
        this.episode = episode;
        this.episode_number = episode_number;
        this.episode_id = episode_id;
        this.track = track;
        this.track_number = track_number;
        this.track_id = track_id;
        this.artist = artist;
        this.genre = genre;
        this.album = album;
        this.album_type = album_type;
        this.album_artist = album_artist;
        this.disc_number = disc_number;
        this.release_year = release_year;
    }
    
    public String getId() {
        return id;
    }
    
    public VideoInstanceInfo setId(String id) {
        this.id = id;
        return this;
    }
    
    public String getTitle() {
        return title;
    }
    
    public VideoInstanceInfo setTitle(String title) {
        this.title = title;
        return this;
    }
    
    public String getUrl() {
        return url;
    }
    
    public VideoInstanceInfo setUrl(String url) {
        this.url = url;
        return this;
    }
    
    public String getExt() {
        return ext;
    }
    
    public VideoInstanceInfo setExt(String ext) {
        this.ext = ext;
        return this;
    }
    
    public String getAlt_title() {
        return alt_title;
    }
    
    public VideoInstanceInfo setAlt_title(String alt_title) {
        this.alt_title = alt_title;
        return this;
    }
    
    public String getDisplay_id() {
        return display_id;
    }
    
    public VideoInstanceInfo setDisplay_id(String display_id) {
        this.display_id = display_id;
        return this;
    }
    
    public String getUploader() {
        return uploader;
    }
    
    public VideoInstanceInfo setUploader(String uploader) {
        this.uploader = uploader;
        return this;
    }
    
    public String getLicense() {
        return license;
    }
    
    public VideoInstanceInfo setLicense(String license) {
        this.license = license;
        return this;
    }
    
    public String getCreator() {
        return creator;
    }
    
    public VideoInstanceInfo setCreator(String creator) {
        this.creator = creator;
        return this;
    }
    
    public String getRelease_date() {
        return release_date;
    }
    
    public VideoInstanceInfo setRelease_date(String release_date) {
        this.release_date = release_date;
        return this;
    }
    
    public Long getTimestamp() {
        return timestamp;
    }
    
    public VideoInstanceInfo setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }
    
    public String getUpload_date() {
        return upload_date;
    }
    
    public VideoInstanceInfo setUpload_date(String upload_date) {
        this.upload_date = upload_date;
        return this;
    }
    
    public String getUploader_id() {
        return uploader_id;
    }
    
    public VideoInstanceInfo setUploader_id(String uploader_id) {
        this.uploader_id = uploader_id;
        return this;
    }
    
    public String getChannel() {
        return channel;
    }
    
    public VideoInstanceInfo setChannel(String channel) {
        this.channel = channel;
        return this;
    }
    
    public String getChannel_id() {
        return channel_id;
    }
    
    public VideoInstanceInfo setChannel_id(String channel_id) {
        this.channel_id = channel_id;
        return this;
    }
    
    public String getLocation() {
        return location;
    }
    
    public VideoInstanceInfo setLocation(String location) {
        this.location = location;
        return this;
    }
    
    public Long getDuration() {
        return duration;
    }
    
    public VideoInstanceInfo setDuration(Long duration) {
        this.duration = duration;
        return this;
    }
    
    public Long getView_count() {
        return view_count;
    }
    
    public VideoInstanceInfo setView_count(Long view_count) {
        this.view_count = view_count;
        return this;
    }
    
    public Long getLike_count() {
        return like_count;
    }
    
    public VideoInstanceInfo setLike_count(Long like_count) {
        this.like_count = like_count;
        return this;
    }
    
    public Long getDislike_count() {
        return dislike_count;
    }
    
    public VideoInstanceInfo setDislike_count(Long dislike_count) {
        this.dislike_count = dislike_count;
        return this;
    }
    
    public Long getRepost_count() {
        return repost_count;
    }
    
    public VideoInstanceInfo setRepost_count(Long repost_count) {
        this.repost_count = repost_count;
        return this;
    }
    
    public Long getAverage_rating() {
        return average_rating;
    }
    
    public VideoInstanceInfo setAverage_rating(Long average_rating) {
        this.average_rating = average_rating;
        return this;
    }
    
    public Long getComment_count() {
        return comment_count;
    }
    
    public VideoInstanceInfo setComment_count(Long comment_count) {
        this.comment_count = comment_count;
        return this;
    }
    
    public Long getAge_limit() {
        return age_limit;
    }
    
    public VideoInstanceInfo setAge_limit(Long age_limit) {
        this.age_limit = age_limit;
        return this;
    }
    
    public Boolean getIs_live() {
        return is_live;
    }
    
    public VideoInstanceInfo setIs_live(Boolean is_live) {
        this.is_live = is_live;
        return this;
    }
    
    public Long getStart_time() {
        return start_time;
    }
    
    public VideoInstanceInfo setStart_time(Long start_time) {
        this.start_time = start_time;
        return this;
    }
    
    public Long getEnd_time() {
        return end_time;
    }
    
    public VideoInstanceInfo setEnd_time(Long end_time) {
        this.end_time = end_time;
        return this;
    }
    
    public String getFormat() {
        return format;
    }
    
    public VideoInstanceInfo setFormat(String format) {
        this.format = format;
        return this;
    }
    
    public String getFormat_id() {
        return format_id;
    }
    
    public VideoInstanceInfo setFormat_id(String format_id) {
        this.format_id = format_id;
        return this;
    }
    
    public String getFormat_note() {
        return format_note;
    }
    
    public VideoInstanceInfo setFormat_note(String format_note) {
        this.format_note = format_note;
        return this;
    }
    
    public Long getWidth() {
        return width;
    }
    
    public VideoInstanceInfo setWidth(Long width) {
        this.width = width;
        return this;
    }
    
    public Long getHeight() {
        return height;
    }
    
    public VideoInstanceInfo setHeight(Long height) {
        this.height = height;
        return this;
    }
    
    public String getResolution() {
        return resolution;
    }
    
    public VideoInstanceInfo setResolution(String resolution) {
        this.resolution = resolution;
        return this;
    }
    
    public Long getTbr() {
        return tbr;
    }
    
    public VideoInstanceInfo setTbr(Long tbr) {
        this.tbr = tbr;
        return this;
    }
    
    public Long getAbr() {
        return abr;
    }
    
    public VideoInstanceInfo setAbr(Long abr) {
        this.abr = abr;
        return this;
    }
    
    public String getAcodec() {
        return acodec;
    }
    
    public VideoInstanceInfo setAcodec(String acodec) {
        this.acodec = acodec;
        return this;
    }
    
    public Long getAsr() {
        return asr;
    }
    
    public VideoInstanceInfo setAsr(Long asr) {
        this.asr = asr;
        return this;
    }
    
    public Long getVbr() {
        return vbr;
    }
    
    public VideoInstanceInfo setVbr(Long vbr) {
        this.vbr = vbr;
        return this;
    }
    
    public Long getFps() {
        return fps;
    }
    
    public VideoInstanceInfo setFps(Long fps) {
        this.fps = fps;
        return this;
    }
    
    public String getVcodec() {
        return vcodec;
    }
    
    public VideoInstanceInfo setVcodec(String vcodec) {
        this.vcodec = vcodec;
        return this;
    }
    
    public String getContainer() {
        return container;
    }
    
    public VideoInstanceInfo setContainer(String container) {
        this.container = container;
        return this;
    }
    
    public Long getFilesize() {
        return filesize;
    }
    
    public VideoInstanceInfo setFilesize(Long filesize) {
        this.filesize = filesize;
        return this;
    }
    
    public Long getFilesize_approx() {
        return filesize_approx;
    }
    
    public VideoInstanceInfo setFilesize_approx(Long filesize_approx) {
        this.filesize_approx = filesize_approx;
        return this;
    }
    
    public String getProtocol() {
        return protocol;
    }
    
    public VideoInstanceInfo setProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }
    
    public String getExtractor() {
        return extractor;
    }
    
    public VideoInstanceInfo setExtractor(String extractor) {
        this.extractor = extractor;
        return this;
    }
    
    public String getExtractor_key() {
        return extractor_key;
    }
    
    public VideoInstanceInfo setExtractor_key(String extractor_key) {
        this.extractor_key = extractor_key;
        return this;
    }
    
    public Long getEpoch() {
        return epoch;
    }
    
    public VideoInstanceInfo setEpoch(Long epoch) {
        this.epoch = epoch;
        return this;
    }
    
    public Long getAutonumber() {
        return autonumber;
    }
    
    public VideoInstanceInfo setAutonumber(Long autonumber) {
        this.autonumber = autonumber;
        return this;
    }
    
    public String getPlaylist() {
        return playlist;
    }
    
    public VideoInstanceInfo setPlaylist(String playlist) {
        this.playlist = playlist;
        return this;
    }
    
    public Long getPlaylist_index() {
        return playlist_index;
    }
    
    public VideoInstanceInfo setPlaylist_index(Long playlist_index) {
        this.playlist_index = playlist_index;
        return this;
    }
    
    public String getPlaylist_id() {
        return playlist_id;
    }
    
    public VideoInstanceInfo setPlaylist_id(String playlist_id) {
        this.playlist_id = playlist_id;
        return this;
    }
    
    public String getPlaylist_title() {
        return playlist_title;
    }
    
    public VideoInstanceInfo setPlaylist_title(String playlist_title) {
        this.playlist_title = playlist_title;
        return this;
    }
    
    public String getPlaylist_uploader() {
        return playlist_uploader;
    }
    
    public VideoInstanceInfo setPlaylist_uploader(String playlist_uploader) {
        this.playlist_uploader = playlist_uploader;
        return this;
    }
    
    public String getPlaylist_uploader_id() {
        return playlist_uploader_id;
    }
    
    public VideoInstanceInfo setPlaylist_uploader_id(String playlist_uploader_id) {
        this.playlist_uploader_id = playlist_uploader_id;
        return this;
    }
    
    public String getChapter() {
        return chapter;
    }
    
    public VideoInstanceInfo setChapter(String chapter) {
        this.chapter = chapter;
        return this;
    }
    
    public Long getChapter_number() {
        return chapter_number;
    }
    
    public VideoInstanceInfo setChapter_number(Long chapter_number) {
        this.chapter_number = chapter_number;
        return this;
    }
    
    public String getChapter_id() {
        return chapter_id;
    }
    
    public VideoInstanceInfo setChapter_id(String chapter_id) {
        this.chapter_id = chapter_id;
        return this;
    }
    
    public String getSeries() {
        return series;
    }
    
    public VideoInstanceInfo setSeries(String series) {
        this.series = series;
        return this;
    }
    
    public String getSeason() {
        return season;
    }
    
    public VideoInstanceInfo setSeason(String season) {
        this.season = season;
        return this;
    }
    
    public Long getSeason_number() {
        return season_number;
    }
    
    public VideoInstanceInfo setSeason_number(Long season_number) {
        this.season_number = season_number;
        return this;
    }
    
    public String getSeason_id() {
        return season_id;
    }
    
    public VideoInstanceInfo setSeason_id(String season_id) {
        this.season_id = season_id;
        return this;
    }
    
    public String getEpisode() {
        return episode;
    }
    
    public VideoInstanceInfo setEpisode(String episode) {
        this.episode = episode;
        return this;
    }
    
    public Long getEpisode_number() {
        return episode_number;
    }
    
    public VideoInstanceInfo setEpisode_number(Long episode_number) {
        this.episode_number = episode_number;
        return this;
    }
    
    public String getEpisode_id() {
        return episode_id;
    }
    
    public VideoInstanceInfo setEpisode_id(String episode_id) {
        this.episode_id = episode_id;
        return this;
    }
    
    public String getTrack() {
        return track;
    }
    
    public VideoInstanceInfo setTrack(String track) {
        this.track = track;
        return this;
    }
    
    public Long getTrack_number() {
        return track_number;
    }
    
    public VideoInstanceInfo setTrack_number(Long track_number) {
        this.track_number = track_number;
        return this;
    }
    
    public String getTrack_id() {
        return track_id;
    }
    
    public VideoInstanceInfo setTrack_id(String track_id) {
        this.track_id = track_id;
        return this;
    }
    
    public String getArtist() {
        return artist;
    }
    
    public VideoInstanceInfo setArtist(String artist) {
        this.artist = artist;
        return this;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public VideoInstanceInfo setGenre(String genre) {
        this.genre = genre;
        return this;
    }
    
    public String getAlbum() {
        return album;
    }
    
    public VideoInstanceInfo setAlbum(String album) {
        this.album = album;
        return this;
    }
    
    public String getAlbum_type() {
        return album_type;
    }
    
    public VideoInstanceInfo setAlbum_type(String album_type) {
        this.album_type = album_type;
        return this;
    }
    
    public String getAlbum_artist() {
        return album_artist;
    }
    
    public VideoInstanceInfo setAlbum_artist(String album_artist) {
        this.album_artist = album_artist;
        return this;
    }
    
    public Long getDisc_number() {
        return disc_number;
    }
    
    public VideoInstanceInfo setDisc_number(Long disc_number) {
        this.disc_number = disc_number;
        return this;
    }
    
    public Long getRelease_year() {
        return release_year;
    }
    
    public VideoInstanceInfo setRelease_year(Long release_year) {
        this.release_year = release_year;
        return this;
    }
    
    public JsonObject toJsonObject() {
        return Misc.GSON.fromJson(toJsonString(), JsonObject.class);
    }
    
    public String toJsonString() {
        return "{\"id\":\"" + id + "\",\"title\":\"" + title + "\",\"url\":\"" + url + "\",\"ext\":\"" + ext + "\",\"alt_title\":\"" + alt_title + "\",\"display_id\":\"" + display_id + "\",\"uploader\":\"" + uploader + "\",\"license\":\"" + license + "\",\"creator\":\"" + creator + "\",\"release_date\":\"" + release_date + "\",\"upload_date\":\"" + upload_date + "\",\"uploader_id\":\"" + uploader_id + "\",\"channel\":\"" + channel + "\",\"channel_id\":\"" + channel_id + "\",\"location\":\"" + location + "\",\"format\":\"" + format + "\",\"format_id\":\"" + format_id + "\",\"format_note\":\"" + format_note + "\",\"resolution\":\"" + resolution + "\",\"acodec\":\"" + acodec + "\",\"vcodec\":\"" + vcodec + "\",\"container\":\"" + container + "\",\"protocol\":\"" + protocol + "\",\"extractor\":\"" + extractor + "\",\"extractor_key\":\"" + extractor_key + "\",\"playlist\":\"" + playlist + "\",\"playlist_id\":\"" + playlist_id + "\",\"playlist_title\":\"" + playlist_title + "\",\"playlist_uploader\":\"" + playlist_uploader + "\",\"playlist_uploader_id\":\"" + playlist_uploader_id + "\",\"chapter\":\"" + chapter + "\",\"chapter_id\":\"" + chapter_id + "\",\"series\":\"" + series + "\",\"season\":\"" + season + "\",\"season_id\":\"" + season_id + "\",\"episode\":\"" + episode + "\",\"episode_id\":\"" + episode_id + "\",\"track\":\"" + track + "\",\"track_id\":\"" + track_id + "\",\"artist\":\"" + artist + "\",\"genre\":\"" + genre + "\",\"album\":\"" + album + "\",\"album_type\":\"" + album_type + "\",\"album_artist\":\"" + album_artist + "\",\"timestamp\":" + timestamp + ",\"duration\":" + duration + ",\"view_count\":" + view_count + ",\"like_count\":" + like_count + ",\"dislike_count\":" + dislike_count + ",\"repost_count\":" + repost_count + ",\"average_rating\":" + average_rating + ",\"comment_count\":" + comment_count + ",\"age_limit\":" + age_limit + ",\"start_time\":" + start_time + ",\"end_time\":" + end_time + ",\"width\":" + width + ",\"height\":" + height + ",\"tbr\":" + tbr + ",\"abr\":" + abr + ",\"asr\":" + asr + ",\"vbr\":" + vbr + ",\"fps\":" + fps + ",\"filesize\":" + filesize + ",\"filesize_approx\":" + filesize_approx + ",\"epoch\":" + epoch + ",\"autonumber\":" + autonumber + ",\"playlist_index\":" + playlist_index + ",\"chapter_number\":" + chapter_number + ",\"season_number\":" + season_number + ",\"episode_number\":" + episode_number + ",\"track_number\":" + track_number + ",\"disc_number\":" + disc_number + ",\"release_year\":" + release_year + ",\"is_live\":" + is_live + "}";
    }
    
    public String toJsonStringPretty() {
        return Misc.GSON_PRETTY.toJson(toJsonObject());
    }
    
    @Override
    public String toString() {
        return "VideoInstanceInfo{" + "id='" + id + '\'' + ", title='" + title + '\'' + ", url='" + url + '\'' + ", ext='" + ext + '\'' + ", alt_title='" + alt_title + '\'' + ", display_id='" + display_id + '\'' + ", uploader='" + uploader + '\'' + ", license='" + license + '\'' + ", creator='" + creator + '\'' + ", release_date='" + release_date + '\'' + ", timestamp=" + timestamp + ", upload_date='" + upload_date + '\'' + ", uploader_id='" + uploader_id + '\'' + ", channel='" + channel + '\'' + ", channel_id='" + channel_id + '\'' + ", location='" + location + '\'' + ", duration=" + duration + ", view_count=" + view_count + ", like_count=" + like_count + ", dislike_count=" + dislike_count + ", repost_count=" + repost_count + ", average_rating=" + average_rating + ", comment_count=" + comment_count + ", age_limit=" + age_limit + ", is_live=" + is_live + ", start_time=" + start_time + ", end_time=" + end_time + ", format='" + format + '\'' + ", format_id='" + format_id + '\'' + ", format_note='" + format_note + '\'' + ", width=" + width + ", height=" + height + ", resolution='" + resolution + '\'' + ", tbr=" + tbr + ", abr=" + abr + ", acodec='" + acodec + '\'' + ", asr=" + asr + ", vbr=" + vbr + ", fps=" + fps + ", vcodec='" + vcodec + '\'' + ", container='" + container + '\'' + ", filesize=" + filesize + ", filesize_approx=" + filesize_approx + ", protocol='" + protocol + '\'' + ", extractor='" + extractor + '\'' + ", extractor_key='" + extractor_key + '\'' + ", epoch=" + epoch + ", autonumber=" + autonumber + ", playlist='" + playlist + '\'' + ", playlist_index=" + playlist_index + ", playlist_id='" + playlist_id + '\'' + ", playlist_title='" + playlist_title + '\'' + ", playlist_uploader='" + playlist_uploader + '\'' + ", playlist_uploader_id='" + playlist_uploader_id + '\'' + ", chapter='" + chapter + '\'' + ", chapter_number=" + chapter_number + ", chapter_id='" + chapter_id + '\'' + ", series='" + series + '\'' + ", season='" + season + '\'' + ", season_number=" + season_number + ", season_id='" + season_id + '\'' + ", episode='" + episode + '\'' + ", episode_number=" + episode_number + ", episode_id='" + episode_id + '\'' + ", track='" + track + '\'' + ", track_number=" + track_number + ", track_id='" + track_id + '\'' + ", artist='" + artist + '\'' + ", genre='" + genre + '\'' + ", album='" + album + '\'' + ", album_type='" + album_type + '\'' + ", album_artist='" + album_artist + '\'' + ", disc_number=" + disc_number + ", release_year=" + release_year + '}';
    }
    
    public static VideoInstanceInfo ofJsonObject(JsonObject jsonObject) {
        final VideoInstanceInfo videoInstanceInfo = new VideoInstanceInfo();
        videoInstanceInfo.setId(jsonObject.has("id") ? jsonObject.get("id").getAsString() : null);
        videoInstanceInfo.setTitle(jsonObject.has("title") ? jsonObject.get("title").getAsString() : null);
        videoInstanceInfo.setUrl(jsonObject.has("url") ? jsonObject.get("url").getAsString() : null);
        videoInstanceInfo.setExt(jsonObject.has("ext") ? jsonObject.get("ext").getAsString() : null);
        videoInstanceInfo.setAlt_title(jsonObject.has("alt_title") ? jsonObject.get("alt_title").getAsString() : null);
        videoInstanceInfo.setDisplay_id(jsonObject.has("display_id") ? jsonObject.get("display_id").getAsString() : null);
        videoInstanceInfo.setUploader(jsonObject.has("uploader") ? jsonObject.get("uploader").getAsString() : null);
        videoInstanceInfo.setLicense(jsonObject.has("license") ? jsonObject.get("license").getAsString() : null);
        videoInstanceInfo.setCreator(jsonObject.has("creator") ? jsonObject.get("creator").getAsString() : null);
        videoInstanceInfo.setRelease_date(jsonObject.has("release_date") ? jsonObject.get("release_date").getAsString() : null);
        videoInstanceInfo.setTimestamp(jsonObject.has("timestamp") ? jsonObject.get("timestamp").getAsLong() : null);
        videoInstanceInfo.setUpload_date(jsonObject.has("upload_date") ? jsonObject.get("upload_date").getAsString() : null);
        videoInstanceInfo.setUploader_id(jsonObject.has("uploader_id") ? jsonObject.get("uploader_id").getAsString() : null);
        videoInstanceInfo.setChannel(jsonObject.has("channel") ? jsonObject.get("channel").getAsString() : null);
        videoInstanceInfo.setChannel_id(jsonObject.has("channel_id") ? jsonObject.get("channel_id").getAsString() : null);
        videoInstanceInfo.setLocation(jsonObject.has("location") ? jsonObject.get("location").getAsString() : null);
        videoInstanceInfo.setDuration(jsonObject.has("duration") ? jsonObject.get("duration").getAsLong() : null);
        videoInstanceInfo.setView_count(jsonObject.has("view_count") ? jsonObject.get("view_count").getAsLong() : null);
        videoInstanceInfo.setLike_count(jsonObject.has("like_count") ? jsonObject.get("like_count").getAsLong() : null);
        videoInstanceInfo.setDislike_count(jsonObject.has("dislike_count") ? jsonObject.get("dislike_count").getAsLong() : null);
        videoInstanceInfo.setRepost_count(jsonObject.has("repost_count") ? jsonObject.get("repost_count").getAsLong() : null);
        videoInstanceInfo.setAverage_rating(jsonObject.has("average_rating") ? jsonObject.get("average_rating").getAsLong() : null);
        videoInstanceInfo.setComment_count(jsonObject.has("comment_count") ? jsonObject.get("comment_count").getAsLong() : null);
        videoInstanceInfo.setAge_limit(jsonObject.has("age_limit") ? jsonObject.get("age_limit").getAsLong() : null);
        videoInstanceInfo.setIs_live(jsonObject.has("is_live") ? jsonObject.get("is_live").getAsBoolean() : null);
        videoInstanceInfo.setStart_time(jsonObject.has("start_time") ? jsonObject.get("start_time").getAsLong() : null);
        videoInstanceInfo.setEnd_time(jsonObject.has("end_time") ? jsonObject.get("end_time").getAsLong() : null);
        videoInstanceInfo.setFormat(jsonObject.has("format") ? jsonObject.get("format").getAsString() : null);
        videoInstanceInfo.setFormat_id(jsonObject.has("format_id") ? jsonObject.get("format_id").getAsString() : null);
        videoInstanceInfo.setFormat_note(jsonObject.has("format_note") ? jsonObject.get("format_note").getAsString() : null);
        videoInstanceInfo.setWidth(jsonObject.has("width") ? jsonObject.get("width").getAsLong() : null);
        videoInstanceInfo.setHeight(jsonObject.has("height") ? jsonObject.get("height").getAsLong() : null);
        videoInstanceInfo.setResolution(jsonObject.has("resolution") ? jsonObject.get("resolution").getAsString() : null);
        videoInstanceInfo.setTbr(jsonObject.has("tbr") ? jsonObject.get("tbr").getAsLong() : null);
        videoInstanceInfo.setAbr(jsonObject.has("abr") ? jsonObject.get("abr").getAsLong() : null);
        videoInstanceInfo.setAcodec(jsonObject.has("acodec") ? jsonObject.get("acodec").getAsString() : null);
        videoInstanceInfo.setAsr(jsonObject.has("asr") ? jsonObject.get("asr").getAsLong() : null);
        videoInstanceInfo.setVbr(jsonObject.has("vbr") ? jsonObject.get("vbr").getAsLong() : null);
        videoInstanceInfo.setFps(jsonObject.has("fps") ? jsonObject.get("fps").getAsLong() : null);
        videoInstanceInfo.setVcodec(jsonObject.has("vcodec") ? jsonObject.get("vcodec").getAsString() : null);
        videoInstanceInfo.setContainer(jsonObject.has("container") ? jsonObject.get("container").getAsString() : null);
        videoInstanceInfo.setFilesize(jsonObject.has("filesize") ? jsonObject.get("filesize").getAsLong() : null);
        videoInstanceInfo.setFilesize_approx(jsonObject.has("filesize_approx") ? jsonObject.get("filesize_approx").getAsLong() : null);
        videoInstanceInfo.setProtocol(jsonObject.has("protocol") ? jsonObject.get("protocol").getAsString() : null);
        videoInstanceInfo.setExtractor(jsonObject.has("extractor") ? jsonObject.get("extractor").getAsString() : null);
        videoInstanceInfo.setExtractor_key(jsonObject.has("extractor_key") ? jsonObject.get("extractor_key").getAsString() : null);
        videoInstanceInfo.setEpoch(jsonObject.has("epoch") ? jsonObject.get("epoch").getAsLong() : null);
        videoInstanceInfo.setAutonumber(jsonObject.has("autonumber") ? jsonObject.get("autonumber").getAsLong() : null);
        videoInstanceInfo.setPlaylist(jsonObject.has("playlist") ? jsonObject.get("playlist").getAsString() : null);
        videoInstanceInfo.setPlaylist_index(jsonObject.has("playlist_index") ? jsonObject.get("playlist_index").getAsLong() : null);
        videoInstanceInfo.setPlaylist_id(jsonObject.has("playlist_id") ? jsonObject.get("playlist_id").getAsString() : null);
        videoInstanceInfo.setPlaylist_title(jsonObject.has("playlist_title") ? jsonObject.get("playlist_title").getAsString() : null);
        videoInstanceInfo.setPlaylist_uploader(jsonObject.has("playlist_uploader") ? jsonObject.get("playlist_uploader").getAsString() : null);
        videoInstanceInfo.setPlaylist_uploader_id(jsonObject.has("playlist_uploader_id") ? jsonObject.get("playlist_uploader_id").getAsString() : null);
        videoInstanceInfo.setChapter(jsonObject.has("chapter") ? jsonObject.get("chapter").getAsString() : null);
        videoInstanceInfo.setChapter_number(jsonObject.has("chapter_number") ? jsonObject.get("chapter_number").getAsLong() : null);
        videoInstanceInfo.setChapter_id(jsonObject.has("chapter_id") ? jsonObject.get("chapter_id").getAsString() : null);
        videoInstanceInfo.setSeries(jsonObject.has("series") ? jsonObject.get("series").getAsString() : null);
        videoInstanceInfo.setSeason(jsonObject.has("season") ? jsonObject.get("season").getAsString() : null);
        videoInstanceInfo.setSeason_number(jsonObject.has("season_number") ? jsonObject.get("season_number").getAsLong() : null);
        videoInstanceInfo.setSeason_id(jsonObject.has("season_id") ? jsonObject.get("season_id").getAsString() : null);
        videoInstanceInfo.setEpisode(jsonObject.has("episode") ? jsonObject.get("episode").getAsString() : null);
        videoInstanceInfo.setEpisode_number(jsonObject.has("episode_number") ? jsonObject.get("episode_number").getAsLong() : null);
        videoInstanceInfo.setEpisode_id(jsonObject.has("episode_id") ? jsonObject.get("episode_id").getAsString() : null);
        videoInstanceInfo.setTrack(jsonObject.has("track") ? jsonObject.get("track").getAsString() : null);
        videoInstanceInfo.setTrack_number(jsonObject.has("track_number") ? jsonObject.get("track_number").getAsLong() : null);
        videoInstanceInfo.setTrack_id(jsonObject.has("track_id") ? jsonObject.get("track_id").getAsString() : null);
        videoInstanceInfo.setArtist(jsonObject.has("artist") ? jsonObject.get("artist").getAsString() : null);
        videoInstanceInfo.setGenre(jsonObject.has("genre") ? jsonObject.get("genre").getAsString() : null);
        videoInstanceInfo.setAlbum(jsonObject.has("album") ? jsonObject.get("album").getAsString() : null);
        videoInstanceInfo.setAlbum_type(jsonObject.has("album_type") ? jsonObject.get("album_type").getAsString() : null);
        videoInstanceInfo.setAlbum_artist(jsonObject.has("album_artist") ? jsonObject.get("album_artist").getAsString() : null);
        videoInstanceInfo.setDisc_number(jsonObject.has("disc_number") ? jsonObject.get("disc_number").getAsLong() : null);
        videoInstanceInfo.setRelease_year(jsonObject.has("release_year") ? jsonObject.get("release_year").getAsLong() : null);
        return videoInstanceInfo;
    }
    
    public static JsonObject outputInfoToJsonObject(String outputInfo) {
        if (outputInfo == null) {
            return null;
        }
        return Misc.GSON.fromJson(outputInfo.replaceAll("(?:\\{\\{\\{###\\{\\{\\{)|(?:\\}\\}\\}###\\}\\}\\})", "\"").replaceAll("=", ":"), JsonObject.class);
    }
    
    public static VideoInstanceInfo outputInfoToVideoInstanceInfo(String outputInfo) {
        final JsonObject jsonObject = outputInfoToJsonObject(outputInfo);
        if (jsonObject == null) {
            return null;
        }
        return ofJsonObject(jsonObject);
    }
    
}
