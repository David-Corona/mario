package com.dcorona.mario.sprites.items;

import com.badlogic.gdx.math.Vector2;

public class ItemDefinition {

    protected Vector2 position;
    protected Class<?> type;

    public ItemDefinition (Vector2 position, Class type){
        this.position = position;
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }

    public Vector2 getPosition() {
        return position;
    }
}
