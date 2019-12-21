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
import de.codemakers.base.util.tough.ToughConsumer;
import de.codemakers.io.file.AdvancedFile;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.util.Scanner;

public class Misc {
    
    public static int monitorProcessDefault(Process process) {
        return monitorProcessNothing(process);
    }
    
    public static int monitorProcessToFile(Process process, AdvancedFile output) {
        return monitorProcessToFile(process, output, true);
    }
    
    public static int monitorProcessToFile(Process process, AdvancedFile output, boolean append) {
        if (output == null) {
            return monitorProcessNothing(process);
        }
        try (final BufferedWriter bufferedWriter = output.createBufferedWriter(append)) {
            final int exitValue = monitorProcess(process, (normal) -> {
                bufferedWriter.write("[INFO ]: " + normal);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }, (error) -> {
                bufferedWriter.write("[ERROR]: " + error);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            });
            bufferedWriter.flush();
            return exitValue;
        } catch (Exception ex) {
            Logger.handleError(ex);
            return -1;
        }
    }
    
    public static int monitorProcessNothing(Process process) {
        return monitorProcess(process, null, null);
    }
    
    public static int monitorProcessDebug(Process process) {
        return monitorProcess(process, System.out::println, System.err::println);
    }
    
    public static int monitorProcess(Process process, ToughConsumer<String> normal) {
        return monitorProcess(process, normal, normal);
    }
    
    public static int monitorProcess(Process process, ToughConsumer<String> normal, ToughConsumer<String> error) {
        final Thread thread_normal = new Thread(() -> monitorInputStream(process.getInputStream(), normal));
        thread_normal.start();
        final Thread thread_error = new Thread(() -> monitorInputStream(process.getErrorStream(), error));
        thread_error.start();
        try {
            thread_normal.join();
            thread_error.join();
        } catch (Exception ex) {
        }
        try {
            return process.waitFor();
        } catch (Exception ex) {
            return -1;
        }
    }
    
    protected static void monitorInputStream(InputStream inputStream, ToughConsumer<String> toughConsumer) {
        final Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            final String line = scanner.nextLine();
            if (toughConsumer != null) {
                toughConsumer.acceptWithoutException(line);
            }
        }
        scanner.close();
    }
    
}
