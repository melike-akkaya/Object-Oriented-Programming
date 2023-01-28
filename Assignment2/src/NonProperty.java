public abstract class NonProperty extends Square{

    NonProperty(int i) {
        id = i;
    }

    public abstract String setProcessing(UPlayer player, UBanker banker, String processing);
}
