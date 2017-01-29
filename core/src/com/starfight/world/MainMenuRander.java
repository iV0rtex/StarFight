package com.starfight.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.starfight.assets.AssetsLoader;

public class MainMenuRander {
    AssetsLoader assets;
    private SpriteBatch batch;
    private Vector2 positionBG;
    private Vector2 velocityBG;
    private float gameWidth;
    private float gameHeight;


    public MainMenuRander(AssetsLoader assets,float gameWidth, float gameHeight,OrthographicCamera cam){
        this.assets = assets;
        batch = new SpriteBatch();
        this.batch.setProjectionMatrix(cam.combined);
        positionBG = new Vector2();
        velocityBG = new Vector2();
        positionBG.set(0f,0f);
        velocityBG.set(0f,-20f);
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        //assets.effect.setPosition(gameWidth/2,gameHeight /2);
    }
    public void render(float delta){
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        this.renderBG(delta);
        //assets.effect.draw(batch,delta);
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
