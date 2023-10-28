package ua.selfcode.exchange_tg_bot.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class KeyboardRow {
    public static InlineKeyboardMarkup createKeyboardCurrency() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        InlineKeyboardButton USD = new InlineKeyboardButton();
        USD.setText("USD");
        USD.setCallbackData("USD");
        InlineKeyboardButton EUR = new InlineKeyboardButton();
        EUR.setText("EUR");
        EUR.setCallbackData("EUR");
        InlineKeyboardButton PLN = new InlineKeyboardButton();
        PLN.setText("PLN");
        PLN.setCallbackData("PLN");
        InlineKeyboardButton GBP = new InlineKeyboardButton();
        GBP.setText("GBP");
        GBP.setCallbackData("GBP");
        firstRow.add(USD);
        firstRow.add(EUR);
        firstRow.add(PLN);
        firstRow.add(GBP);
        rows.add(firstRow);
        InlineKeyboardButton XAU = new InlineKeyboardButton();
        XAU.setText("XAU");
        XAU.setCallbackData("XAU");
        InlineKeyboardButton XAG = new InlineKeyboardButton();
        XAG.setText("XAG");
        XAG.setCallbackData("XAG");
        InlineKeyboardButton XPT = new InlineKeyboardButton();
        XPT.setText("XPT");
        XPT.setCallbackData("XPT");
        InlineKeyboardButton XPD = new InlineKeyboardButton();
        XPD.setText("XPD");
        XPD.setCallbackData("XPD");
        secondRow.add(XAU);
        secondRow.add(XAG);
        secondRow.add(XPT);
        secondRow.add(XPD);
        rows.add(secondRow);
        keyboardMarkup.setKeyboard(rows);
        return keyboardMarkup;
    }
}
