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
    
    public static final String PATTERN_YOUTUBE_URL_STRING = "(?:http|https|)(?::\\/\\/|)(?:www.|)(?:youtu\\.be\\/|youtube\\.com(?:\\/embed\\/|\\/v\\/|\\/watch\\?v=|\\/ytscreeningroom\\?v=|\\/feeds\\/api\\/videos\\/|\\/user\\\\S*[^\\w\\-\\s]|\\S*[^\\w\\-\\s]))([\\w\\-\\_]{11})[a-z0-9;:@#?&%=+\\/\\$_.-]*";
    public static final Pattern PATTERN_YOUTUBE_URL = Pattern.compile(PATTERN_YOUTUBE_URL_STRING);
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
    //Arguments
    public static String ARGUMENT_IGNORE_ERRORS = "-i";
    public static String ARGUMENT_CONFIG_LOCATION = "--config-location";
    public static String ARGUMENT_OUTPUT_FORMAT = "-o";
    public static String ARGUMENT_FORMAT = "-f";
    public static String ARGUMENT_GET_TITLE = "--get-title";
    public static String ARGUMENT_GET_ID = "--get-id";
    public static String ARGUMENT_GET_DURATION = "--get-duration";
    
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
        downloadProgress.setAlive(true);
        downloadProgress.setStarted(true);
        downloadProgress.setSuccessful(downloadDirectIntern(downloadProgress));
        downloadProgress.setAlive(false);
        System.out.println("FINISHED downloadProgress=" + downloadProgress + ", successful=" + downloadProgress.isSuccessful()); //TODO Debug only
        return downloadProgress.isSuccessful();
    }
    
    private static boolean downloadDirectIntern(DownloadProgress downloadProgress) throws Exception {
        final Process process = createProcess(downloadProgress.getDownloadInfo());
        //TODO Implement the progress Stuff (Read output and set the progress value accordingly)
        return Misc.monitorProcessToFile(process, downloadProgress.getDownloadInfo().getLogFile(), false) == 0;
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
        downloadInfo.setArguments(ARGUMENT_IGNORE_ERRORS, ARGUMENT_GET_TITLE, ARGUMENT_GET_ID, ARGUMENT_GET_DURATION);
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
            }, (error) -> errored.set(true));
            if (exitValue != 0 || errored.get()) { //TODO What todo if "errored" is true?
                return null;
            }
            return videoInfos;
        } catch (Exception ex) {
            Logger.handleError(ex);
            return null;
        }
    }
    
}
