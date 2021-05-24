package com.dcorona.mario.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dcorona.mario.Mario;

public class FirstLevel implements Screen {

    private Mario game;
    private TextureAtlas atlas;
    private OrthographicCamera camera; //marca el punto de vista
    private Viewport viewport; //tamaño que vemos representado en la pantalla, hace que se mantenga el ratio


    public FirstLevel(Mario game){
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        this.game.beginDraw();
        this.game.endDraw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
