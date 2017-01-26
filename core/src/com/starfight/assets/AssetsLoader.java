package com.starfight.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetsLoader extends AssetManager {
    public TextureRegion enemyplain;
    public Sprite sprite;
    public void loadAll(){
        this.load("data/sky.jpg", Texture.class);
        this.load("data/userplain.png",Texture.class);
        this.load("data/enemyplain.jpg",Texture.class);
        this.load("data/screenSlowMenu.png",Texture.class);
        this.finishLoading();
        enemyplain = new TextureRegion((Texture) this.get("data/enemyplain.jpg"), 0, 0, 470, 450);
        sprite = new Sprite((Texture) this.get("data/screenSlowMenu.png"));
    }

}
