package net.asbyth.tweaker.mixins.scoreboard;

import net.asbyth.tweaker.config.Settings;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(Scoreboard.class)
public abstract class MixinScoreboard {

    @Shadow @Final private Map<String, ScorePlayerTeam> teams;
    @Shadow @Final private Map<String, ScorePlayerTeam> teamMemberships;
    @Shadow public abstract void func_96513_c(ScorePlayerTeam playerTeam);

    /**
     * @author asbyth
     * @reason fix a crash with scoreboards
     */
    @Overwrite
    public void removeTeam(ScorePlayerTeam team) {
        if (Settings.SCOREBOARDCRASH) {
            if (team == null) return;

            if (team.getRegisteredName() != null) {
                teams.remove(team.getRegisteredName());
            }

            for (String members : team.getMembershipCollection()) {
                teamMemberships.remove(members);
            }

            func_96513_c(team);
        } else {
            teams.remove(team.getRegisteredName());

            for (String s : team.getMembershipCollection()) {
                teamMemberships.remove(s);
            }

            func_96513_c(team);
        }
    }
}
