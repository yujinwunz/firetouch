package com.yujinwunz.firetouch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import java.util.concurrent.Callable;

/**
 * Created by yujinwunz on 11/03/2017.
 */

public class MainMenu extends ScreenAdapter {

	Stage stage = new Stage();
	static BitmapFont font;

	static Skin skin;
	static {
		skin = new Skin();
		TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack.atlas"));
		skin.addRegions(buttonAtlas);
		font = new BitmapFont(Gdx.files.internal("fonts/prstartk-large.fnt"));
		for (String drawableName: new String[]{"up-button", "down-button", "checked-button"}) {
			Drawable d = skin.getDrawable(drawableName);
			d.setBottomHeight(20);
			d.setLeftWidth(20);
			d.setTopHeight(20);
			d.setRightWidth(20);
		}
	}

	public MainMenu(final Callable onClick) {
		Table table = new Table();
		table.setFillParent(true);
		table.center();

		// Add instructions
		Label instructions = new Label(
				"Tap and drag around to create relaxing fires",
				new Label.LabelStyle(font, new Color(0.7f, 0.7f, 0, 1))
		);

		table.add(instructions);
		table.row();

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("up-button");
		textButtonStyle.down = skin.getDrawable("down-button");
		textButtonStyle.checked = skin.getDrawable("checked-button");
		textButtonStyle.checkedFontColor =
				new Color(0.5f, 0.5f, 0, 1);
		textButtonStyle.downFontColor =
				new Color(0.3f, 0.0f, 0, 1);
		textButtonStyle.fontColor =
				new Color(0.5f, 0.5f, 0, 1);

		Button menuButton = new Button();
		textButtonStyle.font = font;
		Button button = new TextButton("OK", textButtonStyle);
		button.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				try {
					onClick.call();
				} catch (Exception e) {
					Gdx.app.error("Menu", "Menu item exception", e);
				}
			}
		});
		table.add(button).pad(50);

		stage.addActor(table);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}
}
