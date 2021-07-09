package secondtask;

class WordWithCounter implements Comparable {
    protected String word;
    protected int count = 0;

    protected WordWithCounter(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return ("\"" + word + "\" was founded " + count + " times");
    }


    public int compareTo(Object obj) {
        WordWithCounter entry = (WordWithCounter) obj;
        if (entry.count != this.count) {
            return entry.count - this.count;
        } else {
            return this.word.compareTo(entry.word);
        }
    }
}
