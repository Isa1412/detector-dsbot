package com.github.isa1412.detectordsbot.repository.entity.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Member entity key.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class MemberId implements Serializable {

    /**
     * Discord {@link User} ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * Discord {@link Guild} ID
     */
    @Column(name = "guild_id")
    private String guildId;
}
