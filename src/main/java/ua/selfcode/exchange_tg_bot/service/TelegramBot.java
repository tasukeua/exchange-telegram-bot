package ua.selfcode.exchange_tg_bot.service;

import com.vdurmont.emoji.EmojiParser;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.selfcode.exchange_tg_bot.config.BotConfig;
import ua.selfcode.exchange_tg_bot.enums.Emoji;
import ua.selfcode.exchange_tg_bot.utils.KeyboardRow;

@Service
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;

    @Autowired
    public TelegramBot(BotConfig botConfig) {
        super(botConfig.getBotToken());
        this.botConfig = botConfig;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String massage = update.getMessage().getText();
            long chatID = update.getMessage().getChatId();
            switch (massage) {
                case "/info":
                    sendMessage(chatID, "This is Exchange Bot based on NBU data " + Emoji.OFFICE.getEmoji() +
                            "This version supports currencies: dollar, euro and zloty " + Emoji.BAG.getEmoji() + "\n" +
                            "Metals: gold, silver, platinum, paladiy" + Emoji.GEM.getEmoji() + "\n" +
                            Emoji.MAG.getEmoji() + "Use Command: /data");
                    break;
                case "/start":
                    startCommand(chatID, update.getMessage().getChat().getFirstName());
                    break;
                case "/data":
                    handleRequest(chatID, "Select currency " + EmojiParser.parseToUnicode(Emoji.MONEY.getEmoji()));
                    break;
                default:
                    sendMessage(chatID, "Command unknown " + Emoji.ERROR.getEmoji());
            }
        } else if (update.hasCallbackQuery()) {
            String callBackData = update.getCallbackQuery().getData();
            long chatID = update.getCallbackQuery().getMessage().getChatId();
            CurrencyJSONService service = new CurrencyJSONService();
            String message = service.getResponse(callBackData);
            sendMessage(chatID, message);
        }
    }

    private void handleRequest(long chatID, String selectCurrency) {
        SendMessage message = new SendMessage();
        message.setChatId(chatID);
        message.setText(selectCurrency);
        InlineKeyboardMarkup markup = KeyboardRow.createKeyboardCurrency();
        message.setReplyMarkup(markup);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessage(long chatID, String sendMassageText) {
        SendMessage message = new SendMessage();
        message.setChatId(chatID);
        message.setText(EmojiParser.parseToUnicode(sendMassageText));
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void startCommand(long chatID, String firstName) {
        String answer = "Hi, " + firstName + ", bot is active " + Emoji.CONGRATULATIONS.getEmoji() + "\n" +
                Emoji.MAG.getEmoji() + " Use command: /info for available currencies" +
                Emoji.MAG.getEmoji() + "Use command: /data get data on currencies";
        sendMessage(chatID, answer);
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }
}
