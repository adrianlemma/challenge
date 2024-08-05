package com.geo.challenge.dto.response;

import com.geo.challenge.dto.AbstractPlayer;

import java.util.List;

public class PlayerResponse {

    private List<AbstractPlayer> data;

    public PlayerResponse(List<AbstractPlayer> data) {
        this.data = data;
    }

    public List<AbstractPlayer> getData() {
        return data;
    }

    public void setData(List<AbstractPlayer> data) {
        this.data = data;
    }
}
