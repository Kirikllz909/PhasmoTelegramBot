package com.phasmoghostbot.telegrambot.bot;

import java.util.Arrays;
import java.util.function.BiConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.bot.BaseAbilityBot;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Flag;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.abilitybots.api.objects.Reply;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.phasmoghostbot.telegrambot.constants.Constants;
import com.phasmoghostbot.telegrambot.impl.responseHandler.ResponseHandler;

@Component
public class PhasmoGhostBot extends AbilityBot {

    @Autowired
    private static Environment env;

    private ResponseHandler responseHandler;

    public PhasmoGhostBot() {
        super(env.getProperty("BOT_TOKEN"), env.getProperty("BOT_USERNAME"));
        responseHandler = new ResponseHandler(silent, db);
    }

    public PhasmoGhostBot(DBContext db) {
        super(env.getProperty("BOT_TOKEN"), env.getProperty("BOT_USERNAME"), db);
        responseHandler = new ResponseHandler(silent, this.db);
    }

    @Override
    public long creatorId() {
        return Long.valueOf(env.getProperty("CREATOR_ID"));
    }

    public void setResponseHandler(ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }

    public Ability startBot() {
        return Ability
                .builder()
                .name("start")
                .info(Constants.START_DESCRIPTION)
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .action(ctx -> responseHandler.replyToStart(ctx.chatId()))
                .build();
    }

    public Reply replyToButtons() {
        BiConsumer<BaseAbilityBot, Update> action = (abilityBot, update) -> responseHandler.replyToButtons(
                update.getMessage().getChatId(),
                update.getCallbackQuery().getData());
        return Reply.of(action, Arrays.asList(Flag.CALLBACK_QUERY));
    }
}
