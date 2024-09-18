package TextAnalyzer;
// Слишком длинные комментарии будем определять исходя из данного числа - максимальной длины комментария.
// TooLongTextAnalyzer должен конструироваться от int-а с максимальной допустимой длиной комментария.
// Объект этого класса должен сохранять в своем состоянии это число в приватном поле maxLength.
public class TooLongTextAnalyzer implements TextAnalyzer {
    private final int maxLength;

    public TooLongTextAnalyzer(int commentMaxLength) {
        this.maxLength = commentMaxLength;
    }

    @Override
    public Label processText(String text) {
        if (text.length() > maxLength) {
            return Label.TOO_LONG;
        }
        return Label.OK;
    }
}
