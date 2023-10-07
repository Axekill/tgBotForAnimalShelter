package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Users;
import pro.sky.telegrambot.repository.UsersRepository;
import pro.sky.telegrambot.service.UsersService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private static final String START = "/start";
    private static final String SHELTERS = "/shelters";
    private static final String VOLUNTEER = "/volunteer";
    private static final String CAT_SHELTERS = "ПРИЮТ ДЛЯ КОШЕК";
    private static final String DOG_SHELTERS = "ПРИЮТ ДЛЯ СОБАК";

    private final UsersRepository userRepository;
    private final UsersService usersService;


    @Autowired
    private TelegramBot telegramBot;

    public TelegramBotUpdatesListener(UsersRepository userRepository, UsersService usersService, TelegramBot telegramBot) {
        this.userRepository = userRepository;
        this.usersService = usersService;
        this.telegramBot = telegramBot;
    }


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Метод process, основной метод нашего бота. В нём будут содержаться, базовые ответы бота, пользователю
     * и обработка сообщений.
     * Используется метод {@link TelegramBotUpdatesListener#startCommand(Long, String, String, String)#unknownCommand(Long)}
     *
     * @param updates
     * @return произошедшие изменения
     */
    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            var textMessage = update.message();
            var telegramUser = textMessage.from();
            var users = registerUsers(telegramUser);

            var text = update.message().text();
            Long chatId = update.message().chat().id();
            switch (text) {
                case START -> {
                    String userName = update.message().chat().username();
                    String firstName = update.message().chat().firstName();
                    String lastName = update.message().chat().lastName();

                    startCommand(chatId, userName, firstName, lastName);

                }
                case SHELTERS -> {
                    telegramBot.execute(sheltersCommand(chatId));

                }
                case VOLUNTEER -> volunteerCommand(chatId);
                default -> unknownCommand(chatId);
            }
            buttonTap(chatId);
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    /**
     * Метод отвечающий за комманду start. Бот, приветствует пользователя в зависимости от его данных(имени, фамилии и никнейма)
     *
     * @param chatId
     * @param userName
     * @param firstName
     * @param lastName
     */
    private void startCommand(Long chatId, String userName, String firstName, String lastName) {
        String textChat = "Добро пожаловать в бот, %s ! %n" +
                "Здесь Вы сможете узнать о приютах для животных, а так же связаться с волонтером %n" +
                "Используйте следущие команды: %n" +
                "/shelters - выбор приюта %n" +
                "/volunteer - вызов волонтера";
        String fullName = userName + firstName + lastName;
        if (userName == null & lastName == null) {
            var formattedText = String.format(textChat, firstName);
            sendMessage(chatId, formattedText);
        } else if (fullName == null) {
            sendMessage(chatId, textChat);
        } else {
            var formattedText = String.format(textChat, userName);
            sendMessage(chatId, formattedText);
        }
    }

    private SendMessage sheltersCommand(Long chatId) {

        SendMessage message = new SendMessage(chatId, "Выберите приют");
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton("Приют для кошек");
        button1.callbackData(CAT_SHELTERS);

        InlineKeyboardButton button2 = new InlineKeyboardButton("Приют для собак");
        button2.callbackData(DOG_SHELTERS);

        rowInline.add(button1);
        rowInline.add(button2);

        rowsInline.add(rowInline);
        markup.addRow(button1, button2);
        message.replyMarkup(markup);

        return message;
    }

    private void volunteerCommand(Long chatId) {
        var text = "Повсем вопросам обращайтесь к https://t.me/Axekill93";
        sendMessage(chatId, text);
    }

    private void buttonTap(Long chatId) {
        CallbackQuery callbackQuery = new CallbackQuery();
        if (callbackQuery.data().equals(CAT_SHELTERS)) {
            String cat = "вы выбрали приют для кошек";
            sendMessage(chatId, cat);
        } else if (callbackQuery.data().equals(DOG_SHELTERS)) {
            String dog = "вы выбрпали приют для собак";
            sendMessage(chatId, dog);
        }


    }


    /**
     * Метод, созданный на случаи, когда боту задают вопросы не по алгоритму действий которые он предлагает.
     *
     * @param chatId
     */
    private void unknownCommand(Long chatId) {
        var text = "Не удалось распознать команду!";
        sendMessage(chatId, text);
    }

    private void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage(chatId, text);
        SendResponse response = telegramBot.execute(message);
    }

    private Users registerUsers(User telegramUser) {
        return usersService.findOrSaveUsers(telegramUser);
    }

}


