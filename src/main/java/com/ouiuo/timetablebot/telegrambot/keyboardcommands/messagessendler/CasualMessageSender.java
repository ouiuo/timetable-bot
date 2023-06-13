package com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler;

import com.ouiuo.timetablebot.model.TrainingPair;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands.*;

@Service
@RequiredArgsConstructor
public class CasualMessageSender implements MessageSender{

    private final TelegramLongPollingBot telegramBot;

    @SneakyThrows
    public void sendList(List<TrainingPair> trainingPairs, User user) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy \nEEEE", new Locale("ru"));
        if (!trainingPairs.isEmpty()) {
            Date prevClassDate = null;
            Date classDate;
            Iterator<TrainingPair> iterator = trainingPairs.iterator();
            while (iterator.hasNext()) {
                TrainingPair trainingPair = iterator.next();
                classDate = trainingPair.getStartDate();
                if (prevClassDate == null || prevClassDate.getDate() != classDate.getDate()) {
                    sendMarkdownText(user.getId(), "`Распиание на " + simpleDateFormat.format(classDate) + "`" + (classDate.getDay() == 3 ? "\uD83D\uDC38" : ""));
                }
                prevClassDate = classDate;

                if (iterator.hasNext()) {
                    sendText(user.getId(), trainingPair.toStringBuffer().toString());
                } else {
                    sendTextWithButtons(user.getId(), trainingPair.toStringBuffer().toString());
                }
            }

        } else {
            sendText(user.getId(), "Нет занятий");
        }
    }

    @SneakyThrows
    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what)
                .build();

        telegramBot.execute(sm);
    }

    @SneakyThrows
    public void sendMarkdownText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what)
                .build();
        sm.enableMarkdown(true);
        telegramBot.execute(sm);
    }

    @SneakyThrows
    public void sendTextWithButtons(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what)
                .replyMarkup(replyKeyboardMarkup())
                .build();

        telegramBot.execute(sm);
    }

    public ReplyKeyboardMarkup replyKeyboardMarkup() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(false);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        replyKeyboardMarkup.setKeyboard(keyboardRows());

        return replyKeyboardMarkup;
    }

    public List<KeyboardRow> keyboardRows() {
        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(new KeyboardRow(keyboardButtonsFirstLine()));
//        rows.add(new KeyboardRow(keyboardButtonsSecondLine()));
        return rows;
    }

    public List<KeyboardButton> keyboardButtonsSecondLine() {
        List<KeyboardButton> buttons = new ArrayList<>();
        buttons.add(new KeyboardButton(TILL_DATE.getCommand()));
        return buttons;
    }

    public List<KeyboardButton> keyboardButtonsFirstLine() {
        List<KeyboardButton> buttons = new ArrayList<>();
        buttons.add(new KeyboardButton(TODAY.getCommand()));
        buttons.add(new KeyboardButton(TOMORROW.getCommand()));
        buttons.add(new KeyboardButton(WEEK.getCommand()));
        buttons.add(new KeyboardButton(ON_DATE.getCommand()));

        return buttons;
    }

}
