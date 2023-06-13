package com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler;

import com.ouiuo.timetablebot.model.TrainingPair;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static com.ouiuo.timetablebot.telegrambot.keyboardcommands.enums.KeyboardCommands.*;

public interface MessageSender {


    void sendList(List<TrainingPair> trainingPairs, User user);

    void sendText(Long who, String what);

    void sendMarkdownText(Long who, String what);

    void sendTextWithButtons(Long who, String what);

    ReplyKeyboardMarkup replyKeyboardMarkup();

    List<KeyboardRow> keyboardRows();

}
