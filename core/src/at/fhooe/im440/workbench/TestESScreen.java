package at.fhooe.im440.workbench;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import at.fhooe.im440.workbench.systems.CameraSystem;
import at.fhooe.im440.workbench.systems.ColliderRenderSystem;
import at.fhooe.im440.workbench.systems.CollisionSystem;
import at.fhooe.im440.workbench.systems.EditorSystem;
import at.fhooe.im440.workbench.systems.InputSystem;
import at.fhooe.im440.workbench.systems.PhysicsSystem;
import at.fhooe.im440.workbench.systems.RenderSystem;
import at.fhooe.im440.workbench.systems.entity.EntitySystem;

public class TestESScreen extends ScreenAdapter {

	private Workbench workbench;
	//private Stage stage;//TODO is stage useful in Workbench at all? if so...where should stage be? in some system? passed around?
	private boolean dead;
	
	public TestESScreen(Workbench workbench) {
		this.workbench = workbench;
	}

	@Override
	public void show() {
		// initialize control variables
		dead = false;
		
		//Note: init systems, then create entities		
		ServiceLocator.Init();
		ServiceLocator.Register(new CameraSystem());
		ServiceLocator.Register(new PhysicsSystem());
		EntitySystem es = new EntitySystem();
		ServiceLocator.Register(es);
		ServiceLocator.Register(new RenderSystem());
		ServiceLocator.Register(new ColliderRenderSystem());
		ServiceLocator.Register(new CollisionSystem());
		ServiceLocator.Register(new InputSystem());
		
		es.InitTestEntities();	
		
		ServiceLocator.Register(new EditorSystem());
		
		
	}
	
	private void endOfState() {
		dead = true;
		workbench.setScreen(new MenuScreen(workbench));
	}
	
	@Override
	public void render(float delta) {	
		
		ServiceLocator.Update(delta);	
		
		//ServiceLocator.LateUpdate();//TODO where could i call this?
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void resize(int width, int height) {
		(ServiceLocator.Get(CameraSystem.class)).resize(width, height);
	}	

}
