package com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler;

import com.ouiuo.timetablebot.model.TrainingPair;
import com.ouiuo.timetablebot.model.UserModel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands.CANCEL;

@Service
@RequiredArgsConstructor
public class ChooseDateMessageSender implements MessageSender {

    private final TelegramLongPollingBot telegramBot;

    @SneakyThrows
    public void sendList(List<TrainingPair> trainingPairs, UserModel user) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy \nEEEE", new Locale("ru"));
        if (!trainingPairs.isEmpty()) {
            Date date = null;
            Date currentDate;
            Iterator<TrainingPair> iterator = trainingPairs.iterator();
            while (iterator.hasNext()) {
                TrainingPair trainingPair = iterator.next();
                currentDate = trainingPair.getStartDate();
                if (date == null || date.getDate() != currentDate.getDate()) {
                    sendMarkdownText(user.getId(), "`Распиание на " + simpleDateFormat.format(currentDate) + "`" + (currentDate.getDay() == 3 ? "\uD83D\uDC38" : ""));
                }
                date = currentDate;

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
        List<KeyboardRow> rows = new ArrayList<>(keyboardRowsCalendar());
        rows.add(new KeyboardRow(keyboardButtonsFirstLine()));
        return rows;
    }

    public List<KeyboardRow> keyboardRowsCalendar() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM", new Locale("ru"));
        DateTime now = DateTime.now(DateTimeZone.forID("Europe/Moscow"));
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<KeyboardButton> buttons = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                int dayOfWeek = now.getDayOfWeek() - 1;
                KeyboardButton keyboardButton;
                if (dayOfWeek == j) {
                    keyboardButton = new KeyboardButton(simpleDateFormat.format(now.toDate()));
                    now = now.plusDays(1);
                } else {
                    keyboardButton = new KeyboardButton("-");
                }
                buttons.add(keyboardButton);
            }
            keyboardRows.add(new KeyboardRow(buttons));
        }
        return keyboardRows;
    }

    public List<KeyboardButton> keyboardButtonsFirstLine() {
        List<KeyboardButton> buttons = new ArrayList<>();
        buttons.add(new KeyboardButton(CANCEL.getCommand()));
        return buttons;
    }

}
