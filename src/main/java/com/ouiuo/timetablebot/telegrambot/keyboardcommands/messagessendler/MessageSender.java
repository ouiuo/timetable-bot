package com.ouiuo.timetablebot.telegrambot.keyboardcommands.messagessendler;

import com.ouiuo.timetablebot.model.TrainingPair;
import com.ouiuo.timetablebot.model.UserModel;
import com.ouiuo.timetablebot.telegrambot.keyboardcommands.KeyboardCommandsProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public interface MessageSender {

    @Autowired
    default void register(List<KeyboardCommandsProcessor> keyboardCommandsProcessors) {
        keyboardCommandsProcessors.forEach(keyboardCommandsProcessor ->
                keyboardCommandsProcessor.register(this));
    }

    MessageType getType();

    void sendList(List<TrainingPair> trainingPairs, UserModel user);

    void sendText(Long who, String what);

    void sendMarkdownText(Long who, String what);

    void sendTextWithButtons(UserModel userModel, String what);


    void sendTextWithCancelButton(UserModel userModel, String what);

    ReplyKeyboardMarkup replyKeyboardMarkup(UserModel userModel);

    List<KeyboardRow> keyboardRows(UserModel userModel);

}
