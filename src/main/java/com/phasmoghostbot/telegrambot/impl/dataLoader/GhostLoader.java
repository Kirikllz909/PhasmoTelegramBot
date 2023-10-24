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
import com.phasmoghostbot.telegrambot.models.Ability;
import com.phasmoghostbot.telegrambot.models.Evidence;
import com.phasmoghostbot.telegrambot.models.Ghost;
import com.phasmoghostbot.telegrambot.models.HuntStartingCondition;

public class GhostLoader implements DataLoader<Ghost> {

    Logger logger = LogManager.getLogger(GhostLoader.class);
    List<Evidence> evidenceList = null;

    @Override
    public List<Ghost> load(String filePath) {
        try {
            List<Ghost> ghostList = new ArrayList<>();
            evidenceList = new EvidenceLoader().load(filePath);

            NodeList ghosts = getAllGhostsFromXMLFile(filePath).getChildNodes();

            for (int i = 1; ghosts.item(i) != null; i += 2) {
                ghostList.add(generateGhostFromXMLNode(ghosts.item(i)));
            }

            return ghostList;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    private static Node getAllGhostsFromXMLFile(String fileName) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(fileName);

        return document.getDocumentElement().getElementsByTagName("Ghosts").item(0);
    }

    private Ghost generateGhostFromXMLNode(Node ghostNode) {
        NodeList ghostNodeElements = ghostNode.getChildNodes();

        final Integer NEXT_ELEMENT_NODE = 2;

        String Name = ghostNodeElements.item(1).getTextContent();
        NodeList GhostEvidences = ghostNodeElements.item(1 + NEXT_ELEMENT_NODE).getChildNodes();
        NodeList GhostSpecialAbilities = ghostNodeElements.item(1 + 2 * NEXT_ELEMENT_NODE).getChildNodes();
        NodeList HuntStartingSanity = ghostNodeElements.item(1 + 3 * NEXT_ELEMENT_NODE).getChildNodes();
        String Speed = ghostNodeElements.item(1 + 4 * NEXT_ELEMENT_NODE).getFirstChild().getTextContent();
        String BlinkFrequency = ghostNodeElements.item(1 + 5 * NEXT_ELEMENT_NODE).getFirstChild().getTextContent();

        Ghost ghost = new Ghost();
        ghost.setName(Name);
        ghost.setGhostEvidences(generateEvidencesFromXMLNodeList(GhostEvidences));
        ghost.setSpecialAbilities(generateAbilitiesFromXMLNodeList(GhostSpecialAbilities));
        ghost.setHuntStartingSanity(generateHuntStartingConditionsFromXMLNodeList(HuntStartingSanity));
        ghost.setSpeed(Speed);
        ghost.setBlinkFrequency(BlinkFrequency);

        return ghost;
    }

    private List<Evidence> generateEvidencesFromXMLNodeList(NodeList evidences) {
        List<Evidence> currentGhostEvidences = new ArrayList<Evidence>();

        for (int i = 1; evidences.item(i) != null; i += 2) {
            Node currentEvidence = evidences.item(i);
            String evidenceRefId = currentEvidence.getChildNodes().item(1).getFirstChild().getTextContent();
            currentGhostEvidences.add(findEvidenceById(evidenceRefId));
        }

        return currentGhostEvidences;
    }

    private Evidence findEvidenceById(String evidenceId) {
        if (evidenceList == null)
            return null;

        Evidence evidence = null;
        for (int i = 0; i < evidenceList.size(); i++) {
            Evidence currentComparingEvidence = evidenceList.get(i);
            if (evidenceId.equals(currentComparingEvidence.getId())) {
                evidence = currentComparingEvidence;
                break;
            }
        }

        return evidence;
    }

    private List<Ability> generateAbilitiesFromXMLNodeList(NodeList abilities) {
        List<Ability> generatedAbilities = new ArrayList<Ability>();

        for (int i = 1; abilities.item(i) != null; i += 2) {
            Node currentAbility = abilities.item(i);
            NodeList currentAbilityElements = currentAbility.getChildNodes();

            String Name = currentAbilityElements.item(1).getFirstChild().getTextContent();
            String Description = currentAbilityElements.item(3).getFirstChild().getTextContent();
            generatedAbilities.add(new Ability(Name, Description));
        }

        return generatedAbilities;
    }

    private List<HuntStartingCondition> generateHuntStartingConditionsFromXMLNodeList(NodeList huntStartingSanity) {
        List<HuntStartingCondition> generatedHuntStartingConditions = new ArrayList<HuntStartingCondition>();

        for (int i = 1; huntStartingSanity.item(i) != null; i += 2) {
            Node currentHuntStartingCondition = huntStartingSanity.item(i);
            NodeList currentHuntStartingConditionElements = currentHuntStartingCondition.getChildNodes();

            String condition = currentHuntStartingConditionElements.item(1).getFirstChild().getTextContent();
            Integer value = Integer
                    .parseInt(currentHuntStartingConditionElements.item(3).getFirstChild().getTextContent());
            generatedHuntStartingConditions.add(new HuntStartingCondition(condition, value));
        }

        return generatedHuntStartingConditions;
    }
}
