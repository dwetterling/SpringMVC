package dwetterling.wctc.springmvc2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dwetterling.wctc.springmvc2.entity.Word;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WordService implements WordListService {
    private List<Word> wordListArray;



    @PostConstruct
    private void initWordData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Word[] wordArray = mapper.readValue(Paths.get("Words.json").toFile(), Word[].class);
            wordListArray = Arrays.asList(wordArray);
        } catch (IOException e) {
            e.printStackTrace();
            wordListArray = new ArrayList<>(0);
        }
    }

    public Word getWord(String word) {
        for (Word item : wordListArray) {
            if (item.getTerm().equalsIgnoreCase(word)) {

                return item;
            }
        }
        return null;
    }

    @Override
    public List<Word> getWords() {
        return wordListArray;
    }


}


