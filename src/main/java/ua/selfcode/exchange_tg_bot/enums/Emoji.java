package ua.selfcode.exchange_tg_bot.enums;

public enum Emoji {
    CONGRATULATIONS(":party_face:"),
    OFFICE(":office:"),
    ERROR(":x:"),
    MONEY(":money_with_wings:"),
    MAG(":mag:"),
    BAG(":moneybag:"),
    UA_FLAG(":ua:"),
    GEM(":gem:");

    private String emoji;

    Emoji(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }
}
