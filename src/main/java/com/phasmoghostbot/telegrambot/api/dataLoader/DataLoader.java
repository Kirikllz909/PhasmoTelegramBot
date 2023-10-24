package com.phasmoghostbot.telegrambot.api.dataLoader;

import java.util.List;

public interface DataLoader<E> {
    public List<E> load(String filePath);
}
