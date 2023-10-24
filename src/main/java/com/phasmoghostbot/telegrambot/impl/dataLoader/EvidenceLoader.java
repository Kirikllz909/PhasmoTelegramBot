package com.phasmoghostbot.telegrambot.impl.dataLoader;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.phasmoghostbot.telegrambot.api.dataLoader.DataLoader;
import com.phasmoghostbot.telegrambot.models.Evidence;

public class EvidenceLoader implements DataLoader<Evidence> {
    Logger logger = LogManager.getLogger(EvidenceLoader.class);

    @Override
    public List<Evidence> load(String filePath) {
        try {
            List<Evidence> evidenceList = new ArrayList<>();

            NodeList evidences = getAllEvidencesFromXMLFile(filePath).getChildNodes();

            for (int i = 1; evidences.item(i) != null; i += 2) {
                evidenceList.add(generateEvidenceFromXMLNode(evidences.item(i)));
            }

            return evidenceList;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    private static Node getAllEvidencesFromXMLFile(String fileName) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(fileName);

        return document.getDocumentElement().getElementsByTagName("Evidences").item(0);
    }

    private Evidence generateEvidenceFromXMLNode(Node currentEvidence) {
        NodeList currentEvidenceElements = currentEvidence.getChildNodes();

        final Integer NEXT_ELEMENT_NODE = 2;

        String Id = currentEvidenceElements.item(1).getFirstChild().getTextContent();
        String Name = currentEvidenceElements.item(1 + NEXT_ELEMENT_NODE).getFirstChild().getTextContent();
        String Mechanics = currentEvidenceElements.item(1 + 2 * NEXT_ELEMENT_NODE).getFirstChild().getTextContent();

        return new Evidence(Id, Name, Mechanics);
    }
}
