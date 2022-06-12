package com.github.isa1412.detectordsbot.repository.entity;

import com.github.isa1412.detectordsbot.repository.entity.id.MemberId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Guild Member entity.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member {

    @EmbeddedId
    private MemberId id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId("guildId")
    @JoinColumn(name = "guild_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Guild guild;

    @Column(name = "wins_count")
    private int count;

    private boolean active;

    public void incCount() {
        count++;
    }

    public Member(MemberId id) {
        this.id = id;
        this.guild = new Guild(id.getGuildId());
        this.active = true;
    }
}
