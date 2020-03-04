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

import de.codemakers.base.Standard;
import de.codemakers.base.logger.Logger;
import de.codemakers.base.multiplets.Doublet;
import de.codemakers.base.util.TimeUtil;
import de.codemakers.base.util.tough.ToughFunction;
import de.codemakers.base.util.tough.ToughSupplier;
import de.codemakers.download.database.YouTubeDatabase;
import de.codemakers.download.entities.AbstractDownloadContainer;
import de.codemakers.download.entities.DownloadSettings;
import de.codemakers.download.entities.VideoInstanceInfo;
import de.codemakers.download.entities.impl.YouTubeDownloadContainer;
import de.codemakers.download.sources.Source;
import de.codemakers.download.sources.YouTubeSource;
import de.codemakers.download.util.Misc;
import de.codemakers.io.file.AdvancedFile;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class YouTubeDL {
    
    // // YouTube URL Pattern
    // All YouTube URL Pattern
    public static final String PATTERN_YOUTUBE_URL_STRING = "(?:http(?:s)?:\\/\\/)?(?:www.)?(?:youtube\\.com\\/(?:channel\\/|playlist\\?list=)([a-zA-Z0-9_-]+)|(?:youtu\\.be\\/|youtube\\.com(?:\\/embed\\/|\\/v\\/|\\/watch\\?v=|\\/ytscreeningroom\\?v=|\\/feeds\\/api\\/videos\\/|\\/user\\\\S*[^\\w\\-\\s]|\\S*[^\\w\\-\\s]))([\\w\\-\\_]{11}))[a-z0-9;:@#?&%=+\\/\\$_.-]*";
    /**
     * Group 1: Maybe Playlist ID
     * Group 2: Maybe Video ID
     */
    public static final Pattern PATTERN_YOUTUBE_URL = Pattern.compile(PATTERN_YOUTUBE_URL_STRING);
    // YouTube Video URL Pattern
    public static final String PATTERN_YOUTUBE_VIDEO_URL_STRING = "(?:http(?:s)?:\\/\\/)?(?:www.)?(?:youtu\\.be\\/|youtube\\.com(?:\\/embed\\/|\\/v\\/|\\/watch\\?v=|\\/ytscreeningroom\\?v=|\\/feeds\\/api\\/videos\\/|\\/user\\\\S*[^\\w\\-\\s]|\\S*[^\\w\\-\\s]))([\\w\\-\\_]{11})[a-z0-9;:@#?&%=+\\/\\$_.-]*";
    /**
     * Group 1: Video ID
     */
    public static final Pattern PATTERN_YOUTUBE_VIDEO_URL = Pattern.compile(PATTERN_YOUTUBE_VIDEO_URL_STRING);
    // YouTube Playlist URL Pattern
    public static final String PATTERN_YOUTUBE_PLAYLIST_URL_STRING = "(?:http|https|)(?::\\/\\/|)(?:www.|)youtube.com\\/playlist\\?list=([a-zA-Z0-9_-]+)[a-z0-9;:@#?&%=+\\/\\$_.-]*";
    /**
     * Group 1: Playlist ID
     */
    public static final Pattern PATTERN_YOUTUBE_PLAYLIST_URL = Pattern.compile(PATTERN_YOUTUBE_PLAYLIST_URL_STRING);
    // YouTube Video in Playlist URL Pattern
    public static final String PATTERN_YOUTUBE_VIDEO_IN_PLAYLIST_URL_STRING = "(?:http|https|)(?::\\/\\/|)(?:www.|)youtube.com\\/watch\\?v=([\\w\\-\\_]{11})&list=([a-zA-Z0-9_-]+)(?:&index=(\\d+))?(?:&t=(\\d+)s)?[a-z0-9;:@#?&%=+\\/\\$_.-]*";
    /**
     * Group 1: Video ID
     * Group 2: Playlist ID
     */
    public static final Pattern PATTERN_YOUTUBE_VIDEO_IN_PLAYLIST_URL = Pattern.compile(PATTERN_YOUTUBE_VIDEO_IN_PLAYLIST_URL_STRING);
    // YouTube User URL Pattern
    public static final String PATTERN_YOUTUBE_USER_URL_STRING = "(?:http|https|)(?::\\/\\/|)(?:www.|)youtube.com\\/user\\/([a-zA-Z0-9]+)[a-z0-9;:@#?&%=+\\/\\$_.-]*";
    /**
     * Group 1: Username
     */
    public static final Pattern PATTERN_YOUTUBE_USER_URL = Pattern.compile(PATTERN_YOUTUBE_USER_URL_STRING);
    // YouTube Channel URL Pattern
    public static final String PATTERN_YOUTUBE_CHANNEL_URL_STRING = "(?:http|https|)(?::\\/\\/|)(?:www.|)youtube.com\\/channel\\/([a-zA-Z0-9_-]+)[a-z0-9;:@#?&%=+\\/\\$_.-]*";
    /**
     * Group 1: Channel ID
     */
    public static final Pattern PATTERN_YOUTUBE_CHANNEL_URL = Pattern.compile(PATTERN_YOUTUBE_CHANNEL_URL_STRING);
    // //
    
    // Intern
    public static final AdvancedFile INTERN_FOLDER = new AdvancedFile(Standard.MAIN_FOLDER, "download");
    // Program Name
    public static final String DEFAULT_PROGRAM_NAME = "youtube-dl";
    private static String PROGRAM_NAME = DEFAULT_PROGRAM_NAME;
    // Config File
    public static final String DEFAULT_CONFIG_FILE_NAME = "youtube-dl.conf";
    public static final AdvancedFile DEFAULT_CONFIG_FILE = new AdvancedFile(DEFAULT_CONFIG_FILE_NAME);
    private static AdvancedFile CONFIG_FILE = DEFAULT_CONFIG_FILE;
    // Output Directory
    private static final AdvancedFile DEFAULT_OUTPUT_DIRECTORY = new AdvancedFile();
    private static AdvancedFile OUTPUT_DIRECTORY = DEFAULT_OUTPUT_DIRECTORY;
    // Single Log
    public static final String DEFAULT_LOG_FILE_NAME = "log.txt";
    public static final AdvancedFile DEFAULT_LOG_FILE = new AdvancedFile(DEFAULT_LOG_FILE_NAME);
    private static AdvancedFile LOG_FILE = DEFAULT_LOG_FILE;
    // Multiple Logs
    public static final String DEFAULT_LOG_FILE_NAME_TEMPLATE = "log_%s_%s.txt";
    private static String LOG_FILE_NAME_TEMPLATE = DEFAULT_LOG_FILE_NAME_TEMPLATE;
    public static final String DEFAULT_LOGS_DIRECTORY_NAME = "logs";
    public static final AdvancedFile DEFAULT_LOGS_DIRECTORY = new AdvancedFile(DEFAULT_LOGS_DIRECTORY_NAME);
    private static AdvancedFile LOGS_DIRECTORY = DEFAULT_LOGS_DIRECTORY;
    // // Arguments
    // Source: https://github.com/ytdl-org/youtube-dl/
    // Options
    /**
     * Print this help text and exit
     */
    public static final String ARGUMENT_HELP = "--help";
    /**
     * Print program version and exit
     */
    public static final String ARGUMENT_VERSION = "--version";
    /**
     * Update this program to latest version. Make
     * sure that you have sufficient permissions
     * (run with sudo if needed)
     */
    public static final String ARGUMENT_UPDATE = "--update";
    /**
     * Continue on download errors, for example to
     * skip unavailable videos in a playlist
     */
    public static final String ARGUMENT_IGNORE_ERRORS = "--ignore-errors";
    /**
     * Abort downloading of further videos (in the
     * playlist or the command line) if an error
     * occurs
     */
    public static final String ARGUMENT_ABORT_ON_ERROR = "--abort-on-error";
    /**
     * Display the current browser identification
     */
    public static final String ARGUMENT_DUMP_USER_AGENT = "--dump-user-agent";
    /**
     * List all supported extractors
     */
    public static final String ARGUMENT_LIST_EXTRACTORS = "--list-extractors";
    /**
     * Output descriptions of all supported
     * extractors
     */
    public static final String ARGUMENT_EXTRACTOR_DESCRIPTIONS = "--extractor-descriptions";
    /**
     * Force extraction to use the generic
     * extractor
     */
    public static final String ARGUMENT_FORCE_GENERIC_EXTRACTOR = "--force-generic-extractor";
    /**
     * Use this prefix for unqualified URLs. For
     * example "gvsearch2:" downloads two videos
     * from google videos for youtube-dl "large
     * apple". Use the value "auto" to let
     * youtube-dl guess ("auto_warning" to emit a
     * warning when guessing). "error" just throws
     * an error. The default value "fixup_error"
     * repairs broken URLs, but emits an error if
     * this is not possible instead of searching.
     * <p>
     * Needs one sub argument: PREFIX
     */
    public static final String ARGUMENT_DEFAULT_SEARCH = "--default-search";
    /**
     * Do not read configuration files. When given
     * in the global configuration file
     * /etc/youtube-dl.conf: Do not read the user
     * configuration in ~/.config/youtube-
     * dl/config (%APPDATA%/youtube-dl/config.txt
     * on Windows)
     */
    public static final String ARGUMENT_IGNORE_CONFIG = "--ignore-config";
    /**
     * Location of the configuration file; either
     * the path to the config or its containing
     * directory.
     * <p>
     * Needs one sub argument: PATH
     */
    public static final String ARGUMENT_CONFIG_LOCATION = "--config-location";
    /**
     * Do not extract the videos of a playlist,
     * only list them.
     */
    public static final String ARGUMENT_FLAT_PLAYLIST = "--flat-playlist";
    /**
     * Mark videos watched (YouTube only)
     */
    public static final String ARGUMENT_MART_WATCHED = "--mark-watched";
    /**
     * Do not mark videos watched (YouTube only)
     */
    public static final String ARGUMENT_NO_MARK_WATCHED = "--no-mark-watched";
    /**
     * Do not emit color codes in output
     */
    public static final String ARGUMENT_NO_COLOR = "--no-color";
    // Network Options
    /**
     * Use the specified HTTP/HTTPS/SOCKS proxy.
     * To enable SOCKS proxy, specify a proper
     * scheme. For example
     * socks5://127.0.0.1:1080/. Pass in an empty
     * string (--proxy "") for direct connection
     * <p>
     * Needs one sub argument: URL
     */
    public static final String ARGUMENT_PROXY = "--proxy";
    /**
     * Time to wait before giving up, in seconds
     * <p>
     * Needs one sub argument: SECONDS
     */
    public static final String ARGUMENT_SOCKET_TIMEOUT = "--socket-timeout";
    /**
     * Client-side IP address to bind to
     * <p>
     * Needs one sub argument: IP
     */
    public static final String ARGUMENT_SOURCE_ADDRESS = "--source-address";
    /**
     * Make all connections via IPv4
     */
    public static final String ARGUMENT_FORCE_IPv4 = "--force-ipv4";
    /**
     * Make all connections via IPv6
     */
    public static final String ARGUMENT_FORCE_IPv6 = "--force-ipv6";
    // Geo Restriction
    /**
     * Use this proxy to verify the IP address for
     * some geo-restricted sites. The default
     * proxy specified by --proxy (or none, if the
     * option is not present) is used for the
     * actual downloading.
     * <p>
     * Needs one sub argument: URL
     */
    public static final String ARGUMENT_GEO_VERIFICATION_PROXY = "--geo-verification-proxy";
    /**
     * Bypass geographic restriction via faking
     * X-Forwarded-For HTTP header
     */
    public static final String ARGUMENT_GEO_BYPASS = "--geo-bypass";
    /**
     * Do not bypass geographic restriction via
     * faking X-Forwarded-For HTTP header
     */
    public static final String ARGUMENT_NO_GEO_BYPASS = "--no-geo-bypass";
    /**
     * Force bypass geographic restriction with
     * explicitly provided two-letter ISO 3166-2
     * country code
     * <p>
     * Needs one sub argument: CODE
     */
    public static final String ARGUMENT_GEO_BYPASS_COUNTRY = "--geo-bypass-country";
    /**
     * Force bypass geographic restriction with
     * explicitly provided IP block in CIDR
     * notation
     * <p>
     * Needs one sub argument: IP_BLOCK
     */
    public static final String ARGUMENT_GEO_BYPASS_IP_BLOCK = "--geo-bypass-ip-block";
    // Video selection
    /**
     * Playlist video to start at (default is 1)
     * <p>
     * Needs one sub argument: NUMBER
     */
    public static final String ARGUMENT_PLAYLIST_START = "--playlist-start";
    /**
     * Playlist video to end at (default is last)
     * <p>
     * Needs one sub argument: NUMBER
     */
    public static final String ARGUMENT_PLAYLIST_END = "--playlist-end";
    /**
     * Playlist video items to download. Specify
     * indices of the videos in the playlist
     * separated by commas like: "--playlist-items
     * 1,2,5,8" if you want to download videos
     * indexed 1, 2, 5, 8 in the playlist. You can
     * specify range: "--playlist-items
     * 1-3,7,10-13", it will download the videos
     * at index 1, 2, 3, 7, 10, 11, 12 and 13.
     * <p>
     * Needs one sub argument: ITEM_SPEC
     */
    public static final String ARGUMENT_PLAYLIST_ITEMS = "--playlist-items";
    /**
     * Download only matching titles (regex or
     * caseless sub-string)
     * <p>
     * Needs one sub argument: REGEX
     */
    public static final String ARGUMENT_MATCH_TITLE = "--match-title";
    /**
     * Skip download for matching titles (regex or
     * caseless sub-string)
     * <p>
     * Needs one sub argument: REGEX
     */
    public static final String ARGUMENT_REJECT_TITLE = "--reject-title";
    /**
     * Abort after downloading NUMBER files
     * <p>
     * Needs one sub argument: NUMBER
     */
    public static final String ARGUMENT_MAX_DOWNLOADS = "--max-downloads";
    /**
     * Do not download any videos smaller than
     * SIZE (e.g. 50k or 44.6m)
     * <p>
     * Needs one sub argument: SIZE
     */
    public static final String ARGUMENT_MIN_FILESIZE = "--min-filesize";
    /**
     * Do not download any videos larger than SIZE
     * (e.g. 50k or 44.6m)
     * <p>
     * Needs one sub argument: SIZE
     */
    public static final String ARGUMENT_MAX_FILESIZE = "--max-filesize";
    /**
     * Download only videos uploaded in this date
     * <p>
     * Needs one sub argument: DATE
     */
    public static final String ARGUMENT_DATE = "--date";
    /**
     * Download only videos uploaded on or before
     * this date (i.e. inclusive)
     * <p>
     * Needs one sub argument: DATE
     */
    public static final String ARGUMENT_DATEBEFORE = "--datebefore";
    /**
     * Download only videos uploaded on or after
     * this date (i.e. inclusive)
     * <p>
     * Needs one sub argument: DATE
     */
    public static final String ARGUMENT_DATEAFTER = "--dateafter";
    /**
     * Do not download any videos with less than
     * COUNT views
     * <p>
     * Needs one sub argument: COUNT
     */
    public static final String ARGUMENT_MIN_VIEWS = "--min-views";
    /**
     * Do not download any videos with more than
     * COUNT views
     * <p>
     * Needs one sub argument: COUNT
     */
    public static final String ARGUMENT_MAX_VIEWS = "--max-views";
    /**
     * Generic video filter. Specify any key (see
     * the "OUTPUT TEMPLATE" for a list of
     * available keys) to match if the key is
     * present, !key to check if the key is not
     * present, key > NUMBER (like "comment_count
     * > 12", also works with >=, <, <=, !=, =) to
     * compare against a number, key = 'LITERAL'
     * (like "uploader = 'Mike Smith'", also works
     * with !=) to match against a string literal
     * and & to require multiple matches. Values
     * which are not known are excluded unless you
     * put a question mark (?) after the operator.
     * For example, to only match videos that have
     * been liked more than 100 times and disliked
     * less than 50 times (or the dislike
     * functionality is not available at the given
     * service), but who also have a description,
     * use --match-filter "like_count > 100 &
     * dislike_count <? 50 & description" .
     * <p>
     * Needs one sub argument: FILTER
     */
    public static final String ARGUMENT_MATCH_FILTER = "--match-filter";
    /**
     * Download only the video, if the URL refers
     * to a video and a playlist.
     */
    public static final String ARGUMENT_NO_PLAYLIST = "--no-playlist";
    /**
     * Download the playlist, if the URL refers to
     * a video and a playlist.
     */
    public static final String ARGUMENT_YES_PLAYLIST = "--yes-playlist";
    /**
     * Download only videos suitable for the given
     * age
     * <p>
     * Needs one sub argument: YEARS
     */
    public static final String ARGUMENT_AGE_LIMIT = "--age-limit";
    /**
     * Download only videos not listed in the
     * archive file. Record the IDs of all
     * downloaded videos in it.
     * <p>
     * Needs one sub argument: FILE
     */
    public static final String ARGUMENT_DOWNLOAD_ARCHIVE = "--download-archive";
    /**
     * Download advertisements as well
     * (experimental)
     */
    public static final String ARGUMENT_INCLUDE_ADS = "--include-ads";
    // Download Options
    /**
     * Maximum download rate in bytes per second
     * (e.g. 50K or 4.2M)
     * <p>
     * Needs one sub argument: RATE
     */
    public static final String ARGUMENT_LIMIT_RATE = "--limit-rate";
    /**
     * Number of retries (default is 10), or
     * "infinite".
     * <p>
     * Needs one sub argument: RETRIES
     */
    public static final String ARGUMENT_RETRIES = "--retries";
    /**
     * Number of retries for a fragment (default
     * is 10), or "infinite" (DASH, hlsnative and
     * ISM)
     * <p>
     * Needs one sub argument: RETRIES
     */
    public static final String ARGUMENT_FRAGMENT_RETRIES = "--fragment-retries";
    /**
     * Skip unavailable fragments (DASH, hlsnative
     * and ISM)
     */
    public static final String ARGUMENT_SKIP_UNAVAILABLE_FRAGMENTS = "--skip-unavailable-fragments";
    /**
     * Abort downloading when some fragment is not
     * available
     */
    public static final String ARGUMENT_ABORT_ON_UNAVAILABLE_FRAGMENTS = "--abort-on-unavailable-fragments";
    /**
     * Keep downloaded fragments on disk after
     * downloading is finished; fragments are
     * erased by default
     */
    public static final String ARGUMENT_KEEP_FRAGMENTS = "--keep-fragments";
    /**
     * Size of download buffer (e.g. 1024 or 16K)
     * (default is 1024)
     * <p>
     * Needs one sub argument: SIZE
     */
    public static final String ARGUMENT_BUFFER_SIZE = "--buffer-size";
    /**
     * Do not automatically adjust the buffer
     * size. By default, the buffer size is
     * automatically resized from an initial value
     * of SIZE.
     */
    public static final String ARGUMENT_NO_RESIZE_BUFFER = "--no-resize-buffer";
    /**
     * Size of a chunk for chunk-based HTTP
     * downloading (e.g. 10485760 or 10M) (default
     * is disabled). May be useful for bypassing
     * bandwidth throttling imposed by a webserver
     * (experimental)
     * <p>
     * Needs one sub argument: SIZE
     */
    public static final String ARGUMENT_HTTP_CHUNK_SIZE = "--http-chunk-size";
    /**
     * Download playlist videos in reverse order
     */
    public static final String ARGUMENT_PLAYLIST_REVERSE = "--playlist-reverse";
    /**
     * Download playlist videos in random order
     */
    public static final String ARGUMENT_PLAYLIST_RANDOM = "--playlist-random";
    /**
     * Set file xattribute ytdl.filesize with
     * expected file size
     */
    public static final String ARGUMENT_XATTR_SET_FILESIZE = "--xattr-set-filesize";
    /**
     * Use the native HLS downloader instead of
     * ffmpeg
     */
    public static final String ARGUMENT_HLS_PREFER_NATIVE = "--hls-prefer-native";
    /**
     * Use ffmpeg instead of the native HLS
     * downloader
     */
    public static final String ARGUMENT_HLS_PREFER_FFMPEG = "--hls-prefer-ffmpeg";
    /**
     * Use the mpegts container for HLS videos,
     * allowing to play the video while
     * downloading (some players may not be able
     * to play it)
     */
    public static final String ARGUMENT_HLS_USE_MPEGTS = "--hls-use-mpegts";
    /**
     * Use the specified external downloader.
     * Currently supports
     * <br>
     * aria2c,avconv,axel,curl,ffmpeg,httpie,wget
     * <p>
     * Needs one sub argument: COMMAND
     */
    public static final String ARGUMENT_EXTERNAL_DOWNLOADER = "--external-downloader";
    /**
     * Give these arguments to the external
     * downloader
     * <p>
     * Needs one sub argument: ARGS
     */
    public static final String ARGUMENT_EXTERNAL_DOWNLOADER_ARGS = "--external-downloader-args";
    // Filesystem Options
    /**
     * File containing URLs to download ('-' for
     * stdin), one URL per line. Lines starting
     * with '#', ';' or ']' are considered as
     * comments and ignored.
     * <p>
     * Needs one sub argument: FILE
     */
    public static final String ARGUMENT_BATCH_FILE = "--batch-file";
    /**
     * Use only video ID in file name
     */
    public static final String ARGUMENT_ID = "--id";
    /**
     * Output filename template, see the "OUTPUT
     * TEMPLATE" for all the info
     * <p>
     * Needs one sub argument: TEMPLATE
     */
    public static final String ARGUMENT_OUTPUT = "--output";
    /**
     * Specify the start value for %(autonumber)s
     * (default is 1)
     * <p>
     * Needs one sub argument: NUMBER
     */
    public static final String ARGUMENT_AUTONUMBER_START = "--autonumber-start";
    /**
     * Restrict filenames to only ASCII
     * characters, and avoid "&" and spaces in
     * filenames
     */
    public static final String ARGUMENT_RESTRICT_FILENAMES = "--restrict-filenames";
    /**
     * Do not overwrite files
     */
    public static final String ARGUMENT_NO_OVERWRITES = "--no-overwrites";
    /**
     * Force resume of partially downloaded files.
     * By default, youtube-dl will resume
     * downloads if possible.
     */
    public static final String ARGUMENT_CONTINUE = "--continue";
    /**
     * Do not resume partially downloaded files
     * (restart from beginning)
     */
    public static final String ARGUMENT_NO_CONTINUE = "--no-continue";
    /**
     * Do not use .part files - write directly
     * into output file
     */
    public static final String ARGUMENT_NO_PART = "--no-part";
    /**
     * Do not use the Last-modified header to set
     * the file modification time
     */
    public static final String ARGUMENT_NO_MTIME = "--no-mtime";
    /**
     * Write video description to a .description
     * file
     */
    public static final String ARGUMENT_WRITE_DESCRIPTION = "--write-description";
    /**
     * Write video metadata to a .info.json file
     */
    public static final String ARGUMENT_WRITE_INFO_JSON = "--write-info-json";
    /**
     * Write video annotations to a
     * .annotations.xml file
     */
    public static final String ARGUMENT_WRITE_ANNOTATIONS = "--write-annotations";
    /**
     * JSON file containing the video information
     * (created with the "--write-info-json"
     * option)
     * <p>
     * Needs one sub argument: FILE
     */
    public static final String ARGUMENT_LOAD_INFO_JSON = "--load-info-json";
    /**
     * File to read cookies from and dump cookie
     * jar in
     * <p>
     * Needs one sub argument: FILE
     */
    public static final String ARGUMENT_COOKIES = "--cookies";
    /**
     * Location in the filesystem where youtube-dl
     * can store some downloaded information
     * permanently. By default
     * $XDG_CACHE_HOME/youtube-dl or
     * ~/.cache/youtube-dl . At the moment, only
     * YouTube player files (for videos with
     * obfuscated signatures) are cached, but that
     * may change.
     * <p>
     * Needs one sub argument: DIR
     */
    public static final String ARGUMENT_CACHE_DIR = "--cache-dir";
    /**
     * Disable filesystem caching
     */
    public static final String ARGUMENT_NO_CACHE_DIR = "--no-cache-dir";
    /**
     * Delete all filesystem cache files
     */
    public static final String ARGUMENT_RM_CACHE_DIR = "--rm-cache-dir";
    // Thumbnail images
    /**
     * Write thumbnail image to disk
     */
    public static final String ARGUMENT_WRITE_THUMBNAIL = "--write-thumbnail";
    /**
     * Write all thumbnail image formats to disk
     */
    public static final String ARGUMENT_WRITE_ALL_THUMBNAILS = "--write-all-thumbnails";
    /**
     * Simulate and list all available thumbnail
     * formats
     */
    public static final String ARGUMENT_LIST_THUMBNAIL = "--list-thumnnails";
    // Verbosity / Simulation Options
    /**
     * Activate quiet mode
     */
    public static final String ARGUMENT_QUIET = "--quiet";
    /**
     * Ignore warnings
     */
    public static final String ARGUMENT_NO_WARNINGS = "--no-warnings";
    /**
     * Do not download the video and do not write
     * anything to disk
     */
    public static final String ARGUMENT_SIMULATE = "--simulate";
    /**
     * Do not download the video
     */
    public static final String ARGUMENT_SKIP_DOWNLOAD = "--skip-download";
    /**
     * Simulate, quiet but print URL
     */
    public static final String ARGUMENT_GET_URL = "--get-url";
    /**
     * Simulate, quiet but print title
     */
    public static final String ARGUMENT_GET_TITLE = "--get-title";
    /**
     * Simulate, quiet but print id
     */
    public static final String ARGUMENT_GET_ID = "--get-id";
    /**
     * Simulate, quiet but print thumbnail URL
     */
    public static final String ARGUMENT_GET_THUMBNAIL = "--get-thumbnail";
    /**
     * Simulate, quiet but print video description
     */
    public static final String ARGUMENT_GET_DESCRIPTION = "--get-description";
    /**
     * Simulate, quiet but print video length
     */
    public static final String ARGUMENT_GET_DURATION = "--get-duration";
    /**
     * Simulate, quiet but print output filename
     */
    public static final String ARGUMENT_GET_FILENAME = "--get-filename";
    /**
     * Simulate, quiet but print output format
     */
    public static final String ARGUMENT_GET_FORMAT = "--get-format";
    /**
     * Simulate, quiet but print JSON information.
     * See the "OUTPUT TEMPLATE" for a description
     * of available keys.
     */
    public static final String ARGUMENT_DUMP_JSON = "--dump-json";
    /**
     * Simulate, quiet but print JSON information
     * for each command-line argument. If the URL
     * refers to a playlist, dump the whole
     * playlist information in a single line.
     */
    public static final String ARGUMENT_DUMP_SINGLE_JSON = "--dump-single-json";
    /**
     * Be quiet and print the video information as
     * JSON (video is still being downloaded).
     */
    public static final String ARGUMENT_PRINT_JSON = "--print-json";
    /**
     * Output progress bar as new lines
     */
    public static final String ARGUMENT_NEWLINE = "--newline";
    /**
     * Do not print progress bar
     */
    public static final String ARGUMENT_NO_PROGRESS = "--no-progress";
    /**
     * Display progress in console titlebar
     */
    public static final String ARGUMENT_CONSOLE_TITLE = "--console-title";
    /**
     * Print various debugging information
     */
    public static final String ARGUMENT_VERBOSE = "--verbose";
    /**
     * Print downloaded pages encoded using base64
     * to debu g problems (very verbose)
     */
    public static final String ARGUMENT_DUMP_PAGES = "--dump-pages";
    /**
     * Write downloaded intermediary pages to
     * files in the current directory to debu g
     * problems
     */
    public static final String ARGUMENT_WRITE_PAGES = "--write-pages";
    /**
     * Display sent and read HTTP traffic
     */
    public static final String ARGUMENT_PRINT_TRAFFIC = "--print-traffic";
    /**
     * Contact the youtube-dl server for debugging
     */
    public static final String ARGUMENT_CALL_HOME = "--call-home";
    /**
     * Do NOT contact the youtube-dl server for
     * debugging
     */
    public static final String ARGUMENT_NO_CALL_HOME = "--no-call-home";
    // Workarounds
    /**
     * Force the specified encoding (experimental)
     * <p>
     * Needs one sub argument: ENCODING
     */
    public static final String ARGUMENT_ENCODING = "--encoding";
    /**
     * Suppress HTTPS certificate validation
     */
    public static final String ARGUMENT_NO_CHECK_CERTIFICATE = "--no-check-certificate";
    /**
     * Use an unencrypted connection to retrieve
     * information about the video. (Currently
     * supported only for YouTube)
     */
    public static final String ARGUMENT_PREFER_INSECURE = "--prefer-insecure";
    /**
     * Specify a custom user agent
     * <p>
     * Needs one sub argument: UA
     */
    public static final String ARGUMENT_USER_AGENT = "--user-agent";
    /**
     * Specify a custom referer, use if the video
     * access is restricted to one domain
     * <p>
     * Needs one sub argument: URL
     */
    public static final String ARGUMENT_REFERER = "--referer";
    /**
     * Specify a custom HTTP header and its value,
     * separated by a colon ':'. You can use this
     * option multiple times
     * <p>
     * Needs two sub arguments: FIELD:VALUE
     */
    public static final String ARGUMENT_ADD_HEADER = "--add-header";
    /**
     * Work around terminals that lack
     * bidirectional text support. Requires bidiv
     * or fribidi executable in PATH
     */
    public static final String ARGUMENT_BIDI_WORKAROUND = "--bidi-workaround";
    /**
     * Number of seconds to sleep before each
     * download when used alone or a lower bound
     * of a range for randomized sleep before each
     * download (minimum possible number of
     * seconds to sleep) when used along with
     * --max-sleep-interval.
     * <p>
     * Needs one sub argument: SECONDS
     */
    public static final String ARGUMENT_SLEEP_INTERVAL = "--sleep-interval";
    /**
     * Upper bound of a range for randomized sleep
     * before each download (maximum possible
     * number of seconds to sleep). Must only be
     * used along with --[min-]sleep-interval.
     * <p>
     * Needs one sub argument: SECONDS
     */
    public static final String ARGUMENT_MAX_SLEEP_INTERVAL = "--max-sleep-interval";
    // Video Format Options
    /**
     * Video format code, see the "FORMAT
     * SELECTION" for all the info
     * <p>
     * Needs one sub argument: FORMAT
     */
    public static final String ARGUMENT_FORMAT = "--format";
    /**
     * Download all available video formats
     */
    public static final String ARGUMENT_ALL_FORMATS = "--all-formats";
    /**
     * Prefer free video formats unless a specific
     * one is requested
     */
    public static final String ARGUMENT_PREFER_FREE_FORMATS = "--prefer-free-formats";
    /**
     * List all available formats of requested
     * videos
     */
    public static final String ARGUMENT_LIST_FORMATS = "--list-formats";
    /**
     * Do not download the DASH manifests and
     * related data on YouTube videos
     */
    public static final String ARGUMENT_YOUTUBE_SKIP_DASH_MANIFEST = "--youtube-skip-dash-manifest";
    /**
     * If a merge is required (e.g.
     * bestvideo+bestaudio), output to given
     * container format. One of mkv, mp4, ogg,
     * webm, flv. Ignored if no merge is required
     * <p>
     * Needs one sub argument: FORMAT
     */
    public static final String ARGUMENT_MERGE_OUTPUT_FORMAT = "--merge-output-format";
    // Subtitle Options
    /**
     * Write subtitle file
     */
    public static final String ARGUMENT_WRITE_SUB = "--write-sub";
    /**
     * Write automatically generated subtitle file
     * (YouTube only)
     */
    public static final String ARGUMENT_WRITE_AUTO_SUB = "--write-auto-sub";
    /**
     * Download all the available subtitles of the
     * video
     */
    public static final String ARGUMENT_ALL_SUBS = "--all-subs";
    /**
     * List all available subtitles for the video
     */
    public static final String ARGUMENT_LIST_SUBS = "--list-subs";
    /**
     * Subtitle format, accepts formats
     * preference, for example: "srt" or
     * "ass/srt/best"
     * <p>
     * Needs one sub argument: FORMAT
     */
    public static final String ARGUMENT_SUB_FORMAT = "--sub-format";
    /**
     * Languages of the subtitles to download
     * (optional) separated by commas, use --list-
     * subs for available language tags
     * <p>
     * Needs one sub argument: LANGS
     */
    public static final String ARGUMENT_SUB_LANG = "--sub-lang";
    // Authentication Options
    /**
     * Login with this account ID
     * <p>
     * Needs one sub argument: USERNAME
     */
    public static final String ARGUMENT_USERNAME = "--username";
    /**
     * Account password. If this option is left
     * out, youtube-dl will ask interactively.
     * <p>
     * Needs one sub argument: PASSWORD
     */
    public static final String ARGUMENT_PASSWORD = "--password";
    /**
     * Two-factor authentication code
     * <p>
     * Needs one sub argument: TWOFACTOR
     */
    public static final String ARGUMENT_TWOFACTOR = "--twofactor";
    /**
     * Use .netrc authentication data
     */
    public static final String ARGUMENT_NETRC = "--netrc";
    /**
     * Video password (vimeo, smotri, youku)
     * <p>
     * Needs one sub argument: PASSWORD
     */
    public static final String ARGUMENT_VIDEO_PASSWORD = "--video-password";
    // Adobe Pass Options
    /**
     * Adobe Pass multiple-system operator (TV
     * provider) identifier, use --ap-list-mso for
     * a list of available MSOs
     * <p>
     * Needs one sub argument: MSO
     */
    public static final String ARGUMENT_AP_MSO = "--ap-mso";
    /**
     * Multiple-system operator account login
     * <p>
     * Needs one sub argument: USERNAME
     */
    public static final String ARGUMENT_AP_USERNAME = "--ap-username";
    /**
     * Multiple-system operator account password.
     * If this option is left out, youtube-dl will
     * ask interactively.
     * <p>
     * Needs one sub argument: PASSWORD
     */
    public static final String ARGUMENT_AP_PASSWORD = "--ap-password";
    /**
     * List all supported multiple-system
     * operators
     */
    public static final String ARGUMENT_AP_LIST_MSO = "--ap-list-mso";
    // Post-processing Options
    /**
     * Convert video files to audio-only files
     * (requires ffmpeg or avconv and ffprobe or
     * avprobe)
     */
    public static final String ARGUMENT_EXTRACT_AUDIO = "--extract-audio";
    /**
     * Specify audio format: "best", "aac",
     * "flac", "mp3", "m4a", "opus", "vorbis", or
     * "wav"; "best" by default; No effect without
     * -x
     * <p>
     * Needs one sub argument: FORMAT
     */
    public static final String ARGUMENT_AUDIO_FORMAT = "--audio-format";
    /**
     * Specify ffmpeg/avconv audio quality, insert
     * a value between 0 (better) and 9 (worse)
     * for VBR or a specific bitrate like 128K
     * (default 5)
     * <p>
     * Needs one sub argument: QUALITY
     */
    public static final String ARGUMENT_AUDIO_QUALITY = "--audio-quality";
    /**
     * Encode the video to another format if
     * necessary (currently supported:
     * <br>
     * mp4|flv|ogg|webm|mkv|avi)
     * <p>
     * Needs one sub argument: FORMAT
     */
    public static final String ARGUMENT_RECODE_VIDEO = "--recode-video";
    /**
     * Give these arguments to the postprocessor
     * <p>
     * Needs one sub argument: ARGS
     */
    public static final String ARGUMENT_POSTPROCESSOR_ARGS = "--postprocessor-args";
    /**
     * Keep the video file on disk after the post-
     * processing; the video is erased by default
     */
    public static final String ARGUMENT_KEEP_VIDEO = "--keep-video";
    /**
     * Do not overwrite post-processed files; the
     * post-processed files are overwritten by
     * default
     */
    public static final String ARGUMENT_NO_POST_OVERWRITES = "--no-post-overwrites";
    /**
     * Embed subtitles in the video (only for mp4,
     * webm and mkv videos)
     */
    public static final String ARGUMENT_EMBED_SUBS = "--embed-subs";
    /**
     * Embed thumbnail in the audio as cover art
     */
    public static final String ARGUMENT_EMBED_THUMBNAIL = "--embed-thumbnail";
    /**
     * Write metadata to the video file
     */
    public static final String ARGUMENT_ADD_METADATA = "--add-metadata";
    /**
     * Parse additional metadata like song title /
     * artist from the video title. The format
     * syntax is the same as --output. Regular
     * expression with named capture groups may
     * also be used. The parsed parameters replace
     * existing values. Example: --metadata-from-
     * title "%(artist)s - %(title)s" matches a
     * title like "Coldplay - Paradise". Example
     * (regex): --metadata-from-title
     * "(?P<artist>.+?) - (?P<title>.+)"
     * <p>
     * Needs one sub argument: FORMAT
     */
    public static final String ARGUMENT_METADATA_FROM_TITLE = "--metadata-from-title";
    /**
     * Write metadata to the video file's xattrs
     * (using dublin core and xdg standards)
     */
    public static final String ARGUMENT_XATTRS = "--xattrs";
    /**
     * Automatically correct known faults of the
     * file. One of never (do nothing), warn (only
     * emit a warning), detect_or_warn (the
     * default; fix file if we can, warn
     * otherwise)
     * <p>
     * Needs one sub argument: POLICY
     */
    public static final String ARGUMENT_FIXUP = "--fixup";
    /**
     * Prefer avconv over ffmpeg for running the
     * postprocessors
     */
    public static final String ARGUMENT_PREFER_AVCONV = "--prefer-avconv";
    /**
     * Prefer ffmpeg over avconv for running the
     * postprocessors (default)
     */
    public static final String ARGUMENT_PREFER_FFMPEG = "--prefer-ffmpeg";
    /**
     * Location of the ffmpeg/avconv binary;
     * either the path to the binary or its
     * containing directory.
     * <p>
     * Needs one sub argument: PATH
     */
    public static final String ARGUMENT_FFMPEG_LOCATION = "--ffmpeg-location";
    /**
     * Execute a command on the file after
     * downloading, similar to find's -exec
     * syntax. Example: --exec 'adb push {}
     * /sdcard/Music/ && rm {}'
     * <p>
     * Needs one sub argument: CMD
     */
    public static final String ARGUMENT_EXEC = "--exec";
    /**
     * Convert the subtitles to other format
     * (currently supported: srt|ass|vtt|lrc)
     * <p>
     * Needs one sub argument: FORMAT
     */
    public static final String ARGUMENT_CONVERT_SUBS = "--convert-subs";
    // //
    
    // Other
    private static final Set<String> USED_LOG_NAMES = new HashSet<>();
    
    public static String getProgramName() {
        return PROGRAM_NAME;
    }
    
    public static void setProgramName(String programName) {
        PROGRAM_NAME = programName;
    }
    
    public static AdvancedFile getConfigFile() {
        return CONFIG_FILE;
    }
    
    public static void setConfigFile(AdvancedFile configFile) {
        CONFIG_FILE = configFile;
    }
    
    public static String getConfigFileAbsoluteAndEscaped() {
        if (getConfigFile() == null) {
            return null;
        }
        return "\"" + getConfigFile().getAbsolutePath() + "\"";
    }
    
    public static AdvancedFile getOutputDirectory() {
        return OUTPUT_DIRECTORY;
    }
    
    public static String getOutputDirectoryAbsoluteAndEscaped() {
        if (getOutputDirectory() == null) {
            return null;
        }
        return "\"" + getOutputDirectory().getAbsolutePath() + "\"";
    }
    
    public static void setOutputDirectory(AdvancedFile outputDirectory) {
        OUTPUT_DIRECTORY = outputDirectory;
    }
    
    public static AdvancedFile getLogFile() {
        return LOG_FILE;
    }
    
    public static void setLogFile(AdvancedFile logFile) {
        LOG_FILE = logFile;
    }
    
    public static String getLogFileNameTemplate() {
        return LOG_FILE_NAME_TEMPLATE;
    }
    
    public static void setLogFileNameTemplate(String logFileNameTemplate) {
        LOG_FILE_NAME_TEMPLATE = logFileNameTemplate;
    }
    
    public static AdvancedFile createLogFile(Source source) {
        if (source == null) {
            return null;
        }
        final AdvancedFile directory = getLogsDirectory();
        if (directory == null) {
            return null;
        }
        directory.mkdirsWithoutException();
        final String template = getLogFileNameTemplate();
        if (template == null) {
            return null;
        }
        final String id = source.getId();
        if (id == null) {
            return null;
        }
        while (true) {
            final String name = String.format(template, id, ZonedDateTime.now().format(TimeUtil.ISO_OFFSET_DATE_TIME_FIXED_LENGTH_FOR_FILES));
            synchronized (USED_LOG_NAMES) {
                if (USED_LOG_NAMES.contains(name)) {
                    continue;
                }
                USED_LOG_NAMES.add(name);
            }
            return new AdvancedFile(directory, name);
        }
    }
    
    public static AdvancedFile getLogsDirectory() {
        return LOGS_DIRECTORY;
    }
    
    public static void setLogsDirectory(AdvancedFile logsDirectory) {
        LOGS_DIRECTORY = logsDirectory;
    }
    
    public static String[] generateCommandStringArray(AbstractDownloadContainer downloadContainer) {
        final DownloadSettings downloadSettings = downloadContainer.getDownloadSettings();
        final boolean useExternalConfig = downloadSettings.isUsingExternalConfig();
        final boolean hasArguments = downloadSettings.hasArguments();
        final String sourceQuoted = downloadContainer.getSource().getSourceQuoted();
        //FIXME What if there is already a "ARGUMENT_CONFIG_LOCATION" in the Argument Array?
        if (hasArguments) {
            if (useExternalConfig) {
                final String[] output = new String[3 + downloadSettings.getArguments().length + 1];
                output[0] = PROGRAM_NAME;
                output[1] = ARGUMENT_CONFIG_LOCATION;
                output[2] = downloadSettings.getConfigFileAbsolutePathQuoted();
                System.arraycopy(downloadSettings.getArguments(), 0, output, 3, downloadSettings.getArguments().length);
                output[output.length - 1] = sourceQuoted;
                return output;
            } else {
                final String[] output = new String[1 + downloadSettings.getArguments().length + 1];
                output[0] = PROGRAM_NAME;
                System.arraycopy(downloadSettings.getArguments(), 0, output, 1, downloadSettings.getArguments().length);
                output[output.length - 1] = sourceQuoted;
                return output;
            }
        } else {
            if (useExternalConfig) {
                return new String[] {PROGRAM_NAME, ARGUMENT_CONFIG_LOCATION, downloadSettings.getConfigFileAbsolutePathQuoted(), sourceQuoted};
            } else {
                return new String[] {PROGRAM_NAME, sourceQuoted};
            }
        }
    }
    
    protected static Process createProcess(YouTubeSource source, DownloadSettings settings) throws Exception {
        return createProcess(new YouTubeDownloadContainer(source, settings));
    }
    
    protected static Process createProcess(AbstractDownloadContainer downloadContainer) throws Exception {
        return createProcessIntern(downloadContainer.getDownloadSettings().getOutputDirectory(), generateCommandStringArray(downloadContainer));
    }
    
    private static Process createProcessIntern(AdvancedFile directory, String[] command) throws Exception {
        final ProcessBuilder processBuilder = new ProcessBuilder(command);
        //System.out.println("command=" + Arrays.toString(command)); //TODO DEBUG Remove this
        System.out.println("Full command: " + Arrays.asList(command).stream().collect(Collectors.joining(" ", "\"", "\""))); //TODO DEBUG Remove this
        //System.out.println("processBuilder=" + processBuilder); //TODO DEBUG Remove this
        directory.mkdirsWithoutException();
        processBuilder.directory(directory.toFile());
        return processBuilder.start();
    }
    
    @Deprecated
    public static List<String> downloadIdsDirect(Source source) {
        final DownloadInfo downloadInfo = new DownloadInfo(source);
        downloadInfo.setUseConfig(false);
        downloadInfo.setArguments(ARGUMENT_GET_ID);
        final List<String> ids = new ArrayList<>();
        try {
            final AtomicBoolean errored = new AtomicBoolean(false);
            final int exitValue = Misc.monitorProcess(createProcess(downloadInfo), ids::add, (error) -> errored.set(true));
            if (exitValue != 0) { //TODO What todo if "errored" is true?
                return null;
            }
            return ids;
        } catch (Exception ex) {
            Logger.handleError(ex);
            return null;
        }
    }
    
    @Deprecated
    public static List<VideoInfo> downloadVideoInfosDirect(Source source) {
        return downloadVideoInfosDirect(source, VideoInfo::new);
    }
    
    @Deprecated
    public static List<VideoInfo> downloadVideoInfosDirect(Source source, ToughSupplier<VideoInfo> videoInfoGenerator) {
        final DownloadInfo downloadInfo = new DownloadInfo(source);
        downloadInfo.setUseConfig(false);
        downloadInfo.setArguments(ARGUMENT_IGNORE_ERRORS, ARGUMENT_FLAT_PLAYLIST, ARGUMENT_GET_TITLE, ARGUMENT_GET_ID, ARGUMENT_GET_DURATION);
        final List<VideoInfo> videoInfos = new ArrayList<>();
        try {
            final AtomicBoolean errored = new AtomicBoolean(false);
            final AtomicInteger counter = new AtomicInteger(0);
            final int exitValue = Misc.monitorProcess(createProcess(downloadInfo), (normal) -> {
                switch (counter.get()) {
                    case 0: //Title
                        videoInfos.add(videoInfoGenerator.getWithoutException().setTitle(normal));
                        break;
                    case 1: //ID
                        videoInfos.get(videoInfos.size() - 1).setId(normal);
                        counter.set(-1); //FIXME Duration is not downloaded, when using --flat-playlist
                        break;
                    case 2: //Duration
                        videoInfos.get(videoInfos.size() - 1).setDuration(normal);
                        counter.set(-1);
                        break;
                }
                counter.incrementAndGet();
            }, (error) -> errored.set(true)); //TODO What if a playlist is private etc.? Throw an Error indicating a private Playlist etc.?
            if (exitValue != 0 || errored.get()) { //TODO What todo if "errored" is true?
                return videoInfos;
            }
            return videoInfos;
        } catch (Exception ex) {
            Logger.handleError(ex);
            return null;
        }
    }
    
    @Deprecated
    public static Doublet<List<FileInfo>, Future<List<FileInfo>>> downloadFileInfosFromListAndThenAsync(Source source) {
        return downloadFileInfosFromListAndThenAsync(source, false);
    }
    
    @Deprecated
    public static Doublet<List<FileInfo>, Future<List<FileInfo>>> downloadFileInfosFromListAndThenAsync(Source source, ToughSupplier<FileInfo> fileInfoGenerator) {
        return downloadFileInfosFromListAndThenAsync(source, fileInfoGenerator, false);
    }
    
    @Deprecated
    public static final Doublet<List<FileInfo>, Future<List<FileInfo>>> downloadFileInfosFromListAndThenAsync(Source source, boolean getIndex) {
        final Doublet<List<FileInfo>, Future<List<FileInfo>>> doublet = downloadFileInfosAndThenAsync(source);
        //addPlaylistInformationToFileInfos(doublet.getA(), source, getIndex); //FIXME
        return doublet;
    }
    
    @Deprecated
    public static final Doublet<List<FileInfo>, Future<List<FileInfo>>> downloadFileInfosFromListAndThenAsync(Source source, ToughSupplier<FileInfo> fileInfoGenerator, boolean getIndex) {
        final Doublet<List<FileInfo>, Future<List<FileInfo>>> doublet = downloadFileInfosAndThenAsync(source, fileInfoGenerator);
        //addPlaylistInformationToFileInfos(doublet.getA(), source, getIndex); //FIXME
        return doublet;
    }
    
    @Deprecated
    public static Doublet<List<FileInfo>, Future<List<FileInfo>>> downloadFileInfosAndThenAsync(Source source) {
        return downloadFileInfosAndThenAsync(source, () -> new FileInfo(new VideoInfo()));
    }
    
    @Deprecated
    public static Doublet<List<FileInfo>, Future<List<FileInfo>>> downloadFileInfosAndThenAsync(Source source, ToughSupplier<FileInfo> fileInfoGenerator) {
        return downloadFileInfosAndThenAsync(Misc.EXECUTOR_SERVICE_TOUGH_SUPPLIER, source, fileInfoGenerator);
    }
    
    @Deprecated
    public static Doublet<List<FileInfo>, Future<List<FileInfo>>> downloadFileInfosAndThenAsync(ToughSupplier<ExecutorService> executorServiceSupplier, Source source, ToughSupplier<FileInfo> fileInfoGenerator) {
        final DownloadInfo downloadInfo = new DownloadInfo(source);
        downloadInfo.setUseConfig(false);
        downloadInfo.setArguments(ARGUMENT_IGNORE_ERRORS, ARGUMENT_IGNORE_CONFIG, ARGUMENT_FLAT_PLAYLIST, ARGUMENT_GET_TITLE, ARGUMENT_GET_ID);
        final List<FileInfo> fileInfos = new ArrayList<>();
        try {
            final AtomicBoolean errored = new AtomicBoolean(false);
            final AtomicInteger counter = new AtomicInteger(0);
            final AtomicReference<String> title = new AtomicReference<>(null);
            final int exitValue = Misc.monitorProcess(createProcess(downloadInfo), (normal) -> {
                switch (counter.get()) {
                    case 0: //Title
                        title.set(normal);
                        //videoInfos.put(videoInfoGenerator.getWithoutException().setTitle(normal)); //TODO Remove this
                        break;
                    case 1: //ID
                        final FileInfo fileInfo = fileInfoGenerator.getWithoutException();
                        fileInfo.getVideoInfo().setId(normal);
                        if (title.get() != null) {
                            fileInfo.getVideoInfo().setTitle(title.get());
                            title.set(null);
                        }
                        fileInfos.add(fileInfo);
                        //videoInfos.get(videoInfos.size() - 1).setId(normal);
                        counter.set(-1); //FIXME Duration is not downloaded, when using --flat-playlist
                        break;
                    case 2: //Duration
                        //videoInfos.get(videoInfos.size() - 1).setDuration(normal); //TODO Remove this
                        counter.set(-1);
                        break;
                }
                counter.incrementAndGet();
            }, (error) -> errored.set(true)); //TODO What if a playlist is private etc.? Throw an Error indicating a private Playlist etc.?
            if (exitValue != 0 || errored.get()) { //TODO What todo if "errored" is true?
                //return videoInfos;
            }
            
            final Future<List<FileInfo>> future = downloadFileInfosAsync(executorServiceSupplier, fileInfos);
            
            //return videoInfos;
            return new Doublet<>(fileInfos, future);
        } catch (Exception ex) {
            Logger.handleError(ex);
            return null;
        }
    }
    
    @Deprecated
    private static Future<List<FileInfo>> downloadFileInfosAsync(ToughSupplier<ExecutorService> executorServiceSupplier, List<FileInfo> fileInfos) {
        FutureTask<List<FileInfo>> futureTask = new FutureTask<>(() -> downloadFileInfosExtras(executorServiceSupplier, fileInfos));
        Standard.async(futureTask::run); //TODO Async extra info loading stuff
        return futureTask;
    }
    
    @Deprecated
    private static List<FileInfo> downloadFileInfosExtras(ToughSupplier<ExecutorService> executorServiceSupplier, List<FileInfo> fileInfos) {
        final ExecutorService executorService = executorServiceSupplier.getWithoutException();
        //fileInfos.forEach((videoInfo) -> executorService.submit(() -> downloadFileInfoExtras(videoInfo)));
        fileInfos.forEach((videoInfo) -> executorService.submit(() -> downloadInfoEverythingAndAddToFileInfo(videoInfo)));
        executorService.shutdown();
        Standard.silentError(() -> executorService.awaitTermination(10, TimeUnit.MINUTES));
        executorService.shutdownNow();
        return fileInfos;
    }
    
    public static boolean downloadDirect(YouTubeSource source, DownloadSettings settings) {
        return downloadDirect(new YouTubeDownloadContainer(source, settings, null));
    }
    
    public static boolean downloadDirect(AbstractDownloadContainer downloadContainer) {
        if (downloadContainer == null) {
            return false;
        }
        final boolean useDownloadProgress = downloadContainer.isUsingDownloadProgress();
        if (useDownloadProgress) {
            if (downloadContainer.getDownloadProgress().isStarted() || downloadContainer.getDownloadProgress().isAlive()) {
                return false;
            }
            downloadContainer.getDownloadProgress().start();
        }
        boolean success;
        try {
            success = executeDownloadContainer(downloadContainer);
            if (useDownloadProgress) {
                downloadContainer.getDownloadProgress().setSuccessful(success);
            }
        } catch (Exception ex) {
            success = false;
            Logger.handleError(ex);
        }
        if (useDownloadProgress) {
            downloadContainer.getDownloadProgress().setAlive(false);
        }
        return success;
    }
    
    protected static boolean executeDownload(YouTubeSource source, DownloadSettings settings) throws Exception {
        return executeDownloadContainer(new YouTubeDownloadContainer(source, settings, null));
    }
    
    private static boolean executeDownloadContainer(AbstractDownloadContainer downloadContainer) throws Exception {
        if (downloadContainer.isUsingDownloadProgress() && !downloadContainer.getDownloadSettings().hasArgument(ARGUMENT_NEWLINE)) {
            downloadContainer.getDownloadSettings().addArgument(ARGUMENT_NEWLINE);
        }
        return Misc.monitorProcess(createProcess(downloadContainer), downloadContainer) == 0;
    }
    
    public static VideoInstanceInfo downloadVideoInstanceInfo(YouTubeSource source) { //TODO IMPORTANT Download VideoInstanceInfo for every MediaFile and then always (create playlist if not exists and) add the video to the playlist if it is not already in there (Database) (But getting the Index requires the information for a video, so always add all videos from a playlist to it in the database if a method detects a playlist is being download instead of a video??)
        return downloadRFromFirstLine(source, VideoInstanceInfo::outputInfoToVideoInstanceInfo);
    }
    
    protected static <R> R downloadRFromFirstLine(YouTubeSource source, ToughFunction<String, R> function) {
        return downloadRFromFirstLine(source, DownloadSettings.empty(), function);
    }
    
    protected static <R> R downloadRFromFirstLine(YouTubeSource source, DownloadSettings settings, ToughFunction<String, R> function) {
        return downloadRFromFirstLine(null, source, settings, function);
    }
    
    protected static <R> R downloadRFromFirstLine(YouTubeDatabase database, YouTubeSource source, DownloadSettings settings, ToughFunction<String, R> function) {
        if (source == null || function == null) {
            return null;
        }
        final YouTubeDownloadContainer downloadContainer = new YouTubeDownloadContainer(database, source, settings, null);
        try {
            final AtomicReference<R> atomicReference = new AtomicReference<>(null);
            final AtomicBoolean errored = new AtomicBoolean(false);
            final int exitValue = Misc.monitorProcess(createProcess(downloadContainer), (normal) -> atomicReference.set(function.applyWithoutException(normal)), (error) -> errored.set(true)); //TODO What if a playlist is private etc.? Throw an Error indicating a private Playlist etc.?
            if (exitValue != 0 || errored.get()) { //TODO What todo if "errored" is true?
                //return;
            }
            return atomicReference.get();
        } catch (Exception ex) {
            Logger.handleError(ex);
            return null;
        }
    }
    
}
