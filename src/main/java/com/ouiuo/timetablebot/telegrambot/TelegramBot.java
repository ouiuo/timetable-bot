package com.ouiuo.timetablebot.telegrambot;

import com.ouiuo.timetablebot.model.History;
import com.ouiuo.timetablebot.service.HistoryService;
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
import java.util.Optional;

import static com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands.getCommand;
import static com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands.getPriorityCommand;

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final TelegramBotsApi telegramBotsApi;

    private final String BOT_NAME = "РГЭУ (РИНХ) Расписание";
    @Value("${telegram.token}")
    private final String TOKEN;

    private final HistoryService historyService;

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
        Optional<History> last = historyService.findLast(user.getId());
        KeyboardCommands command = getCommand(msgText);
        if (last.isPresent()) {
            command = getPriorityCommand(command, last.get().getKeyboardCommands());
        }
        keyboardMapProcessors.get(command).process(user, msgText, command);
    }

    public void registrateCommandProcessor(KeyboardCommands command, KeyboardCommandsProcessor keyboardCommandsProcessor) {
        keyboardMapProcessors.put(command, keyboardCommandsProcessor);
    }
}
