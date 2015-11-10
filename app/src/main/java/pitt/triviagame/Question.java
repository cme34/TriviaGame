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

    public Question(String quest, String ans, String fAns1, String fAns2, String fAnsw3, Category cat) {
        question = quest.intern();
        answer = ans.intern();
        fakeAnswer1 = fAns1.intern();
        fakeAnswer2 = fAns2.intern();
        fakeAnswer3 = fAnsw3.intern();
        category = cat;
    }

    public Question(Question q) {
        question = q.question.intern();
        answer = q.answer.intern();
        fakeAnswer1 = q.fakeAnswer1.intern();
        fakeAnswer2 = q.fakeAnswer2.intern();
        fakeAnswer3 = q.fakeAnswer3.intern();
        category = q.category;
    }

    public void setWholeQuestion(String quest, String ans, String fAns1, String fAns2, String fAnsw3, Category cat) {
        question = quest.intern();
        answer = ans.intern();
        fakeAnswer1 = fAns1.intern();
        fakeAnswer2 = fAns2.intern();
        fakeAnswer3 = fAnsw3.intern();
        category = cat;
    }

    public void setQuestion(Question q) {
        question = q.question.intern();
        answer = q.answer.intern();
        fakeAnswer1 = q.fakeAnswer1.intern();
        fakeAnswer2 = q.fakeAnswer2.intern();
        fakeAnswer3 = q.fakeAnswer3.intern();
        category = q.category;
    }

    public void setQuestion(String s) { question = s.intern(); }

    public void setAnswer(String s) { answer = s.intern(); }

    public void setFakeAnswer1(String s) { fakeAnswer1 = s.intern(); }

    public void setFakeAnswer2(String s) { fakeAnswer2 = s.intern(); }

    public void setFakeAnswer3(String s) { fakeAnswer3 = s.intern(); }

    public void setCategory(Category c) { category = c; }

    public String getQuestion()
    {
        return question.intern();
    }

    public String getAnswer()
    {
        return answer.intern();
    }

    public String getFakeAnswer1()
    {
        return fakeAnswer1.intern();
    }

    public String getFakeAnswer2()
    {
        return fakeAnswer2.intern();
    }

    public String getFakeAnswer3()
    {
        return fakeAnswer3.intern();
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
            return this.getQuestion().intern().equals(q.getQuestion().intern()) && this.getAnswer().intern().equals(q.getAnswer().intern()) &&
                    this.getFakeAnswer1().intern().equals(q.getFakeAnswer1().intern()) && this.getFakeAnswer2().intern().equals(q.getFakeAnswer2().intern()) &&
                    this.getFakeAnswer3().intern().equals(q.getFakeAnswer3().intern()) && this.getCategory() == q.getCategory();
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
        if (s.intern().equals("HISTORY")) return Category.HISTORY;
        else if (s.intern().equals("BUSINESSES")) return  Category.BUSINESSES;
        else return Category.OTHER;
    }
}
