
package dictionary.backend;

//Class Word có bổ sung class Comparator
//dùng để sort lại các từ có trong từ điển
public class Word implements Comparable<Word>
{
    private String word_target;
    private String word_explain;

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public String getWord_target() {
        return word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }
    
    @Override
    public int compareTo(Word other)
    {
    	return this.word_target.compareTo(other.word_target);
    }

	
}
