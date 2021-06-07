package com.dcorona.mario.sprites.items;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.dcorona.mario.Mario;
import com.dcorona.mario.Screens.FirstLevel;
import com.dcorona.mario.sprites.Player;

public abstract class Item extends Sprite {

    protected final FirstLevel screen;
    protected final World world;
    protected Vector2 velocity;
    protected boolean toDestroy;
    protected boolean destroyed;
    protected Body b2Body;


    public Item (FirstLevel screen, float x, float y) {
        this.screen = screen;
        this.world = screen.getWorld();
        this.setPosition(x, y);
        this.setBounds(this.getX(), this.getY(), 16/ Mario.PPM, 16/Mario.PPM);
    }

    public void update(float delta) {
        if(this.toDestroy && !this.destroyed){
            this.world.destroyBody(this.b2Body);
            this.destroyed = true;
        }
    }

    public void draw(Batch batch) {
        if(!this.destroyed){
            super.draw(batch);
        }
    }

    public void reverseVelocity(boolean x, boolean y){
        if (x) {
            this.velocity.x = -this.velocity.x;
        }
        if (y) {
            this.velocity.y = -this.velocity.y;
        }
    }

    public void destroy() {
        this.toDestroy = true;
    }

    public abstract void defineItem();

    public abstract void use(Player mario);

}
