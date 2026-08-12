package dk.sdu.mmmi.mmy.scoringsystem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/score")
public class ScoreController {

    private final AtomicInteger score = new AtomicInteger();

    @GetMapping
    public int getScore() {
        return score.get();
    }

    @PostMapping("/add")
    public int addScore(@RequestParam int points) {
        return score.addAndGet(points);
    }

    @PostMapping("/reset")
    public int reset() {
        score.set(0);
        return 0;
    }
}
