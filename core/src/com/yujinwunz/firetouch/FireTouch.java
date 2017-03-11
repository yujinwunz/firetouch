package com.yujinwunz.firetouch;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.concurrent.Callable;

public class FireTouch extends Game {
	SpriteBatch batch;
	Texture img;
	OrthographicCamera camera;
	ShapeRenderer shapeRenderer;

	@Override
	public void create () {
		Gdx.app	.setLogLevel(Application.LOG_DEBUG);
		Gdx.app.debug("create", "Create was called");
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
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
	public void resume() {
		super.resume();
		Gdx.app.log("resume", "Resume was called");
	}

	@Override
	public void pause() {
		super.resume();
		Gdx.app.log("pause", "Pause was called");
	}

	@Override
	public void dispose() {
		super.resume();
		Gdx.app.log("dispose", "Dispose was called");
	}


	@Override
	public void render () {
		super.render();
	}
}
