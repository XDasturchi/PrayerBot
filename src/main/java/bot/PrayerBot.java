package bot;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import updateHandler.UpdateHandler;

public class PrayerBot extends TelegramLongPollingBot {

    private DefaultAbsSender sender;
    private UpdateHandler updateHandler = new UpdateHandler(this);

    @Override
    public void onUpdateReceived(Update update) {
        updateHandler.handler(update);
    }

    @Override
    public String getBotToken() {
        return "6089416530:AAHjCln_GJG8oe2YMhrzggHoz8IFgRmy3yU";
    }

    @Override
    public String getBotUsername() {
        return "XPrayerBot";
    }

    public void telegramExecute(BotApiMethod method) {
        try {
            execute(method);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}