package com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler;

import com.ouiuo.timetablebot.model.TrainingPair;
import com.ouiuo.timetablebot.model.UserModel;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public interface MessageSender {


    void sendList(List<TrainingPair> trainingPairs, UserModel user);

    void sendText(Long who, String what);

    void sendMarkdownText(Long who, String what);

    void sendTextWithButtons(Long who, String what);

    ReplyKeyboardMarkup replyKeyboardMarkup();

    List<KeyboardRow> keyboardRows();

}
