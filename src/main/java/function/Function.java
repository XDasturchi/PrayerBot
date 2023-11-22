package function;

import bot.PrayerBot;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Function {

    private static Function function = null;


    public void sendMessage(long id, String text, ReplyKeyboard keyboard) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(id);
        sendMessage.setReplyMarkup(keyboard);
        sendMessage.setParseMode("html");

        new PrayerBot().telegramExecute(sendMessage);
    }


    public static Function getInstance() {
        if (function == null) function = new Function();
        return function;
    }
}