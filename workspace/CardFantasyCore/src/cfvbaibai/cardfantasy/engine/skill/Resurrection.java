package cfvbaibai.cardfantasy.engine.skill;

import java.util.List;

import cfvbaibai.cardfantasy.CardFantasyRuntimeException;
import cfvbaibai.cardfantasy.GameUI;
import cfvbaibai.cardfantasy.Randomizer;
import cfvbaibai.cardfantasy.data.Skill;
import cfvbaibai.cardfantasy.engine.CardInfo;
import cfvbaibai.cardfantasy.engine.SkillUseInfo;
import cfvbaibai.cardfantasy.engine.SkillResolver;
import cfvbaibai.cardfantasy.engine.Player;

public final class Resurrection {
    public static void apply(SkillResolver resolver, SkillUseInfo skillUseInfo, CardInfo resurrector) {
        if (resurrector == null) {
            throw new CardFantasyRuntimeException("resurrector should not be null");
        }
        if (resurrector.getOwner() != resolver.getStage().getActivePlayer()) {
            throw new CardFantasyRuntimeException("resurrector is not the current active player!");
        }
        Skill skill = skillUseInfo.getSkill();
        // Grave is a stack, find the last-in card and revive it.
        int resurrectionCount = skill.getImpact();
        Player player = resurrector.getOwner();
        CardInfo exclusion = skill.isDeathSkill() ? resurrector : null;
        List<CardInfo> cardsToResurrect = Randomizer.getRandomizer().pickRandom(
            player.getGrave().toList(), resurrectionCount, true, exclusion);
        GameUI ui = resolver.getStage().getUI();
        ui.useSkill(resurrector, cardsToResurrect, skill, true);
        for (CardInfo card : cardsToResurrect) {
            ui.cardToDeck(player, card);
            player.getGrave().removeCard(card);
            player.getDeck().addCard(card);
        }
    }
}
