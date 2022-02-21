package dwetterling.wctc.springmvc2.service;

import dwetterling.wctc.springmvc2.entity.Word;

import java.util.List;

public interface WordListService {
    List<Word> getWords();

    Object getWord(String word);
}
