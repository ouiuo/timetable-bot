package com.ouiuo.timetablebot.telegrambot;

import com.ouiuo.timetablebot.model.TrainingPair;
import com.ouiuo.timetablebot.service.TimetableService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final TelegramBotsApi telegramBotsApi;

    private final String BOT_NAME = "РГЭУ (РИНХ) Расписание";

    private final String TOKEN = "${telegram.token}";

    @Autowired
    private TimetableService timetableService;

    @SneakyThrows
    public TelegramBot(TelegramBotsApi telegramBotsApi) {
        this.telegramBotsApi = telegramBotsApi;
        telegramBotsApi.registerBot(this);
        List<BotCommand> botCommandList = new ArrayList<>();
        botCommandList.add(new BotCommand("/today", "Today's classes"));
        botCommandList.add(new BotCommand("/tomorrow", "Tomorrow's classes"));
        botCommandList.add(new BotCommand("/cringe", "Ohno cringe"));
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
        var msg = update.getMessage();
        var user = msg.getFrom();
        String msgText = msg.getText();
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
    }

    private void sendList(List<TrainingPair> trainingPairs, User user) {
        Date lastUpdate = null;
        for (TrainingPair pair : trainingPairs) {
            sendText(user.getId(), pair.toString());
            lastUpdate = pair.getUpdateDate();
        }
        sendText(user.getId(), "last update: " + lastUpdate + "\n" +
                "/today");
    }

    @SneakyThrows
    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what).build();
        execute(sm);
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
