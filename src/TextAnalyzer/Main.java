package TextAnalyzer;
// Реализовать иерархию классов определенного вида и решить конкретную задачу.
// Вы делаете систему фильтрации комментариев по разным критериям на веб-портале,
// хотите легко добавлять новые фильтры и модифицировать старые.
// Будем фильтровать: 1) спам, 2) комментарии с негативным содержанием и 3) слишком длинные комментарии.
public class Main {
    public static void main(String[] args) {
        // инициализация анализаторов для проверки в порядке данного набора анализаторов
        String[] spamKeywords = {"spam", "bad"};
        int commentMaxLength = 40;
        TextAnalyzer[] textAnalyzers1 = {
            new SpamAnalyzer(spamKeywords),
            new NegativeTextAnalyzer(),
            new TooLongTextAnalyzer(commentMaxLength)
        };
        TextAnalyzer[] textAnalyzers2 = {
            new SpamAnalyzer(spamKeywords),
            new TooLongTextAnalyzer(commentMaxLength),
            new NegativeTextAnalyzer()
        };
        TextAnalyzer[] textAnalyzers3 = {
                new TooLongTextAnalyzer(commentMaxLength),
                new SpamAnalyzer(spamKeywords),
                new NegativeTextAnalyzer()
        };
        // тестовые комментарии
        String[] tests = new String[8];
        tests[0] = "This comment is so good.";                            // OK
        tests[1] = "This comment is so Loooooooooooooooooooooooooooong."; // TOO_LONG
        tests[2] = "Very negative comment !!!!=(!!!!;";                   // NEGATIVE_TEXT
        tests[3] = "Very BAAAAAAAAAAAAAAAAAAAAAAAAD comment with :|;";    // NEGATIVE_TEXT or TOO_LONG
        tests[4] = "This comment is so bad....";                          // SPAM
        tests[5] = "The comment is a spam, maybeeeeeeeeeeeeeeeeeeeeee!";  // SPAM or TOO_LONG
        tests[6] = "Negative bad :( spam.";                               // SPAM or NEGATIVE_TEXT
        tests[7] = "Very bad, very neg =(, very ..................";      // SPAM or NEGATIVE_TEXT or TOO_LONG
        TextAnalyzer[][] textAnalyzers = {textAnalyzers1, textAnalyzers2, textAnalyzers3};
        Main testObject = new Main();
        int numberOfAnalyzer; // номер анализатора, указанный в идентификаторе textAnalyzers{№}
        int numberOfTest = 0; // номер теста, который соответствует индексу тестовых комментариев
        for (String test : tests) {
            numberOfAnalyzer = 1;
            System.out.print("test #" + numberOfTest + ": ");
            System.out.println(test);
            for (TextAnalyzer[] analyzers : textAnalyzers) {
                System.out.print(numberOfAnalyzer + ": ");
                System.out.println(testObject.checkLabels(analyzers, test));
                numberOfAnalyzer++;
            }
            numberOfTest++;
        }
    }
    public static Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for (TextAnalyzer analyzer : analyzers) {
            if (analyzer.processText(text) != Label.OK) {
                return analyzer.processText(text);
            }
        }
        return Label.OK;
    }
}

//    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
//        for (int i = 0; i < analyzers.length; i++)
//            if (analyzers[i].processText(text) != Label.OK) {
//                return analyzers[i].processText(text);
//            }
//        return Label.OK;
//    }

//    public static Label checkLabels(TextAnalyzer[] analyzers, String text) {
//        for (TextAnalyzer analyzer : analyzers) {
//            Label result = analyzer.processText(text);
//            if (result != Label.OK) {
//                return result;
//            }
//        }
//        return Label.OK;
//    }