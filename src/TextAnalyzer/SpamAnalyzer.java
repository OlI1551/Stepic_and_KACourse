package TextAnalyzer;
// Спам будем фильтровать по наличию указанных ключевых слов в тексте.
// SpamAnalyzer должен конструироваться от массива строк с ключевыми словами.
// Объект этого класса должен сохранять в своем состоянии этот массив строк в приватном поле keywords.
public class SpamAnalyzer extends KeywordAnalyzer {
    private final String[] keywords;

    public SpamAnalyzer(String[] keywords) {
        this.keywords = keywords;
    }

    @Override
    protected String[] getKeywords() {
        return keywords;
    }

    @Override
    protected Label getLabel() {
        return Label.SPAM;
    }
}