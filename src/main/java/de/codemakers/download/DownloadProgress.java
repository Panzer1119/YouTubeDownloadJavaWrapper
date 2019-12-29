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

import java.util.Arrays;

public class DownloadProgress {
    
    private final DownloadInfo downloadInfo;
    //Temp
    private transient boolean started = false;
    private transient boolean alive = false;
    private transient boolean successful = false;
    private transient volatile float[] progresses = null;
    
    public DownloadProgress(DownloadInfo downloadInfo) {
        this.downloadInfo = downloadInfo;
    }
    
    public DownloadInfo getDownloadInfo() {
        return downloadInfo;
    }
    
    public DownloadProgress start() {
        setAlive(true);
        setStarted(true);
        setProgresses(new float[downloadInfo.getExpectedDownloads()]);
        return this;
    }
    
    public boolean isStarted() {
        return started;
    }
    
    protected DownloadProgress setStarted(boolean started) {
        this.started = started;
        return this;
    }
    
    public boolean isAlive() {
        return alive;
    }
    
    protected DownloadProgress setAlive(boolean alive) {
        this.alive = alive;
        return this;
    }
    
    public boolean isSuccessful() {
        return successful;
    }
    
    protected DownloadProgress setSuccessful(boolean successful) {
        this.successful = successful;
        return this;
    }
    
    public float[] getProgresses() {
        return progresses;
    }
    
    public DownloadProgress setProgresses(float[] progresses) {
        this.progresses = progresses;
        return this;
    }
    
    public int getNextProgressIndex() {
        for (int i = 0; i < progresses.length; i++) {
            if (progresses[i] < 1.0F) {
                return i;
            }
        }
        return -1;
    }
    
    public DownloadProgress setProgress(int index, float progress) {
        this.progresses[index] = progress;
        return this;
    }
    
    public float getProgress(int index) {
        return progresses[index];
    }
    
    public double getProgressOverall() {
        double sum = 0.0;
        for (float f : progresses) {
            sum += f;
        }
        return sum / progresses.length;
    }
    
    @Override
    public String toString() {
        return "DownloadProgress{" + "downloadInfo=" + downloadInfo + ", started=" + started + ", alive=" + alive + ", successful=" + successful + ", progresses=" + Arrays.toString(progresses) + '}';
    }
    
}
