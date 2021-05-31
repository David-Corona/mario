package com.dcorona.mario.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
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
    private Box2DDebugRenderer b2dr; //para que se dibuje el circulito verde, cuando acabe se debería eliminar
    private B2WorldCreator creator;
    // se encargará de crear el mapa

    private Player mario;

    private Music music; //reproducirá los sonidos

    private Array<Item> items;
    private LinkedBlockingQueue<ItemDefinition> itemsToSpawn; //objetos, plantas


    public FirstLevel(Mario game){
        this.atlas = new TextureAtlas("sprites/Mario_and_enemies.pack");
        this.game = game;
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(Mario.V_WIDTH / Mario.PPM, Mario.V_HEIGHT / Mario.PPM, camera);
        this.hub = new Hub(game.getBatch());

        this.mapLoader = new TmxMapLoader();
        this.map = mapLoader.load("maps/0-0.tmx");
        this.renderer = new OrthogonalTiledMapRenderer(map, 1/Mario.PPM);
        this.camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        this.world = new World(new Vector2(0,-10), true); //activa la gravedad
        this.b2dr = new Box2DDebugRenderer();

        this.creator = new B2WorldCreator(this);
        this.mario = new Player(this);

        this.world.setContactListener(new WorldContactListener(this.hub, this.game.getManager())); //pendiente de posibles colisiones, dirá que pasará - gestor eventos
        this.music = this.game.getManager().get("audio/music/mario_music.ogg", Music.class);
        this.music.setLooping(true);
        this.music.setVolume(.1f);
        this.music.play(); //sonará la musica, no habrá opción de cambiar volumen o apagar

        this.items = new Array<Item>();
        this.itemsToSpawn = new LinkedBlockingQueue<ItemDefinition>();
    }

    public TextureAtlas getAtlas() {
        return this.atlas;
    }

    public TiledMap getMap() {
        return this.map;
    }

    public World getWorld() {
        return this.world;
    }

    @Override
    public void show() {

    }

    public void update(float delta) {
        this.handleInput(delta);
        this.handleSpawningItems();

        this.world.step(1/60f, 6, 2);
        this.mario.update(delta);

        for(Enemy enemy : this.creator.getGoombas()) {
            enemy.update(delta);
            if (enemy.getX() < this.mario.getX() + 224/Mario.PPM) { //que se active cuando se acerque mario
                enemy.activate();
            }
        }

        for (Item item : this.items) {
            item.update(delta);
        }

        this.hub.update(delta);

        this.camera.position.x = this.mario.getPosition().x;

        this.camera.update();
        this.renderer.setView(this.camera);
    }

    @Override
    public void render(float delta) {
        this.update(delta);
        ScreenUtils.clear(0, 0, 0, 1); //limpiar pantalla
        this.renderer.render(); //lo primero es pintar el mapa
        this.b2dr.render(this.world, this.camera.combined);

        this.game.setProjectionMatrix(this.camera.combined);

        this.game.beginDraw();
        this.mario.draw(this.game.getBatch());
        for (Enemy enemy : creator.getGoombas()) {
            enemy.draw(this.game.getBatch());
        }
        for (Item item : this.items) {
            item.draw(this.game.getBatch());
        }
        this.game.endDraw();

        this.game.setProjectionMatrix(this.hub.getProjectionMatrixCamera());
        this.hub.draw();
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

    public void handleInput(float delta) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            this.mario.impulseUp();
        }
        if ((Gdx.input.isKeyJustPressed(Input.Keys.D) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)
                && this.mario.getVelocity().x <= 2 )) {
            this.mario.impulseRight();
        }
        if ((Gdx.input.isKeyJustPressed(Input.Keys.A) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT)
                && this.mario.getVelocity().x >= -2 )) {
            this.mario.impulseLeft();
        }
    }

}
