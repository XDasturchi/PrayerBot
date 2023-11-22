package messageBuilder;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class CreatorInlineMarkUp {

    private static String[] buttons = new String[]{};
    private static String[] buttonsCallback = new String[]{};

    public CreatorInlineMarkUp(String[] buttons, String[] buttonsCallback) {
        if (buttons.length == buttonsCallback.length) {
            this.buttons = buttons;
            this.buttonsCallback = buttonsCallback;
        }
    }

    public InlineKeyboardMarkup getInlineMarkUp() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();


        List<List<InlineKeyboardButton>> rowList = new ArrayList<>(); //Создаём ряд

        for (int i = 0; i < buttons.length; i++) {
            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
            inlineKeyboardButton1.setText(buttons[i]);
            inlineKeyboardButton1.setCallbackData(buttonsCallback[i]);
            List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
            keyboardButtonsRow1.add(inlineKeyboardButton1);
            rowList.add(keyboardButtonsRow1);
        }

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }


}