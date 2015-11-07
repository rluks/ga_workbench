package at.fhooe.im440.workbench;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class TestESScreen extends ScreenAdapter {

	private static final Color BG_COL = new Color(1.00f, 0.00f,  0.5f, 1f);
	private static final float FADE_TIME = 0.1f;
	private static final float DISPLAY_TIME = 0.1f;
	private Workbench workbench;
	private Texture texture;
	private Stage stage;
	private boolean dead;
	
	public TestESScreen(Workbench workbench) {
		this.workbench = workbench;
		stage = workbench.getStage();
	}

	@Override
	public void show() {
		// initialize control variables
		dead = false;
		
		// load resources
		Texture texture = new Texture("splash.png");
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		// cleanup
		stage.clear();
		stage.getRoot().setPosition(0, 0);
		
		//assemble stage
		Actor img = new Image(texture);
		img.setPosition(0,  0, Align.center);
		img.getColor().a = 0;
		img.setName("splash");
		
		img.addAction(Actions.sequence(
				Actions.delay(0.5f),
				Actions.fadeIn(FADE_TIME),
				Actions.delay(DISPLAY_TIME),
				Actions.run(new Runnable() {
					@Override
					public void run() {
						fadeOut();
					}
				})
				));

		stage.addActor(img);
		stage.setViewport(new FitViewport(Workbench.V_WIDTH, Workbench.V_HEIGHT));
	}
	
	private void fadeOut() {
		if (dead) return;
		dead = true;

		Actor img = stage.getRoot().findActor("splash");
		assert(img != null);
		img.clearActions();
		img.addAction(Actions.sequence(
				Actions.fadeOut(FADE_TIME),
				Actions.run(new Runnable() {
					@Override
					public void run() {
						//endOfState();
					}
				})));
	}
	
	
	private void endOfState() {
		dead = true;
		workbench.setScreen(new MenuScreen(workbench));
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(BG_COL.r, BG_COL.g, BG_COL.b, BG_COL.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void dispose() {
		texture.dispose();
		stage.clear();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().getCamera().position.set(0, 0, 0);
		stage.getViewport().update(width, height, false);
	}

}
