/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backede.fileutils.listener;

import com.backede.fileutils.exception.BeckedeFileException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author joaki
 */
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FolderListenerExecutionTest {

    private static final Path ROOT = Paths.get("target");
    private static final Path FILE_PATH = Paths.get("/test.tst");
    private static final Path FOLDER_PATH = Paths.get(ROOT.toString().concat("/testfolder"));

    private static final Path FILE_FOLDER_PATH = Paths.get(FOLDER_PATH.toString().concat(FILE_PATH.toString()));

    private final Integer TIMER_INTERVAL = 1000;

    private static final Consumer<File> CONSUMER = file -> {
        System.out.println("Default consumer");
    };

    public FolderListenerExecutionTest() {
    }

    @Before
    public void init() throws IOException {
        Files.deleteIfExists(FILE_PATH);
        Files.deleteIfExists(FILE_FOLDER_PATH);
        Files.deleteIfExists(FOLDER_PATH);
    }

    @AfterClass
    public static void destroy() throws IOException {
        Files.deleteIfExists(FILE_PATH);
        Files.deleteIfExists(FILE_FOLDER_PATH);
        Files.deleteIfExists(FOLDER_PATH);
    }

    @Test
    public void testFileIsImported() throws BeckedeFileException, IOException, InterruptedException {
        System.out.println("testFileIsImported");

        CountDownLatch countDown = new CountDownLatch(1);

        Consumer<File> consumer = file -> {
            assertTrue("File need to exist!", file.exists());
            countDown.countDown();
        };

        Files.createDirectory(FOLDER_PATH);
        Integer sleepTimer = TIMER_INTERVAL + 1000;

        FolderListener listener = new FolderListener(FOLDER_PATH, TIMER_INTERVAL, consumer);
        listener.run();

        List<String> lines = Arrays.asList("The first line", "The second line");
        Files.write(FILE_FOLDER_PATH, lines, Charset.forName("UTF-8"));

        //wait until the message is received, but not more than one second
        //await returns false if it reaches the timeout
        assertTrue(countDown.await(sleepTimer, TimeUnit.MILLISECONDS));
    }

}
