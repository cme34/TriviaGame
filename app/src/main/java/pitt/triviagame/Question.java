package pitt.triviagame;

/**
 * Created by Cory on 10/19/2015.
 * A data structure for holding all the information for a question
 */
public class Question {
    private String question, answer, fakeAnswer1, fakeAnswer2, fakeAnswer3;
    /** The type of question. EX: History, Sports, etc. */
    private Category category;

    public Question() {
        question = "";
        answer = "";
        fakeAnswer1 = "";
        fakeAnswer2 = "";
        fakeAnswer3 = "";
        category = Category.OTHER;
    }

    public Question(String quest, String ans, String fAns1, String fAns2, String fAns3, Category cat) {
        question = quest;
        answer = ans;
        fakeAnswer1 = fAns1;
        fakeAnswer2 = fAns2;
        fakeAnswer3 = fAns3;
        category = cat;
    }

    public Question(Question q) {
        question = q.question;
        answer = q.answer;
        fakeAnswer1 = q.fakeAnswer1;
        fakeAnswer2 = q.fakeAnswer2;
        fakeAnswer3 = q.fakeAnswer3;
        category = q.category;
    }

    public void setWholeQuestion(String quest, String ans, String fAns1, String fAns2, String fAns3, Category cat) {
        question = quest;
        answer = ans;
        fakeAnswer1 = fAns1;
        fakeAnswer2 = fAns2;
        fakeAnswer3 = fAns3;
        category = cat;
    }

    public void setQuestion(Question q) {
        question = q.question;
        answer = q.answer;
        fakeAnswer1 = q.fakeAnswer1;
        fakeAnswer2 = q.fakeAnswer2;
        fakeAnswer3 = q.fakeAnswer3;
        category = q.category;
    }

    public void setQuestion(String s) { question = s; }

    public void setAnswer(String s) { answer = s; }

    public void setFakeAnswer1(String s) { fakeAnswer1 = s; }

    public void setFakeAnswer2(String s) { fakeAnswer2 = s; }

    public void setFakeAnswer3(String s) { fakeAnswer3 = s; }

    public void setCategory(Category c) { category = c; }

    public String getQuestion()
    {
        return question;
    }

    public String getAnswer()
    {
        return answer;
    }

    public String getFakeAnswer1()
    {
        return fakeAnswer1;
    }

    public String getFakeAnswer2()
    {
        return fakeAnswer2;
    }

    public String getFakeAnswer3()
    {
        return fakeAnswer3;
    }

    public Category getCategory()
    {
        return category;
    }

    public String toString() {
        return question + ", " + answer + ", " + fakeAnswer1 + ", " + fakeAnswer2 + ", " + fakeAnswer3 + ", " + category.toString();
    }

    public boolean equals(Object obj) {
        if (this.getClass() == obj.getClass()) {
            Question q = (Question) obj;
            return this.getQuestion().equals(q.getQuestion()) && this.getAnswer().equals(q.getAnswer()) &&
                    this.getFakeAnswer1().equals(q.getFakeAnswer1()) && this.getFakeAnswer2().equals(q.getFakeAnswer2()) &&
                    this.getFakeAnswer3().equals(q.getFakeAnswer3()) && this.getCategory() == q.getCategory();
        }
        else
            return false;
    }

    /**
     * Converts a String into a valid Category
     * @param s
     * @return Category, if the String does not match a valid category then Category.OTHER is returned
     */
    public static Category convertStringToCategory(String s)
    {
        if (s.equals("HISTORY")) return Category.HISTORY;
        else if (s.equals("BUSINESSES")) return  Category.BUSINESSES;
        else if (s.equals("FAMOUS_FIGURES")) return Category.FAMOUS_FIGURES;
        else if (s.equals("MOVIES")) return Category.MOVIES;
        else if (s.equals("TV")) return Category.TV;
        else return Category.OTHER;
    }
}
