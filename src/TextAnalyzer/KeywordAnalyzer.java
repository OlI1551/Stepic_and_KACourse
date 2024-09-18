package TextAnalyzer;
// SpamAnalyzer и NegativeTextAnalyzer похожи - проверяют текст на наличие ключевых слов:
// в случае спама мы получаем их из конструктора,
// в случае негативного текста из заданного набора грустных смайликов
// в случае нахождения одного из ключевых слов возвращают Label (SPAM или NEGATIVE_TEXT)
// если ничего не нашлось - возвращают OK.
// Эту логику абстрагируем в абстрактный класс KeywordAnalyzer следующим образом:
// Выделим два абстрактных метода getKeywords и getLabel
// первый будет возвращать набор ключевых слов, а второй метку, которой необходимо пометить положительные срабатывания.
// Нам незачем показывать эти методы потребителям классов, поэтому оставим доступ к ним только для наследников.
// Реализуем processText таким образом, чтобы он зависел только от getKeywords и getLabel.
// Сделаем SpamAnalyzer и NegativeTextAnalyzer наследниками KeywordAnalyzer и реализуем абстрактные методы.

public abstract class KeywordAnalyzer implements TextAnalyzer {
    protected abstract String[] getKeywords();
    protected abstract Label getLabel();

    @Override
    public Label processText(String text) {
        String[] keywords = getKeywords();
        for (String keyword : keywords) {
            if (text.contains(keyword)) {
                return getLabel();
            }
        }
        return Label.OK;
    }
//    public Label processText(String text) {
//        for (String keyword : getKeywords()) { // так тоже будет работать
//            if (text.contains(keyword)) {
//                return getLabel();
//            }
//        }
//        return Label.OK;
//    }
}
