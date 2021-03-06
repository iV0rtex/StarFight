package com.starfight.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public class AssetsLoader extends AssetManager {
    public TextureRegion enemyplain;
    public Sprite sprite;
    public ParticleEffect effect;
    public void loadAll(){
        this.load("data/sky.jpg", Texture.class);
        this.load("data/userplain.png",Texture.class);
        this.load("data/enemyplain.jpg",Texture.class);
        this.load("data/screenSlowMenu.png",Texture.class);
        this.load("data/pauseUp.png",Texture.class);
        this.load("data/pauseDown.png",Texture.class);
        this.load("data/backToGame.png",Texture.class);
        this.finishLoading();
        enemyplain = new TextureRegion((Texture) this.get("data/enemyplain.jpg"), 0, 0, 470, 450);
        sprite = new Sprite((Texture) this.get("data/screenSlowMenu.png"));
    }
    public void loadMainMenu(){
        this.load("data/sky.jpg", Texture.class);
        this.load("data/userplain.png",Texture.class);
        this.load("data/pauseUp.png",Texture.class);
        this.load("data/pauseDown.png",Texture.class);
        this.load("data/SetMenuLevels.png",Texture.class);
        this.load("data/toGarage.png",Texture.class);
        this.finishLoading();
        /*effect = new ParticleEffect();
        effect.load(Gdx.files.internal("data/IMGTest.p"),Gdx.files.internal("data"));
        effect.start();*/
    }
    @Override
    public synchronized void dispose (){
        super.dispose();
    }

}
