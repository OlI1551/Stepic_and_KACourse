public class ex16Human {
    public static void main(String[] args) {
        Human vasya = new Human("Вася боится собак", "У Степана родился сын", "Знает все цены в городе", "Степан поругался с женой");
        System.out.println(vasya.getNews());
    }

    public static class Human {
        private final String secret; //секретики
        private final String news; //новости
        private final String experience; //опыт
        private final String gossip; //сплетни

        public Human(String secret, String news, String experience, String gossip) {
            this.secret = secret;
            this.news = news;
            this.experience = experience;
            this.gossip = gossip;
        }

        private String getSecret() {
            return secret;
        }

        public String getNews() {
            return news;
        }

        protected String getExperience() {
            return experience;
        }

        String getGossip() {
            return gossip;
        }
    }
}
