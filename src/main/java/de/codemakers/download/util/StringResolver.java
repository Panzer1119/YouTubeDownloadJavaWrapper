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

package de.codemakers.download.util;

import de.codemakers.base.logger.Logger;
import de.codemakers.base.util.StringUtil;
import de.codemakers.io.file.AdvancedFile;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringResolver {
    
    public static final String DEFAULT_TAG_START = ":";
    public static final String DEFAULT_TAG_END = ":";
    
    protected Properties properties = null;
    private String tagStart = DEFAULT_TAG_START;
    private String tagEnd = DEFAULT_TAG_END;
    //
    private transient AdvancedFile advancedFile = null;
    
    public StringResolver(AdvancedFile advancedFile) {
        this.advancedFile = advancedFile;
        loadFromFile();
    }
    
    private void loadFromFile() {
        if (advancedFile == null) {
            return;
        }
        if (properties == null) {
            setProperties(new Properties());
        }
        advancedFile.createInputStreamClosingAction().consumeWithoutException(properties::load);
    }
    
    public StringResolver() {
        this(new Properties());
    }
    
    public StringResolver(Properties properties) {
        setProperties(properties);
    }
    
    public Properties getProperties() {
        return properties;
    }
    
    public StringResolver setProperties(Properties properties) {
        this.properties = properties;
        return this;
    }
    
    public AdvancedFile getAdvancedFile() {
        return advancedFile;
    }
    
    public StringResolver setAdvancedFile(AdvancedFile advancedFile) {
        this.advancedFile = advancedFile;
        return this;
    }
    
    public String getTagStart() {
        return tagStart;
    }
    
    public StringResolver setTagStart(String tagStart) {
        this.tagStart = tagStart;
        return this;
    }
    
    public String getTagEnd() {
        return tagEnd;
    }
    
    public StringResolver setTagEnd(String tagEnd) {
        this.tagEnd = tagEnd;
        return this;
    }
    
    public String resolve(final String input) {
        String output = input;
        if (properties != null) {
            for (String key : properties.stringPropertyNames()) {
                final String toReplace = tagStart + key + tagEnd;
                final String replacement = properties.getProperty(key, "");
                output = output.replaceAll(StringUtil.escapePlainStringToRegExMatchString(toReplace), StringUtil.escapePlainStringToRegExReplacementString(replacement));
            }
            final Matcher matcher = Pattern.compile(StringUtil.escapePlainStringToRegExMatchString(tagStart) + ".*" + StringUtil.escapePlainStringToRegExMatchString(tagEnd)).matcher(output);
            if (matcher.find()) {
                //TODO Should this return null instead?
                Logger.logWarning(String.format("Couldn't fully resolve the String \"%s\", resolved only to \"%s\"", input, output)); //DEBUG
                return null;
            }
        }
        return output;
    }
    
    @Override
    public String toString() {
        return "StringResolver{" + "properties=" + properties + ", tagStart='" + tagStart + '\'' + ", tagEnd='" + tagEnd + '\'' + ", advancedFile=" + advancedFile + '}';
    }
    
}
