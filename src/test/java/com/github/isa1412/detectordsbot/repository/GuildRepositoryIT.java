package com.github.isa1412.detectordsbot.repository;

import com.github.isa1412.detectordsbot.repository.entity.Guild;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * Integration-level testing for {@link GuildRepository}.
 */
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class GuildRepositoryIT {

    @Autowired
    private GuildRepository guildRepository;

    @Sql(scripts = {"/sql/clearDB.sql", "/sql/guild.sql"})
    @Test
    public void shouldProperlyFindAllGuilds() {
        //when
        List<Guild> guilds = guildRepository.findAll();

        //then
        Assertions.assertEquals(8, guilds.size());
    }

    @Sql(scripts = {"/sql/clearDB.sql"})
    @Test
    public void shouldProperlySaveGuild() {
        //given
        Guild guild = new Guild("1235568768");
        guildRepository.save(guild);

        //when
        Optional<Guild> saved = guildRepository.findById(guild.getId());

        //then
        Assertions.assertTrue(saved.isPresent());
        Assertions.assertEquals(guild, saved.get());
    }
}
