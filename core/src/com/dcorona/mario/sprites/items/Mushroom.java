package com.dcorona.mario.sprites.items;

import com.badlogic.gdx.math.Vector2;
import com.dcorona.mario.Screens.FirstLevel;
import com.dcorona.mario.sprites.Player;

public class Mushroom extends Item {

    public Mushroom(FirstLevel screen, float x, float y) {
        super(screen, x, y);
        this.setRegion(this.screen.getAtlas().findRegion("mushroom"), 0, 0, 16, 16);
        this.velocity = new Vector2(.7f, 0);
    }

    @Override
    public void defineItem() {
    }

    @Override
    public void use(Player mario) {
    }
}
