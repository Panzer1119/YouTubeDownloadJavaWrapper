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

package de.codemakers.download.entities;

import de.codemakers.base.logger.Logger;
import de.codemakers.base.util.ArrayUtil;
import de.codemakers.io.file.AdvancedFile;

import java.io.BufferedWriter;
import java.util.Arrays;

public class DownloadSettings {
    
    private AdvancedFile outputDirectory;
    private AdvancedFile configFile;
    private AdvancedFile logFile;
    private boolean useConfig;
    private String[] arguments;
    private int expectedDownloads;
    
    public DownloadSettings() {
        this(null, null, null, false, null, 2);
    }
    
    public DownloadSettings(AdvancedFile outputDirectory, AdvancedFile configFile, AdvancedFile logFile, boolean useConfig, String[] arguments, int expectedDownloads) {
        this.outputDirectory = outputDirectory;
        this.configFile = configFile;
        this.logFile = logFile;
        this.useConfig = useConfig;
        this.arguments = arguments;
        this.expectedDownloads = expectedDownloads;
    }
    
    public static DownloadSettings empty() {
        return new DownloadSettings().setExpectedDownloads(0);
    }
    
    protected String getOutputDirectoryAbsolutePathQuoted() {
        if (outputDirectory == null) {
            return null;
        }
        return "\"" + outputDirectory.getAbsolutePath() + "\"";
    }
    
    protected AdvancedFile getOutputDirectoryAbsolute() {
        if (outputDirectory == null) {
            return null;
        }
        return outputDirectory.getAbsoluteFile();
    }
    
    public AdvancedFile getOutputDirectory() {
        return outputDirectory;
    }
    
    public DownloadSettings setOutputDirectory(AdvancedFile outputDirectory) {
        this.outputDirectory = outputDirectory;
        return this;
    }
    
    protected String getConfigFileAbsolutePathQuoted() {
        if (configFile == null) {
            return null;
        }
        return "\"" + configFile.getAbsolutePath() + "\"";
    }
    
    protected AdvancedFile getConfigFileAbsolute() {
        if (configFile == null) {
            return null;
        }
        return configFile.getAbsoluteFile();
    }
    
    public AdvancedFile getConfigFile() {
        return configFile;
    }
    
    public DownloadSettings setConfigFile(AdvancedFile configFile) {
        this.configFile = configFile;
        return this;
    }
    
    public boolean logError(Object object) {
        return logIntern("[ERROR]: " + object);
    }
    
    public boolean log(Object object) {
        return logIntern("[INFO ]: " + object);
    }
    
    protected boolean logIntern(Object object) {
        if (logFile == null || object == null) {
            return false;
        }
        try (final BufferedWriter bufferedWriter = logFile.createBufferedWriter(true)) {
            bufferedWriter.write(object.toString());
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (Exception ex) {
            Logger.handleError(ex);
        }
        return true;
    }
    
    public boolean isLogging() {
        return logFile != null;
    }
    
    public AdvancedFile getLogFile() {
        return logFile;
    }
    
    public DownloadSettings setLogFile(AdvancedFile logFile) {
        this.logFile = logFile;
        return this;
    }
    
    public boolean isUseConfig() {
        return useConfig;
    }
    
    public DownloadSettings setUsingConfig(boolean useConfig) {
        this.useConfig = useConfig;
        return this;
    }
    
    public String[] getArguments() {
        return arguments;
    }
    
    public boolean hasArguments() {
        return arguments != null && arguments.length > 0;
    }
    
    public boolean hasArgument(String argument) {
        if (arguments == null || arguments.length == 0 || argument == null || argument.isEmpty()) {
            return false;
        }
        return ArrayUtil.arrayContains(arguments, argument);
    }
    
    public DownloadSettings addArgument(String argument) {
        if (argument == null) {
            return this;
        }
        if (arguments == null) {
            return setArguments(argument);
        }
        arguments = Arrays.copyOf(arguments, arguments.length + 1);
        arguments[arguments.length - 1] = argument;
        return this;
    }
    
    public DownloadSettings setArguments(String... arguments) {
        this.arguments = arguments;
        return this;
    }
    
    public int getExpectedDownloads() {
        return expectedDownloads;
    }
    
    public DownloadSettings setExpectedDownloads(int expectedDownloads) {
        this.expectedDownloads = expectedDownloads;
        return this;
    }
    
    @Override
    public String toString() {
        return "DownloadSettings{" + "outputDirectory=" + outputDirectory + ", configFile=" + configFile + ", logFile=" + logFile + ", useConfig=" + useConfig + ", arguments=" + Arrays.toString(arguments) + ", expectedDownloads=" + expectedDownloads + '}';
    }
    
}
