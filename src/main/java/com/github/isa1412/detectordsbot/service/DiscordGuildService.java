package com.github.isa1412.detectordsbot.service;

import com.github.isa1412.detectordsbot.repository.entity.Guild;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * {@link Service} for handling {@link Guild} entity.
 */
public interface DiscordGuildService {

    /**
     * Save provided {@link Guild} entity.
     *
     * @param  guild provided {@link Guild}.
     */
    void save(Guild guild);

    /**
     * Find {@link Guild} by id.
     *
     * @param id provided Discord ID.
     * @return {@link Guild} with provided ID or null otherwise.
     */
    Optional<Guild> findById(String id);
}
