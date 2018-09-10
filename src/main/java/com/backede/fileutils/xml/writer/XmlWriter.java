/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backede.fileutils.xml.writer;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Slf4j
public class XmlWriter {

    public static void writeXml(String filePath, Class clazz, Object data) {
        try {
            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(data, file);
        } catch (JAXBException ex) {
            log.error("Error when writing XML file to path {} with Class {} and data {}", filePath, clazz.getName(), data.toString(), ex);
        }
    }

    public static void writeXml(File file, Class clazz, Object data) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(data, file);
        } catch (JAXBException ex) {
            log.error("Error when writing XML file to path {} with Class {} and data {}", file.getAbsolutePath(), clazz.getName(), data.toString(), ex);
        }
    }

}
