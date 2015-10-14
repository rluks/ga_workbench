/*
 * Copyright (c) 2013 Roman Divotkey, Univ. of Applied Sciences Upper Austria. 
 * All rights reserved.
 * 
 * THIS CODE IS PROVIDED AS EDUCATIONAL MATERIAL AND NOT INTENDED TO ADDRESS
 * ALL REAL WORLD PROBLEMS AND ISSUES IN DETAIL.
 */

package at.fhooe.im440.workbench;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;

public class MenuScreen extends ScreenAdapter {

	private Workbench workbench;
	private BitmapFont font;
	private LabelStyle style;
	private Label label;
	
	private OrthographicCamera camera;
	
	public MenuScreen(Workbench workbench) {
		this.workbench = workbench;
		this.camera = new OrthographicCamera();
		
		this.font = new BitmapFont(Gdx.files.internal("arial_black_32.fnt"));
		this.style = new Label.LabelStyle(font, Color.WHITE);
		String text = "hello world";
		this.label = new Label(text, style);
		this.label.setAlignment(Align.center);
	}
	

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
	}


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void resize(int width, int height) {
		float ar = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
		camera.setToOrtho(false, Workbench.V_WIDTH, Workbench.V_WIDTH / ar);
		camera.position.set(0, 0, 0);
		camera.update();
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void dispose() {
		//super.dispose();
		font.dispose();
	}

}
