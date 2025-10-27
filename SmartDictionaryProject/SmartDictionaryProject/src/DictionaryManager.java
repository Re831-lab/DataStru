public class DictionaryManager {
    private MyHashMap dictionary;

    public DictionaryManager() {
        dictionary = new MyHashMap();
    }

    public boolean addWord(String word, String meaning) {
        if (word == null || meaning == null || word.isEmpty() || meaning.isEmpty())
            return false;
        dictionary.put(word, meaning);
        return true;
    }

    public String searchWord(String word) {
        return dictionary.get(word);
    }

    public boolean deleteWord(String word) {
        return dictionary.remove(word);
    }

    public String showAll() {
        return dictionary.showAll();
    }

    public boolean isEmpty() {
        return dictionary.isEmpty();
    }
}
