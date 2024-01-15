package com.ouiuo.timetablebot.telegrambot;

import com.ouiuo.timetablebot.model.state.State;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.KeyboardCommandsProcessor;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashMap;
import java.util.Map;

import static com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands.getCommand;

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final TelegramBotsApi telegramBotsApi;

    private final String BOT_NAME = "РГЭУ (РИНХ) Расписание";
    @Value("${telegram.token}")
    private final String TOKEN;

    private final Map<KeyboardCommands, KeyboardCommandsProcessor> keyboardMapProcessors = new HashMap<>();


    @PostConstruct
    @SneakyThrows
    public void postConstruct() {
        telegramBotsApi.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        User user = null;
        String msgText = "";

        if (update.hasMessage()) {
            var msg = update.getMessage();
            user = msg.getFrom();
            msgText = msg.getText();
        } else if (update.hasCallbackQuery()) {
            var callbackQuery = update.getCallbackQuery();
            user = callbackQuery.getFrom();
            msgText = callbackQuery.getData();
        }
        KeyboardCommands command = getCommand(msgText);


        keyboardMapProcessors.get(command).process(user, msgText);
    }

    public void registrateCommandProcessor(KeyboardCommands command, KeyboardCommandsProcessor keyboardCommandsProcessor) {
        keyboardMapProcessors.put(command, keyboardCommandsProcessor);
    }
}
