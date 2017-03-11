package com.yujinwunz.firetouch;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.concurrent.Callable;

public class FireTouch extends Game {
	SpriteBatch batch;
	Texture img;
	OrthographicCamera camera = new OrthographicCamera();
	ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		camera.setToOrtho(false, 800, 400);
		final FireTouch me = this;
		this.setScreen(new MainMenu(new Callable() {
			@Override
			public Object call() throws Exception {
				me.setScreen(new FireTouchGame(me));
				return null;
			}
		}));
	}

	@Override
	public void render () {
		super.render();
	}
}
