package com.ouiuo.timetablebot.telegrambot;

import com.ouiuo.timetablebot.model.TrainingPair;
import com.ouiuo.timetablebot.service.TimetableService;
import com.ouiuo.timetablebot.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final TelegramBotsApi telegramBotsApi;

    private final String BOT_NAME = "РГЭУ (РИНХ) Расписание";
    private final String TOKEN;

    private final TimetableService timetableService;
    private final UserService userService;

    @SneakyThrows
    public TelegramBot(TelegramBotsApi telegramBotsApi, @Value("${telegram.token}") String token, TimetableService timetableService, UserService userService) {
        this.telegramBotsApi = telegramBotsApi;
        TOKEN = token;
        telegramBotsApi.registerBot(this);
        this.timetableService = timetableService;
        this.userService = userService;
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

        if (msgText.contains("/today")) {
            sendList(timetableService.getToday(), user);
        } else if (msgText.contains("/tomorrow")) {
            sendList(timetableService.getTomorrow(), user);
        } else if (msgText.contains("/cringe")) {
            sendImage(user);
        } else {
            sendText(user.getId(), "The command not supported. \n" +
                    "/today");
        }
        userService.updateOnline(user);
    }

    @SneakyThrows
    private void sendList(List<TrainingPair> trainingPairs, User user) {
        Date lastUpdate = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy \nEEEE", new Locale("ru"));
        List<List<InlineKeyboardButton>> classesList = new ArrayList<>();
        if (!trainingPairs.isEmpty()) {
            sendText(user.getId(), "Распиание на " +  simpleDateFormat.format(trainingPairs.get(0).getStartDate()));
        }
        for (TrainingPair pair : trainingPairs) {
            sendText(user.getId(), pair.toStringBuffer().toString());
            lastUpdate = pair.getUpdateDate();
        }
        sendTextWithButtons(user.getId(), "Last update: " + lastUpdate);
    }

    public void sendTextWithButtons(Long who, String what) throws TelegramApiException {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what)
                .replyMarkup(new InlineKeyboardMarkup(Collections.singletonList(getTodayTomorrowButtons())))
                .build();

        execute(sm);
    }

    @SneakyThrows
    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what)
                .build();

        execute(sm);
    }

    private List<InlineKeyboardButton> getTodayTomorrowButtons() {
        ArrayList<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        inlineKeyboardButtons.add(inlineKeyboardButtonCreator("Сегодня", "/today"));
        inlineKeyboardButtons.add(inlineKeyboardButtonCreator("Завтра", "/tomorrow"));
        return inlineKeyboardButtons;
    }

    private InlineKeyboardButton inlineKeyboardButtonCreator(String text, String callbackData) {
        return InlineKeyboardButton.builder().text(text).callbackData(callbackData).build();
    }

    @SneakyThrows
    public void sendImage(User user) {
        new File("/img/cringe/img.png");
        ClassPathResource classPathResource = new ClassPathResource("img/cringe/img.png");
        classPathResource.getInputStream();

        InputFile inputFile = new InputFile(classPathResource.getInputStream(), "img/cringe/img.png");
        SendPhoto sendPhoto = SendPhoto.builder()
                .chatId(user.getId().toString())
                .photo(inputFile)
                .build();
        execute(sendPhoto);
    }
}
