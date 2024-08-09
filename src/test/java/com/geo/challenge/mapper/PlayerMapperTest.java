package com.geo.challenge.mapper;

import com.geo.challenge.dto.FemalePlayer;
import com.geo.challenge.dto.MalePlayer;
import com.geo.challenge.mapper.impl.PlayerMapperImpl;
import com.geo.challenge.model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.geo.challenge.constant.ConstantValues.*;
import static com.geo.challenge.utils.MockUtils.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class PlayerMapperTest {

    @InjectMocks
    private PlayerMapperImpl playerMapper;

    @Test
    @DisplayName("mapping-male-player-to-dto")
    void testMalePlayerMapping() {
        MalePlayer player = (MalePlayer) playerMapper.playerToPlayerDTO(mockPlayer(MALE_LETTER, 1));
        assertNotNull(player);
    }

    @Test
    @DisplayName("mapping-female-player-to-dto")
    void testFemalePlayerMapping() {
        FemalePlayer player = (FemalePlayer) playerMapper.playerToPlayerDTO(mockPlayer(FEMALE_LETTER, 1));
        assertNotNull(player);
    }

    @Test
    @DisplayName("mapping-dto-to-male-player")
    void testPlayerMappingToMale() {
        Player player = playerMapper.playerRequestToPlayer(mockPlayerRequest(MALE));
        assertNotNull(player);
    }

    @Test
    @DisplayName("mapping-dto-to-female-player")
    void testPlayerMappingToFemale() {
        Player player = playerMapper.playerRequestToPlayer(mockPlayerRequest(FEMALE));
        assertNotNull(player);
    }
}
