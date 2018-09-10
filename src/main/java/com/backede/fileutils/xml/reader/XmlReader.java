/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backede.fileutils.xml.reader;

import java.io.File;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Slf4j
public class XmlReader<T> {

    public Optional<T> readXml(String filePath, Class clazz) {

        try {
            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            T data = (T) jaxbUnmarshaller.unmarshal(file);

            return Optional.ofNullable(data);

        } catch (JAXBException ex) {
            log.error("Error when reading XML file from path {} with Class {}", filePath, clazz.getName(), ex);
        }
        return Optional.empty();
    }

    public Optional<T> readXml(File file, Class clazz) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            T data = (T) jaxbUnmarshaller.unmarshal(file);

            return Optional.ofNullable(data);

        } catch (JAXBException ex) {
            log.error("Error when reading XML file from path {} with Class {} ", file.getAbsolutePath(), clazz.getName(), ex);
        }
        return Optional.empty();
    }

}
