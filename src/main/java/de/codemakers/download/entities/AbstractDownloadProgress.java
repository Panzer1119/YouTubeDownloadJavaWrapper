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

import java.util.Arrays;

public abstract class AbstractDownloadProgress<T extends AbstractDownloadProgress> {
    
    protected final float[] progresses;
    protected boolean alive = false;
    protected boolean started = false;
    protected boolean successful = false;
    
    public AbstractDownloadProgress(int expectedDownloads) {
        this.progresses = new float[expectedDownloads];
    }
    
    public boolean isAlive() {
        return alive;
    }
    
    public T setAlive(boolean alive) {
        this.alive = alive;
        return (T) this;
    }
    
    public boolean isStarted() {
        return started;
    }
    
    public T setStarted(boolean started) {
        this.started = started;
        return (T) this;
    }
    
    public boolean isSuccessful() {
        return successful;
    }
    
    public T setSuccessful(boolean successful) {
        this.successful = successful;
        return (T) this;
    }
    
    public T reset() {
        setAlive(false);
        setStarted(false);
        setSuccessful(false);
        synchronized (progresses) {
            Arrays.fill(progresses, 0.0F);
        }
        return (T) this;
    }
    
    public boolean start() {
        if (isAlive()) {
            return false;
        }
        reset();
        setAlive(true);
        setStarted(true);
        return true;
    }
    
    private float[] getProgresses() {
        return progresses;
    }
    
    public int getNextProgressIndex() {
        synchronized (progresses) {
            for (int i = 0; i < progresses.length; i++) {
                if (progresses[i] < 1.0F) {
                    return i;
                }
            }
            return -1;
        }
    }
    
    public float getProgress(int index) {
        synchronized (progresses) {
            return progresses[index];
        }
    }
    
    public double getProgressOverall() {
        synchronized (progresses) {
            double sum = 0.0;
            for (float f : progresses) {
                sum += f;
            }
            return sum / progresses.length;
        }
    }
    
    public T setProgress(int index, float progress) {
        synchronized (progresses) {
            progresses[index] = Math.min(1.0F, Math.max(progress, 0.0F));
            return (T) this;
        }
    }
    
    @Override
    public String toString() {
        synchronized (progresses) {
            return "AbstractDownloadProgress{" + "progresses=" + Arrays.toString(progresses) + ", alive=" + alive + ", started=" + started + ", successful=" + successful + '}';
        }
    }
    
}
