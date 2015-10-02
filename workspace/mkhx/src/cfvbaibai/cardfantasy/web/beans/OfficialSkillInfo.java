package cfvbaibai.cardfantasy.web.beans;

import java.util.ArrayList;
import java.util.List;

import cfvbaibai.cardfantasy.officialdata.OfficialCard;
import cfvbaibai.cardfantasy.officialdata.OfficialDataStore;
import cfvbaibai.cardfantasy.officialdata.OfficialSkill;

public class OfficialSkillInfo {
    public OfficialSkill skill;
    public List<OfficialCard> owningCards;
    public static OfficialSkillInfo build(OfficialSkill skill, OfficialDataStore store) {
        OfficialSkillInfo skillInfo = new OfficialSkillInfo();
        skillInfo.skill = skill;
        skillInfo.owningCards = new ArrayList<OfficialCard>();
        for (OfficialCard card : store.cardStore.data.Cards) {
            if (skill.SkillId.equalsIgnoreCase(card.getSkill1()) ||
                skill.SkillId.equalsIgnoreCase(card.getSkill2()) ||
                skill.SkillId.equalsIgnoreCase(card.getSkill3()) ||
                skill.SkillId.equalsIgnoreCase(card.getSkill4()) ||
                skill.SkillId.equalsIgnoreCase(card.getSkill5())) {
                skillInfo.owningCards.add(card);
            }
        }
        return skillInfo;
    }
    
    public OfficialCard[] getOwningCards() {
        OfficialCard[] result = new OfficialCard[this.owningCards.size()];
        for (int i = 0; i < this.owningCards.size(); ++i) {
            result[i] = this.owningCards.get(i);
        }
        return result;
    }

    public String getName() {
        return this.skill.getName();
    }

    public String getDescription() {
        return this.skill.getDescription();
    }
}
