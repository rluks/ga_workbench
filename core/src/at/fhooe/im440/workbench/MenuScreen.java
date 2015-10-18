/*
 * Copyright (c) 2013 Roman Divotkey, Univ. of Applied Sciences Upper Austria. 
 * All rights reserved.
 * 
 * THIS CODE IS PROVIDED AS EDUCATIONAL MATERIAL AND NOT INTENDED TO ADDRESS
 * ALL REAL WORLD PROBLEMS AND ISSUES IN DETAIL.
 */

package at.fhooe.im440.workbench;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MenuScreen extends ScreenAdapter implements InputProcessor {

	private Workbench workbench;
	private BitmapFont font;
	private LabelStyle style;
	private Label label;
	private Label splashLabel;
	private Label exitLabel;
	private Stage stage;
	private int labelDistance = 300; 

	
	public enum MenuItem {
	    Play, Splash, Exit
	};
	
	private int currentMenuPosX = 0;
	private MenuItem currentMenuItem = MenuItem.Play;
	
	private OrthographicCamera camera;
	
	public MenuScreen(Workbench workbench) {
		this.workbench = workbench;
		stage = workbench.getStage();
		this.camera = new OrthographicCamera();
		
		
	}
	

	@Override
	public void show() {
		this.font = new BitmapFont(Gdx.files.internal("arial_black_32/arial_black_32.fnt"));
		this.style = new Label.LabelStyle(font, Color.WHITE);
		
		this.label = new Label(MenuItem.Play.name(), style);
		this.label.setPosition(MenuItem.Play.ordinal()*labelDistance, 0, Align.center);
		this.label.setName(MenuItem.Play.name());
		//label.addAction(Actions.sequence(Actions.delay(5.5f)));
		
		this.splashLabel = new Label(MenuItem.Splash.name(), style);
		this.splashLabel.setPosition(MenuItem.Splash.ordinal()*labelDistance, 0, Align.center);
		this.splashLabel.setName(MenuItem.Splash.name());
		
		this.exitLabel = new Label(MenuItem.Exit.name(), style);
		this.exitLabel.setPosition(MenuItem.Exit.ordinal()*labelDistance, 0, Align.center);
		this.exitLabel.setName(MenuItem.Exit.name());
		
		stage.addActor(label);
		stage.addActor(splashLabel);
		stage.addActor(exitLabel);
		stage.setViewport(new FitViewport(Workbench.V_WIDTH, Workbench.V_HEIGHT));
		
		Gdx.input.setInputProcessor(this);//required for InputProcessor
	}


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 1, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().getCamera().position.set(0, 0, 0);
		stage.getViewport().update(width, height, false);
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void dispose() {
		font.dispose();
		stage.clear();
	}
	
	/////////////////////////////////////////////////
	/////// Interface InputProcessor
	/////////////////////////////////////////////////

	@Override
	public boolean keyDown(int keycode) {	
		return true;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.LEFT){
			
		}
		if(keycode == Keys.RIGHT){
			
		}
		System.out.println(keycode);	
		return false;
	}
	
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return true;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		//currentMenuItem
		//if(currentMenuItem > 0)
		//	currentMenuItem = 0;
		
		currentMenuPosX += amount*labelDistance;
		if(currentMenuPosX > 0)
			currentMenuPosX = 0;
		else if(currentMenuPosX < -2*labelDistance)
			currentMenuPosX = -2*labelDistance;
		
		System.out.println(currentMenuPosX);
		
		stage.getRoot().setPosition(currentMenuPosX, 0);
		return true;
	}

}
