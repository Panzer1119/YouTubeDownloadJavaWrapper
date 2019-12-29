/*
 *    Copyright 2019 Paul Hagedorn (Panzer1119)
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

import de.codemakers.base.logger.Logger;
import de.codemakers.base.util.TimeUtil;
import de.codemakers.base.util.tough.ToughSupplier;
import de.codemakers.download.sources.Source;
import de.codemakers.io.file.AdvancedFile;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class YouTubeDL {
    
    // // YouTube URL Pattern
    // All YouTube URL Pattern
    /**
     * Group 1: Maybe Playlist ID
     * Group 2: Maybe Video ID
     */
    public static final String PATTERN_YOUTUBE_URL_STRING = "(?:http(?:s)?:\\/\\/)?(?:www.)?(?:youtube\\.com\\/(?:channel\\/|playlist\\?list=)([a-zA-Z0-9_-]+)|(?:youtu\\.be\\/|youtube\\.com(?:\\/embed\\/|\\/v\\/|\\/watch\\?v=|\\/ytscreeningroom\\?v=|\\/feeds\\/api\\/videos\\/|\\/user\\\\S*[^\\w\\-\\s]|\\S*[^\\w\\-\\s]))([\\w\\-\\_]{11}))[a-z0-9;:@#?&%=+\\/\\$_.-]*";
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
    public static final String TEMPLATE_LOG_FILE_NAME = "log_%s_%s.txt";
    public static final String UNKNOWN_YOUTUBE_ID = "UNKNOWN";
    
    public static final String DEFAULT_CONFIG_NAME = "youtube-dl.conf";
    private static AdvancedFile CONFIG_FILE = new AdvancedFile(DEFAULT_CONFIG_NAME);
    public static final String DEFAULT_PROGRAM_NAME = "youtube-dl";
    private static String PROGRAM_NAME = DEFAULT_PROGRAM_NAME;
    protected static final AdvancedFile DEFAULT_DIRECTORY = new AdvancedFile();
    private static AdvancedFile DIRECTORY = DEFAULT_DIRECTORY;
    public static final String DEFAULT_LOG_NAME = "log.txt";
    private static AdvancedFile LOG_FILE = new AdvancedFile(DEFAULT_LOG_NAME);
    public static final String DEFAULT_LOGS_NAME = "logs";
    private static AdvancedFile LOGS_DIRECTORY = new AdvancedFile(DEFAULT_LOGS_NAME);
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
    //TODO
    
    // Download Options
    //TODO
    
    // Filesystem Options
    //TODO
    /**
     * Output filename template, see the "OUTPUT
     * TEMPLATE" for all the info
     * <p>
     * Needs one sub argument: TEMPLATE
     */
    public static final String ARGUMENT_OUTPUT = "--output";
    //TODO
    
    // Thumbnail images
    //TODO
    
    // Verbosity / Simulation Options
    //TODO
    /**
     * Simulate, quiet but print title
     */
    public static final String ARGUMENT_GET_TITLE = "--get-title";
    /**
     * Simulate, quiet but print id
     */
    public static final String ARGUMENT_GET_ID = "--get-id";
    //TODO
    /**
     * Simulate, quiet but print video length
     */
    public static final String ARGUMENT_GET_DURATION = "--get-duration";
    //TODO
    
    // Workarounds
    //TODO
    
    // Video Format Options
    /**
     * Video format code, see the "FORMAT
     * SELECTION" for all the info
     * <p>
     * Needs one sub argument: FORMAT
     */
    public static final String ARGUMENT_FORMAT = "--format";
    //TODO
    
    // Subtitle Options
    //TODO
    
    // Authentication Options
    //TODO
    
    // Adobe Pass Options
    //TODO
    
    // Post-processing Options
    //TODO
    
    /**
     *
     */
    public static final String ARGUMENT_ = "";
    // //
    
    //Other
    private static final Set<String> USED_LOG_NAMES = new HashSet<>();
    
    public static AdvancedFile getConfigFile() {
        return CONFIG_FILE;
    }
    
    public static void setConfigFile(AdvancedFile configFile) {
        CONFIG_FILE = configFile;
    }
    
    protected static String getConfigFileAbsoluteAndEscaped() {
        return "\"" + getConfigFile().getAbsolutePath() + "\"";
    }
    
    public static String getProgramName() {
        return PROGRAM_NAME;
    }
    
    public static void setProgramName(String programName) {
        PROGRAM_NAME = programName;
    }
    
    public static AdvancedFile getDirectory() {
        return DIRECTORY;
    }
    
    protected static String getDirectoryAbsoluteAndEscaped() {
        return "\"" + getDirectory().getAbsolutePath() + "\"";
    }
    
    public static void setDirectory(AdvancedFile directory) {
        DIRECTORY = directory;
    }
    
    public static AdvancedFile getLogFile() {
        return LOG_FILE;
    }
    
    public static void setLogFile(AdvancedFile logFile) {
        LOG_FILE = logFile;
    }
    
    public static AdvancedFile getLogsDirectory() {
        return LOGS_DIRECTORY;
    }
    
    public static void setLogsDirectory(AdvancedFile logsDirectory) {
        LOGS_DIRECTORY = logsDirectory;
    }
    
    public static String[] generateCommandStringArray(DownloadInfo downloadInfo) {
        final boolean useConfig = downloadInfo.isUsingConfig();
        final boolean hasArguments = downloadInfo.hasArguments();
        if (hasArguments) {
            if (useConfig) {
                final String[] output = new String[3 + downloadInfo.getArguments().length + 1];
                output[0] = PROGRAM_NAME;
                output[1] = ARGUMENT_CONFIG_LOCATION;
                output[2] = getConfigFileAbsoluteAndEscaped();
                System.arraycopy(downloadInfo.getArguments(), 0, output, 3, downloadInfo.getArguments().length);
                output[output.length - 1] = downloadInfo.getUrl();
                return output;
            } else {
                final String[] output = new String[1 + downloadInfo.getArguments().length + 1];
                output[0] = PROGRAM_NAME;
                System.arraycopy(downloadInfo.getArguments(), 0, output, 1, downloadInfo.getArguments().length);
                output[output.length - 1] = downloadInfo.getUrl();
                return output;
            }
        } else {
            if (useConfig) {
                return new String[] {PROGRAM_NAME, ARGUMENT_CONFIG_LOCATION, getConfigFileAbsoluteAndEscaped(), downloadInfo.getUrl()};
            } else {
                return new String[] {PROGRAM_NAME, downloadInfo.getUrl()};
            }
        }
    }
    
    public static Process createProcess(Source source) throws Exception {
        return createProcess(DIRECTORY, source);
    }
    
    public static Process createProcess(AdvancedFile directory, Source source) throws Exception {
        return createProcess(directory, source, null);
    }
    
    public static Process createProcess(AdvancedFile directory, Source source, AdvancedFile logFile) throws Exception {
        return createProcess(new DownloadInfo(directory, source, logFile));
    }
    
    public static Process createProcess(DownloadInfo downloadInfo) throws Exception {
        return createProcessIntern(downloadInfo.getDirectoryAbsolute(), generateCommandStringArray(downloadInfo));
    }
    
    private static Process createProcessIntern(AdvancedFile directory, String[] command) throws Exception {
        final ProcessBuilder processBuilder = new ProcessBuilder(command);
        System.out.println("command=" + Arrays.toString(command)); //TODO Debug only
        System.out.println("Full command: " + Arrays.asList(command).stream().collect(Collectors.joining(" ", "\"", "\""))); //TODO Debug only
        System.out.println("processBuilder=" + processBuilder); //TODO Debug only
        directory.mkdirsWithoutException();
        processBuilder.directory(directory.toFile());
        return processBuilder.start();
    }
    
    public static boolean downloadDirect(DownloadInfo downloadInfo) throws Exception {
        return downloadDirect(new DownloadProgress(downloadInfo));
    }
    
    public static boolean downloadDirect(DownloadProgress downloadProgress) throws Exception {
        if (downloadProgress.isStarted() || downloadProgress.isAlive()) {
            return false;
        }
        downloadProgress.start();
        downloadProgress.setSuccessful(downloadDirectIntern(downloadProgress));
        downloadProgress.setAlive(false);
        System.out.println("FINISHED downloadProgress=" + downloadProgress + ", successful=" + downloadProgress.isSuccessful()); //TODO Debug only
        return downloadProgress.isSuccessful();
    }
    
    private static boolean downloadDirectIntern(DownloadProgress downloadProgress) throws Exception {
        final Process process = createProcess(downloadProgress.getDownloadInfo());
        //TODO Implement the progress Stuff (Read output and set the progress value accordingly)
        
        if (true) {
            return Misc.monitorProcess(process, downloadProgress) == 0;
        }
        return Misc.monitorProcessToFile(process, downloadProgress.getDownloadInfo().getLogFile(), false) == 0; //FIXME Remove the old thing
    }
    
    protected static AdvancedFile createLogFile(Source source) {
        LOGS_DIRECTORY.mkdirsWithoutException();
        final String id = source.getId();
        while (true) {
            final String name = String.format(TEMPLATE_LOG_FILE_NAME, id, ZonedDateTime.now().format(TimeUtil.ISO_OFFSET_DATE_TIME_FIXED_LENGTH_FOR_FILES));
            synchronized (USED_LOG_NAMES) {
                if (USED_LOG_NAMES.contains(name)) {
                    continue;
                }
                USED_LOG_NAMES.add(name);
            }
            return new AdvancedFile(LOGS_DIRECTORY, name);
        }
    }
    
    public static String getIdFromYouTubeUrl(String url) { //FIXME What if i just supply the id? Maybe add an option to (direct) download by id?
        return getIdFromYouTubeUrl(url, "");
    }
    
    public static String getIdFromYouTubeUrl(String url, String defaultValue) {
        if (url == null || url.isEmpty()) {
            return defaultValue;
        }
        final Matcher matcher = PATTERN_YOUTUBE_URL.matcher(url);
        if (!matcher.matches()) {
            return defaultValue;
        }
        if (matcher.group(1) == null || matcher.group(1).isEmpty()) {
            return matcher.group(2);
        }
        return matcher.group(1);
    }
    
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
    
    public static List<VideoInfo> downloadVideoInfosDirect(Source source) {
        return downloadVideoInfosDirect(source, VideoInfo::new);
    }
    
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
                        break;
                    case 2: //Duration
                        videoInfos.get(videoInfos.size() - 1).setDuration(normal);
                        counter.set(-1);
                        break;
                }
                counter.incrementAndGet();
            }, (error) -> errored.set(true)); //TODO What if a playlist is private etc.? Throw an Error indicating a private Playlist etc.?
            System.out.println("downloadVideoInfosDirect: exitValue=" + exitValue);
            if (exitValue != 0 || errored.get()) { //TODO What todo if "errored" is true?
                return videoInfos;
            }
            return videoInfos;
        } catch (Exception ex) {
            Logger.handleError(ex);
            return null;
        }
    }
    
}
