package dk.sdu.mmmi.mmy.score;

import dk.sdu.mmmi.mmy.common.services.IScoreService;
import org.springframework.web.client.RestTemplate;

public class ScoreService implements IScoreService {

    private static final String BASE_URL = "http://localhost:8080/score";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void addPoints(int points) {
        try {
            restTemplate.postForObject(BASE_URL + "/add?points=" + points, null, String.class);
        } catch (Exception e) {
            System.out.println("Score service unavailable");
        }
    }

    @Override
    public int getScore() {
        try {
            String score = restTemplate.getForObject(BASE_URL, String.class);
            return Integer.parseInt(score.trim());
        } catch (Exception e) {
            return 0;
        }
    }
}
