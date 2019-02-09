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
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Joakim Backdede <joakim.backede@outlook.com>
 */
@Slf4j
@Getter
public class FolderListener extends Thread {

    private final Path folderPath;
    private final Consumer<File> consumer;
    private final WatchService watcher;
    private final WatchKey key;

    public FolderListener(Path folderPath, Consumer<File> consumer, Boolean createFolder) throws BeckedeFileException, IOException {

        this.folderPath = folderPath;
        this.consumer = consumer;

        File file = this.folderPath.toFile();

        if (!file.exists() && createFolder) {
            Files.createDirectory(folderPath);
        }

        if (!file.isDirectory()) {
            log.error("Path need to point to a folder. Path = {}", file.getAbsolutePath());
            throw new BeckedeFileException("Path need to point to a folder");
        }

        this.watcher = FileSystems.getDefault().newWatchService();
        this.key = folderPath.register(watcher,
                ENTRY_CREATE
        );

    }

    private void checkForNewFiles(Path folderPath) {
        File folder = folderPath.toFile();
        for (File listFile : folder.listFiles()) {
            consumer.accept(listFile);
        }
    }

    @Override
    public void run() {

        checkForNewFiles(folderPath);

        while (true) {
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

    }
}
