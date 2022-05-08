package com.github.isa1412.detectordsbot.repository;

import com.github.isa1412.detectordsbot.repository.entity.Guild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link Repository} for handling with {@link Guild} entity.
 */
@Repository
public interface GuildRepository extends JpaRepository<Guild, String> {
}
