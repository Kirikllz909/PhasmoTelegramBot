package com.phasmoghostbot.telegrambot.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GhostSearchParameters {
    private String speed;
    private Integer currentSanity;
    private List<Evidence> evidences;

    public GhostSearchParameters() {
        speed = "Default";
        currentSanity = 100;
        evidences = new ArrayList<Evidence>();
    }
}
