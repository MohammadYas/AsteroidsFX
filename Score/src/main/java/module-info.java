import dk.sdu.mmmi.mmy.common.services.IScoreService;

module Score {
    requires Common;
    requires spring.web;

    provides IScoreService with dk.sdu.mmmi.mmy.score.ScoreService;

    exports dk.sdu.mmmi.mmy.score;
}
