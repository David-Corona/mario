package com.dcorona.mario.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dcorona.mario.Mario;

public class Hub implements Disposable { //será el menu/pantalla con el nombre, puntuacion, tiempo, etc.

    private Stage stage;
    private Viewport viewport;
    private int worldTimer;
    private float timeCount;
    private int score;

    private Label countDownLabel;
    private Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label playerLabel; //nombre y corazones de vidas

    public Hub(SpriteBatch sb) {
        this.worldTimer = 300;
        this.timeCount = 0;
        this.score = 0;

        this.viewport = new FitViewport(Mario.V_WIDTH, Mario.V_HEIGHT, new OrthographicCamera()); //para que mantenga ratio
        Table table = new Table();
        table.top();
        table.setFillParent(true); //que se ajuste al padre

        this.countDownLabel = new Label(String.format("%03d", this.worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        this.scoreLabel = new Label(String.format("%03d", this.score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        this.timeLabel = new Label("TIEMPO", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        this.levelLabel = new Label("0-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        this.worldLabel = new Label("MUNDO", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        this.playerLabel = new Label("DAVID", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(this.playerLabel).expandX().padTop(10);
        table.add(this.worldLabel).expandX().padTop(10);
        table.add(this.timeLabel).expandX().padTop(10);
        table.row();
        table.add(this.scoreLabel).expandX();
        table.add(this.levelLabel).expandX();
        table.add(this.countDownLabel).expandX();

        this.stage.addActor(table);
    }

    public Matrix4 getProjectionMatrixCamera() {
        return this.stage.getCamera().combined; //devuelve camera invertida
    }

    public void update(float delta) {
        this.timeCount += delta;
        if (this.timeCount >= 1){
            this.worldTimer--;
            this.countDownLabel.setText(String.format("%03d", this.worldTimer));
            this.timeCount = 0;
        }
    }

    public void addScore(int value) {
        this.score += value;
        this.scoreLabel.setText(String.format("%06d", this.score));
    }

    public void draw(){
        this.stage.draw();
    }

    @Override
    public void dispose() {
        this.stage.dispose();
    }
}
