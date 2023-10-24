package com.phasmoghostbot.telegrambot.models;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Ghost {
    private String Name;
    private List<Evidence> GhostEvidences;
    private List<Ability> SpecialAbilities;
    private List<HuntStartingCondition> HuntStartingSanity;
    private String Speed;
    private String BlinkFrequency;
}
