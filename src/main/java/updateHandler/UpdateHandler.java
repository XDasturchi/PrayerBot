package updateHandler;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import messageBuilder.MessageBuilder;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import user.State;
import user.User;
import user.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class UpdateHandler {
    private final DefaultAbsSender sender;
    private final UserRepository userRepository = UserRepository.getInstance();

    private final MessageBuilder message = MessageBuilder.getInstance();

    @SneakyThrows
    public void handler(Update update) {

        if (update.hasMessage()) {
            Long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();
            userRepository.add(new User(chatId, State.MAIN));
            Optional<User> getUser = userRepository.findById(chatId);
            User user = getUser.get();


            if (text.equals("/start")) {
                sender.execute(message.messageStart(chatId));
            }

            if (text.contains("✅") && text.startsWith("✅")) {
                String getType = text.split(" ")[1];
                if (message.checker(getType, false, user)) {
                    sender.execute(message.sendChanged(chatId));
                }
            }

            if (text.contains("❌") && text.startsWith("❌")) {
                String getType = text.split(" ")[1];
                if (message.checker(getType, true, user)) {
                    sender.execute(message.sendChanged(chatId));
                }
            }

        }
    }
}
