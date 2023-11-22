package messageBuilder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import user.User;
import user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageBuilder {
    private static final MessageBuilder messageBuilder = new MessageBuilder();
    private UserRepository userRepository = UserRepository.getInstance();

    public SendMessage messageStart(long userId) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.setText("<b>Assalomu Alaykum botimizga xush kelibsiz!</b>\n\n <b><tg-spoiler>\uD83D\uDD04 Botimiz orqali siz har namoz vaqti kirganida sizga xabar kelib turadi!</tg-spoiler></b>\n\n✅ <b><i>Marhamat uzingizga moslab sozlab oling!</i></b>\n\n\uD83D\uDD1B <b>Yoniq: Bomdod ✅\n\uD83D\uDCF4O'chiq: Bomdod ❌</b>");

        sendMessage.setChatId(userId);
        sendMessage.setParseMode("html");
        sendMessage.setReplyMarkup(getButton(userId));

        return sendMessage;
    }

    public SendMessage sendChanged(long userId) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(userId);
        sendMessage.setText("✅ <b>O'zgartirildi!</b>");
        sendMessage.setParseMode("html");
        sendMessage.setReplyMarkup(getButton(userId));

        return sendMessage;
    }


    private ReplyKeyboardMarkup getButton(long id) {
        Optional<User> getUser = userRepository.findById(id);
        User user = getUser.get();

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        // Birinchi Keyboard Qatori
        KeyboardRow keyboardRow1 = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton(user.isMorningStatus() ? "✅ Bomdod" : "❌ Bomdod");
        KeyboardButton keyboardButton2 = new KeyboardButton(user.isNoonStatus() ? "✅ Peshin" : "❌ Peshin");
        keyboardRow1.add(keyboardButton);
        keyboardRow1.add(keyboardButton2);

        // Ikkinchi Qator
        KeyboardRow keyboardRow2 = new KeyboardRow();
        KeyboardButton keyboardButton3 = new KeyboardButton(user.isAfternoonStatus() ? "✅ Asr" : "❌ Asr");
        KeyboardButton keyboardButton4 = new KeyboardButton(user.isEveningStatus() ? "✅ Shom" : "❌ Shom");
        KeyboardButton keyboardButton5 = new KeyboardButton(user.isNightStatus() ? "✅ Xufton" : "❌ Xufton");
        keyboardRow2.add(keyboardButton3);
        keyboardRow2.add(keyboardButton4);
        keyboardRow2.add(keyboardButton5);

        keyboardRowList.add(keyboardRow1);
        keyboardRowList.add(keyboardRow2);

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        return replyKeyboardMarkup;
    }

    public boolean checker(String text, boolean status, User user) {
        if (text.equals("Bomdod")) {
            user.setMorningStatus(status);
            return true;
        } else if (text.equals("Peshin")) {
            user.setNoonStatus(status);
            return true;
        } else if (text.equals("Asr")) {
            user.setAfternoonStatus(status);
            return true;
        } else if (text.equals("Shom")) {
            user.setEveningStatus(status);
            return true;
        } else if (text.equals("Xufton")) {
            user.setNightStatus(status);
            return true;
        }

        return false;
    }


    public static MessageBuilder getInstance() {
        return messageBuilder;
    }

}
