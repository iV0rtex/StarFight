package com.starfight.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.starfight.controlEnemy.ControlEnemy;
import com.starfight.gameObject.PlayerShip;
import com.starfight.gameObject.StaticAttack;
import com.starfight.gameObject.enemies.EnemyV1;

import java.util.ArrayList;

public class GameRander {
    private ShapeRenderer shapeRenderer;
    private PlayerShip ship;
    private ControlEnemy enemies;
    public GameRander(GameWorld world, float gameWidth, float gameHeight){
        OrthographicCamera cam = new OrthographicCamera();
        cam.setToOrtho(false, gameWidth, gameHeight);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        ship = world.getPlayer();
        enemies = world.getEnemies();
    }
    public void render(){
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        this.shapeRenderer.setColor(0.21568628F, 0.3137255F, 0.39215687F, 1.0F);
        this.shapeRenderer.rect(ship.position.x,ship.position.y,ship.getOption("width"),ship.getOption("height"));
        ArrayList<StaticAttack> attacks = ship.getListAttack();
        for (StaticAttack attack : attacks){
            this.shapeRenderer.setColor(0.21568628F, 0.3137255F, 0.39215687F, 1.0F);
            this.shapeRenderer.rect(attack.position.x,attack.position.y,attack.getOption("width"),attack.getOption("height"));
        }
        ArrayList<EnemyV1> listEnemies = enemies.getListEnemies();
        for (EnemyV1 enemy : listEnemies){
            this.shapeRenderer.setColor(0.21568628F, 0.3137255F, 0.39215687F, 1.0F);
            this.shapeRenderer.rect(enemy.position.x,enemy.position.y,enemy.getOption("width"),enemy.getOption("height"));
            Rectangle healthBody = enemy.getHealthBody().getBody();
            this.shapeRenderer.rect(healthBody.getX(),healthBody.getY(),healthBody.getWidth(),healthBody.getHeight());
        }
        this.shapeRenderer.end();

    }
}
