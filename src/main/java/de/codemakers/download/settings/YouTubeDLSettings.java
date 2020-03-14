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

package de.codemakers.download.settings;

import de.codemakers.base.util.DefaultSettings;
import de.codemakers.base.util.Settings;
import de.codemakers.download.util.Misc;
import de.codemakers.io.file.AdvancedFile;

import java.util.Properties;

public class YouTubeDLSettings extends DefaultSettings {
    
    public static final String KEY_DATABASE_DIRECTORY = "database_directory";
    public static final String DEF_DATABASE_DIRECTORY = "Path to the directory, where the database will be stored";
    public static final String KEY_ARCHIVE_DIRECTORY = "archive_directory";
    public static final String DEF_ARCHIVE_DIRECTORY = "Path to the directory, where all the downloaded video and audio will be stored";
    
    public YouTubeDLSettings() {
    }
    
    public YouTubeDLSettings(Properties properties) {
        super(properties);
    }
    
    public YouTubeDLSettings(AdvancedFile advancedFile) {
        super(advancedFile);
    }
    
    public String getDatabaseDirectoryString() {
        return getProperty(KEY_DATABASE_DIRECTORY);
    }
    
    public AdvancedFile getDatabaseDirectory() {
        return new AdvancedFile(getDatabaseDirectoryString());
    }
    
    public YouTubeDLSettings setDatabaseDirectory(String path) {
        setProperty(KEY_DATABASE_DIRECTORY, path);
        return this;
    }
    
    public YouTubeDLSettings setDatabaseDirectory(AdvancedFile directory) {
        setDatabaseDirectory(directory.getAbsolutePath()); //TODO Should i use .getAbsolutePath(), or not?
        return this;
    }
    
    public String getArchiveDirectoryString() {
        return getProperty(KEY_ARCHIVE_DIRECTORY);
    }
    
    public AdvancedFile getArchiveDirectory() {
        return new AdvancedFile(getArchiveDirectoryString());
    }
    
    public YouTubeDLSettings setArchiveDirectory(String path) {
        setProperty(KEY_ARCHIVE_DIRECTORY, path);
        return this;
    }
    
    public YouTubeDLSettings setArchiveDirectory(AdvancedFile directory) {
        setArchiveDirectory(directory.getAbsolutePath()); //TODO Should i use .getAbsolutePath(), or not?
        return this;
    }
    
    @Override
    public String toString() {
        return "YouTubeDLSettings{" + "properties=" + properties + ", advancedFile=" + advancedFile + ", autoSave=" + autoSave + '}';
    }
    
    public static final YouTubeDLSettings getOrCreateNewSettingsInDirectory(String subProgramName) {
        return getOrCreateNewSettings(Misc.getSubAppDataProgramDirectory(subProgramName, Misc.DEFAULT_SETTINGS_FILE_NAME));
    }
    
    public static final YouTubeDLSettings getOrCreateNewSettingsInDirectory(AdvancedFile directory) {
        return getOrCreateNewSettings(new AdvancedFile(directory, Misc.DEFAULT_SETTINGS_FILE_NAME));
    }
    
    public static final YouTubeDLSettings getOrCreateNewSettings(AdvancedFile advancedFile) {
        if (advancedFile.exists()) {
            final YouTubeDLSettings youTubeDLSettings = new YouTubeDLSettings(advancedFile);
            youTubeDLSettings.loadSettings();
            return youTubeDLSettings;
        }
        return createNewSettings(advancedFile);
    }
    
    public static final YouTubeDLSettings createNewSettings(AdvancedFile advancedFile) {
        final YouTubeDLSettings youTubeDLSettings = new YouTubeDLSettings(advancedFile);
        addToNewSettings(youTubeDLSettings);
        return youTubeDLSettings;
    }
    
    public static final void addToNewSettings(Settings settings) {
        if (!settings.hasProperty(KEY_DATABASE_DIRECTORY)) {
            settings.setProperty(KEY_DATABASE_DIRECTORY, DEF_DATABASE_DIRECTORY);
        }
        if (!settings.hasProperty(KEY_ARCHIVE_DIRECTORY)) {
            settings.setProperty(KEY_ARCHIVE_DIRECTORY, DEF_ARCHIVE_DIRECTORY);
        }
        settings.saveSettings();
    }
    
}
