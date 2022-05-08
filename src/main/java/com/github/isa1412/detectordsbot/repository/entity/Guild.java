package com.github.isa1412.detectordsbot.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Discord Guild entity.
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "guild")
public class Guild {

    @Id
    @NonNull
    private String id;

    private long timestamp;

    @OneToMany(mappedBy = "guild", fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Member> members;
}
