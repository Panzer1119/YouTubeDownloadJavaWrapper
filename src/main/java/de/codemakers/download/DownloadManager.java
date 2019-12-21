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

import de.codemakers.base.CJP;
import de.codemakers.base.util.interfaces.Stoppable;
import de.codemakers.base.util.tough.ToughRunnable;
import de.codemakers.base.util.tough.ToughSupplier;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class DownloadManager implements Stoppable {
    
    private final CJP cjp;
    
    public DownloadManager() {
        this(CJP.createInstance());
    }
    
    public DownloadManager(CJP cjp) {
        this.cjp = cjp;
    }
    
    @Override
    public boolean stop() throws Exception {
        cjp.stopExecutorServiceNow();
        return true;
    }
    
    protected Future<?> submit(ToughRunnable toughRunnable) {
        return submitIntern(toughRunnable::runWithoutException);
    }
    
    protected <T> Future<T> submit(ToughSupplier<T> toughSupplier) {
        return submitIntern(toughSupplier::getWithoutException);
    }
    
    private Future<?> submitIntern(Runnable runnable) {
        return cjp.getFixedExecutorService().submit(runnable);
    }
    
    private <T> Future<T> submitIntern(Callable<T> callable) {
        return cjp.getFixedExecutorService().submit(callable);
    }
    
    public DownloadProgress submitDownload(DownloadInfo downloadInfo) {
        final DownloadProgress downloadProgress = new DownloadProgress(downloadInfo);
        submit(() -> YouTubeDL.downloadDirect(downloadProgress));
        return downloadProgress;
    }
    
}