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

import at.fhooe.im440.workbench.entitysystem.Entity;
import at.fhooe.im440.workbench.entitysystem.EntitySystem;
import at.fhooe.im440.workbench.entitysystem.SpriteComponent;
import at.fhooe.im440.workbench.entitysystem.TransformComponent;

public class TestESScreen extends ScreenAdapter {

	private static final Color BG_COL = new Color(1.00f, 0.00f,  0.5f, 1f);
	private static final float FADE_TIME = 0.1f;
	private static final float DISPLAY_TIME = 0.5f;
	private Workbench workbench;
	private Stage stage;//where should stage be? in some system? passed around?
	private boolean dead;
	
	public TestESScreen(Workbench workbench) {
		this.workbench = workbench;
		stage = workbench.getStage();
	}

	@Override
	public void show() {
		// initialize control variables
		dead = false;
		
		// cleanup
		stage.clear();
		stage.getRoot().setPosition(0, 0);
		
		EntitySystem.Init();
		RenderSystem.Init();
		PhysicsSystem.Init();
		Entity e = EntitySystem.GetTest();
		
		SpriteComponent sc = (SpriteComponent) e.GetComponent("SpriteComponent");
		TransformComponent tc = (TransformComponent) e.GetComponent("TransformComponent");
		Actor ent = new Image(sc.Texture);
		ent.setPosition(tc.Position.x,  tc.Position.y, Align.center);
		ent.setName(e.Name);
		
		//assemble stage - where should i do this?
		//stage.addActor(ent);		
		//stage.addActor(img);//order of adding matters
		
		stage.setViewport(new FitViewport(Workbench.V_WIDTH, Workbench.V_HEIGHT));
	}
	
	private void endOfState() {
		dead = true;
		workbench.setScreen(new MenuScreen(workbench));
	}
	
	@Override
	public void render(float delta) {	
		PhysicsSystem.Update(delta);
		RenderSystem.Render(stage, BG_COL);
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void dispose() {
		stage.clear();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().getCamera().position.set(0, 0, 0);
		stage.getViewport().update(width, height, false);
	}

}
