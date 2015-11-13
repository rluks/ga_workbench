package at.fhooe.im440.workbench;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import at.fhooe.im440.workbench.entitysystem.EntitySystem;

public class TestESScreen extends ScreenAdapter {

	private Workbench workbench;
	//private Stage stage;//TODO is stage useful in Workbench at all? if so...where should stage be? in some system? passed around?
	private boolean dead;
	
	public TestESScreen(Workbench workbench) {
		this.workbench = workbench;
		//stage = workbench.getStage();
	}

	@Override
	public void show() {
		// initialize control variables
		dead = false;
		
		// cleanup
		//stage.clear();
		//stage.getRoot().setPosition(0, 0);
		
		//Note: init systems, then create entities		
		ServiceLocator.Init();
		ServiceLocator.Register(new PhysicsSystem());
		EntitySystem es = new EntitySystem();
		ServiceLocator.Register(es);
		ServiceLocator.Register(new RenderSystem());
		
		es.InitTestEntities();
		
		//stage.setViewport(new FitViewport(Workbench.V_WIDTH, Workbench.V_HEIGHT));
	}
	
	private void endOfState() {
		dead = true;
		workbench.setScreen(new MenuScreen(workbench));
	}
	
	@Override
	public void render(float delta) {	
		ServiceLocator.Update(delta);	
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void dispose() {
		//stage.clear();
	}

	@Override
	public void resize(int width, int height) {
		//stage.getViewport().getCamera().position.set(0, 0, 0);
		//stage.getViewport().update(width, height, false);
		(ServiceLocator.Get(RenderSystem.class)).resize(width, height);
	}

}
