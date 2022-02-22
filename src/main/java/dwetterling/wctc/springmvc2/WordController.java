package dwetterling.wctc.springmvc2;



import dwetterling.wctc.springmvc2.entity.Word;
import dwetterling.wctc.springmvc2.service.WordListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;


@Controller
public class WordController {
    private WordListService wordListService;

    @Autowired
    public WordController(WordListService wls){
        this.wordListService = wls;
    }

    @RequestMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("word", wordListService.getWords());
        return "index";
    }


    @GetMapping("/search")
    public String showForm( Model model){
        model.addAttribute("wordList",
                wordListService.getWords());

        Word defaultWord = new Word();
        defaultWord.setTerm("");
        defaultWord.setDefinition("");
        model.addAttribute("word", defaultWord);
        return "search";
    }

    @PostMapping("/definition")
    public  String showSearchPage(@RequestParam(name ="search") String word){
        if(wordListService.getWord(word) == null){
           return "error";
        }else
        return "redirect:/definition/" + word;
    }

    @RequestMapping("/definition/{word}")
    public String wordList(@PathVariable String word, Model model){
        model.addAttribute("wls", wordListService.getWord(word));
        return "definition";
    }

}

