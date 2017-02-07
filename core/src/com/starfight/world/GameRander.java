package com.starfight.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.starfight.assets.AssetsLoader;
import com.starfight.controlEnemy.ControlEnemy;
import com.starfight.gameObject.PlayerShip;
import com.starfight.gameObject.StaticAttack;
import com.starfight.gameObject.enemies.DropSpares;
import com.starfight.gameObject.enemies.EnemyV1;
import com.starfight.ui.simpleButton.circleButton;

import java.util.ArrayList;

public class GameRander{

    private ShapeRenderer shapeRenderer;
    private PlayerShip ship;
    private ControlEnemy enemies;
    private AssetsLoader assets;
    private SpriteBatch batch;
    private Vector2 positionBG;
    private Vector2 velocityBG;
    private float gameWidth;
    private float gameHeight;
    private GameWorld world;
    public GameRander(GameWorld world, OrthographicCamera cam, AssetsLoader assets, float gameWidth, float gameHeight){

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        ship = world.getPlayer();
        enemies = world.getEnemies();
        this.world = world;
        this.assets = assets;
        batch = new SpriteBatch();
        this.batch.setProjectionMatrix(cam.combined);
        positionBG = new Vector2();
        velocityBG = new Vector2();
        positionBG.set(0f,0f);
        velocityBG.set(0f,-20f);
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
    }
    public void render(float delta){
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //this.cam.update();
        batch.begin();
        //this.batch.disableBlending();
        this.renderBG(delta);
        //this.batch.enableBlending();
        if(ship.getObjectStatus()){
            Texture userplain = assets.get("data/userplain.png");
            batch.draw(userplain,ship.position.x,ship.position.y,ship.getOption("width"),ship.getOption("height"));
        }
        ArrayList<EnemyV1> listEnemies = enemies.getListEnemies();

        for (EnemyV1 enemy : listEnemies){
            batch.draw(assets.enemyplain,enemy.position.x,enemy.position.y,enemy.getOption("width"),enemy.getOption("height"));
        }
        batch.end();
        ArrayList<DropSpares> listSpares = enemies.getListSpares();

        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        this.shapeRenderer.setColor(0.21568628F, 0.3137255F, 0.39215687F, 1.0F);
        for (DropSpares enemySpares : listSpares){
            this.shapeRenderer.circle(enemySpares.getBody().x,enemySpares.getBody().y,enemySpares.getBody().radius);
        }
        this.shapeRenderer.setColor(0.21568628F, 0.3137255F, 0.39215687F, 1.0F);
        for (EnemyV1 enemy : listEnemies){
            Rectangle healthBody = enemy.getHealthBody().getBody();
            this.shapeRenderer.rect(healthBody.getX(),healthBody.getY(),healthBody.getWidth(),healthBody.getHeight());
        }

        ArrayList<StaticAttack> attacks = ship.getListAttack();
        for (StaticAttack attack : attacks){
            this.shapeRenderer.setColor(0.21568628F, 0.3137255F, 0.39215687F, 1.0F);
            this.shapeRenderer.rect(attack.position.x,attack.position.y,attack.getOption("width"),attack.getOption("height"));
        }

        this.shapeRenderer.end();
        if (world.isSlow()){
            renderSlowMenu();
        }else if(world.isPause()){
            renderPauseMenu();
        }

    }
    private void renderSlowMenu(){
        assets.sprite.setColor(1,1,1,.5f);
        assets.sprite.setSize(this.gameWidth,this.gameHeight);
        assets.sprite.setPosition(0,0);
        batch.begin();
        assets.sprite.draw(batch);
        ArrayList<circleButton> buttonList = world.getMenuSlow().getButtonList();
        for (circleButton button : buttonList){
            Circle body = (Circle) button.getBody();
            batch.draw(button.getButtonTexture(),body.x,body.y,body.radius,body.radius);
        }
        batch.end();
    }
    private void renderPauseMenu(){
        assets.sprite.setColor(1,1,1,.5f);
        assets.sprite.setSize(this.gameWidth,this.gameHeight);
        assets.sprite.setPosition(0,0);
        batch.begin();
        assets.sprite.draw(batch);
        ArrayList<circleButton> buttonList = world.getMenuPause().getButtonList();
        for (circleButton button : buttonList){
            Rectangle body = (Rectangle) button.getBody();
            batch.draw(button.getButtonTexture(),body.x,body.y,body.width,body.height);
        }
        batch.end();
    }
    private void renderBG(float delta){
        Texture bg = assets.get("data/sky.jpg");
        Vector2 newPosit = this.velocityBG.cpy().scl(delta);
        if(Math.abs(this.positionBG.y)+Math.abs(newPosit.y) >= this.gameHeight){
            this.positionBG.y = positionBG.y+this.gameHeight - newPosit.y;
        }else{
            this.positionBG.add(newPosit);
        }
        batch.draw(bg,0f,positionBG.y,this.gameWidth,this.gameHeight);
        batch.draw(bg,0f,positionBG.y+this.gameHeight,this.gameWidth,this.gameHeight);
    }
}
