package TextAnalyzer;
// Негативное содержание будем определять по наличию одного из трех смайликов -  :( =( :|
// NegativeTextAnalyzer должен конструироваться конструктором по-умолчанию.
public class NegativeTextAnalyzer extends KeywordAnalyzer {
    private static final String[] KEYWORDS = {":(", "=(", ":|"};

    @Override
    protected String[] getKeywords() {
        return KEYWORDS;
    }

    @Override
    protected Label getLabel() {
        return Label.NEGATIVE_TEXT;
    }
}