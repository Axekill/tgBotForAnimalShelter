package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import jdk.jfr.Enabled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service

public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private static final String START = "/start";

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Метод process, основной метод нашего бота. В нём будут содержаться, базовые ответы бота, пользователю
     * и обработка сообщений.
     *Используется метод {@link TelegramBotUpdatesListener#startCommand(Long, String, String, String)#unknownCommand(Long)}
     * @param updates
     * @return произошедшие изменения
     */
    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            var text = update.message().text();
            Long chatId = update.message().chat().id();
            switch (text) {
                case START -> {
                    String userName = update.message().chat().username();
                    String firstName = update.message().chat().firstName();
                    String lastName = update.message().chat().lastName();
                    startCommand(chatId, userName, firstName, lastName);


                }
                default -> unknownCommand(chatId);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
    // проверка


    private void startCommand(Long chatId, String userName, String firstName, String lastName) {
        String textChat;
        String fullName = userName + firstName + lastName;
        if (userName == null & lastName == null) {
            textChat = "Добро пожаловать в бот, %s !";
            var formattedText = String.format(textChat, firstName);
            sendMessage(chatId, formattedText);


        } else if (fullName == null) {
            textChat = "Добро пожаловать в бот";
            sendMessage(chatId, textChat);
        } else {
            textChat = "Добро пожаловать в бот, %s !";
            var formattedText = String.format(textChat, userName);
            sendMessage(chatId, formattedText);
        }
    }

    private void unknownCommand(Long chatId) {
        var text = "Не удалось распознать команду!";
        sendMessage(chatId, text);
    }

    private void sendMessage(Long chatId, String text) {

        SendMessage message = new SendMessage(chatId,text);
        SendResponse response = telegramBot.execute(message);

    }


}
