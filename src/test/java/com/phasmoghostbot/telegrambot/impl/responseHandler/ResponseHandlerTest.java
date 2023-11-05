package com.phasmoghostbot.telegrambot.impl.responseHandler;

import static org.mockito.Mockito.times;

import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.db.MapDBContext;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import com.phasmoghostbot.telegrambot.constants.Constants;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.selection.SelectKeyboardFactory;

public class ResponseHandlerTest {

    private static ResponseHandler handler;
    private static SilentSender sender;
    private static DBContext db;

    private static Map<Long, Integer> firstMessagesId;

    private static final long CHAT_ID = 1337L;
    private static final int MESSAGE_ID = 1337;

    @BeforeAll
    public static void setUp() {
        db = MapDBContext.offlineInstance("TEST");

        firstMessagesId = db.getMap(Constants.FIRST_MESSAGES);

        sender = Mockito.mock(SilentSender.class);

        handler = new ResponseHandler(sender, db);
    }

    // Test if method sending message
    @Test
    void testReplyToStart() {
        SendMessage expectedMsg = new SendMessage();
        expectedMsg.setText(Constants.SELECT_MESSAGE);
        expectedMsg.setChatId(CHAT_ID);
        expectedMsg.setReplyMarkup(new SelectKeyboardFactory().generateKeyboard());

        handler.replyToStart(CHAT_ID);

        Mockito.verify(sender, times(1)).execute(expectedMsg);
        db.clear();
    }

    @Test
    void testReplyToStartAfterStartWasCalled() {
        firstMessagesId.put(CHAT_ID, MESSAGE_ID);
        EditMessageText expectedMsg = new EditMessageText();
        expectedMsg.setChatId(CHAT_ID);
        expectedMsg.setReplyMarkup((InlineKeyboardMarkup) new SelectKeyboardFactory().generateKeyboard());
        expectedMsg.setText(Constants.SELECT_MESSAGE);
        expectedMsg.setMessageId(firstMessagesId.get(CHAT_ID));

        handler.replyToStart(CHAT_ID);

        Mockito.verify(sender, times(1)).execute(expectedMsg);
        db.clear();
    }
}
