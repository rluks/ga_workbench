package at.fhooe.im440.workbench;

import com.badlogic.gdx.ScreenAdapter;

import at.fhooe.im440.workbench.systems.CameraSystem;
import at.fhooe.im440.workbench.systems.ColliderRenderSystem;
import at.fhooe.im440.workbench.systems.CollisionSystem;
import at.fhooe.im440.workbench.systems.EditorSystem;
import at.fhooe.im440.workbench.systems.EntityFactorySystem;
import at.fhooe.im440.workbench.systems.InputSystem;
import at.fhooe.im440.workbench.systems.PhysicsSystem;
import at.fhooe.im440.workbench.systems.RenderSystem;
import at.fhooe.im440.workbench.systems.VectorRenderSystem;
import at.fhooe.im440.workbench.systems.entity.EntitySystem;

public class TestESScreen extends ScreenAdapter {

	private Workbench workbench;
	//private Stage stage;//TODO is stage useful in Workbench at all? if so...where should stage be? in some system? passed around?
	//stage just for UI, perhaps
	
	//private boolean dead;
	
	public TestESScreen(Workbench workbench) {
		this.workbench = workbench;
	}

	@Override
	public void show() {
		// initialize control variables
		//dead = false;
		
		//Note: init systems, then create entities		
		ServiceLocator.Init();
		ServiceLocator.Register(new CameraSystem());
		ServiceLocator.Register(new PhysicsSystem());
		ServiceLocator.Register(new EntityFactorySystem());
		EntitySystem es = new EntitySystem();
		ServiceLocator.Register(es);
		ServiceLocator.Register(new RenderSystem());
		ServiceLocator.Register(new ColliderRenderSystem());
		ServiceLocator.Register(new CollisionSystem());
		ServiceLocator.Register(new InputSystem(workbench));
		ServiceLocator.Register(new VectorRenderSystem());
		
		es.InitTestEntities();	
		es.InitBoxEntities();
		
		ServiceLocator.Register(new EditorSystem());
		
		
	}
	
	//private void endOfState() {
		//dead = true;
	//	workbench.setScreen(new MenuScreen(workbench));
	//}
	
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
	}

	@Override
	public void resize(int width, int height) {
		(ServiceLocator.Get(CameraSystem.class)).resize(width, height);
	}	

}
