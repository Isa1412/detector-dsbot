package com.github.isa1412.detectordsbot.service;

import com.github.isa1412.detectordsbot.repository.GuildRepository;
import com.github.isa1412.detectordsbot.repository.entity.Guild;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of {@link GuildService}.
 */
@Service
public class GuildServiceImpl implements GuildService {

    private final GuildRepository guildRepository;

    public GuildServiceImpl(GuildRepository guildRepository) {
        this.guildRepository = guildRepository;
    }

    @Override
    public void save(Guild guild) {
        guildRepository.save(guild);
    }

    @Override
    public Optional<Guild> findById(String id) {
        return guildRepository.findById(id);
    }
}
