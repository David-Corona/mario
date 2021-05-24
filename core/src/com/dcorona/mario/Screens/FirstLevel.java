package com.dcorona.mario.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dcorona.mario.Mario;
import com.dcorona.mario.Scenes.Hub;

import java.util.concurrent.LinkedBlockingQueue;

public class FirstLevel implements Screen {

    private Mario game;
    private TextureAtlas atlas;
    private OrthographicCamera camera; //marca el punto de vista
    private Viewport viewport; //tamaño que vemos representado en la pantalla, hace que se mantenga el ratio
    private Hub hub;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world; //cada nivel nuevo, tendrá un mapa nuevo, por eso está aqui
    private Box2DDebugRenderer b2d; //para que se dibuje el circulito verde, cuando acabe se debería eliminar
    //private B2WorldCreator creator;
    // se encargará de crear el mapa

    //private Player mario;

    private Music music; //reproducirá los sonidos

    //private Array<Item> items;
    //private LinkedBlockingQueue<ItemDefinition> itemsToSpawn; //objetos, plantas



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
