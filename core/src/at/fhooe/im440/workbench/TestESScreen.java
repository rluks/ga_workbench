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

import at.fhooe.im440.workbench.entitysystem.EntitySystem;

public class TestESScreen extends ScreenAdapter implements InputProcessor {

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
		ServiceLocator.Register(new PhysicsSystem());
		EntitySystem es = new EntitySystem();
		ServiceLocator.Register(es);
		ServiceLocator.Register(new RenderSystem());
		ServiceLocator.Register(new CollisionSystem());
		
		es.InitTestEntities();	
		
		Gdx.input.setInputProcessor(this);//required for InputProcessor
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
	}

	@Override
	public void resize(int width, int height) {
		(ServiceLocator.Get(RenderSystem.class)).resize(width, height);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		RenderSystem rs = ServiceLocator.Get(RenderSystem.class);
		Vector3 origin3d = rs.camera.getPickRay(screenX, screenY).origin;
		Vector2 origin2d = new Vector2(origin3d.x, origin3d.y);
		CollisionSystem cs = ServiceLocator.Get(CollisionSystem.class);
		cs.test(origin2d);
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
		return false;
	}

}
