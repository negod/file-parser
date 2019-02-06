/*
* Tutorial here https://docs.oracle.com/javase/tutorial/essential/io/notification.html
 */
package com.backede.fileutils.listener;

import com.backede.fileutils.exception.BeckedeFileException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.function.Consumer;
import javax.swing.Timer;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author joaki
 */
@Slf4j
@Getter
public class FolderListener extends Thread {

    private final Path folderPath;
    private final Integer timerInterval;
    private final Consumer<File> consumer;

    public FolderListener(Path folderPath, Integer timerInterval, Consumer<File> consumer) throws BeckedeFileException, IOException {

        this.timerInterval = timerInterval;
        this.folderPath = folderPath;
        this.consumer = consumer;

        File file = this.folderPath.toFile();

        if (!file.isDirectory()) {
            log.error("Path need to point to a folder. Path = {}", file.getAbsolutePath());
            throw new BeckedeFileException("Path need to point to a folder");
        }

        if (this.timerInterval < 1000) {
            log.error("Timerinterval needs to be at least 1 second. Interval ( ms ) = {}", timerInterval);
            throw new BeckedeFileException("Timerinterval needs to be at least 1 second. Interval ( ms ) = {}");
        }

    }

    @Override
    public void run() {

        try {

            WatchService watcher = FileSystems.getDefault().newWatchService();

            WatchKey key = folderPath.register(watcher,
                    ENTRY_CREATE,
                    ENTRY_MODIFY);

            Timer timer = new Timer(timerInterval, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {

                    try {
                        WatchKey key = watcher.take();

                        for (WatchEvent<?> event : key.pollEvents()) {
                            WatchEvent<Path> ev = (WatchEvent<Path>) event;
                            Path filename = ev.context();
                            consumer.accept(folderPath.resolve(filename).toFile());
                        }

                    } catch (InterruptedException ex) {
                        log.error("Error when getting folder changes {}", ex.getStackTrace());
                    }
                }

            });
            timer.setRepeats(true);
            timer.start();

        } catch (IOException ex) {
            log.error("Error when initializing timer {}", ex.getStackTrace());
        }

    }

}
