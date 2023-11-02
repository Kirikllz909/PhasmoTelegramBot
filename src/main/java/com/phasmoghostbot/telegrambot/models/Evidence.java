package com.phasmoghostbot.telegrambot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evidence {
    private String Id;
    private String Name;
    private String Mechanics;

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (this == obj)
            return true;

        if (obj instanceof Evidence) {
            Evidence other = (Evidence) obj;

            if (!Id.equals(other.Id))
                return false;
            if (!Name.equals(other.Name))
                return false;
            if (!Mechanics.equals(other.Mechanics))
                return false;
        }
        return true;
    }
}
