package at.fhooe.im440.workbench.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import at.fhooe.im440.workbench.ServiceLocator;
import at.fhooe.im440.workbench.systems.entity.Entity;

public class InputSystem extends BaseSystem implements InputProcessor {

	public InputSystem(){
		Gdx.input.setInputProcessor(this);//required for InputProcessor
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
		CameraSystem cam = ServiceLocator.Get(CameraSystem.class);

		Vector3 origin3d = cam.camera.unproject(new Vector3(screenX, screenY, 0));
		//Vector3 origin3d = rs.camera.getPickRay(screenX, screenY).origin;
		Vector2 origin2d = new Vector2(origin3d.x, origin3d.y);
		
		CollisionSystem cs = ServiceLocator.Get(CollisionSystem.class);
		//cs.test(origin2d);
		Entity clickedEntity = cs.GetEntityByPos(origin2d);
		
		EditorSystem editSys = ServiceLocator.Get(EditorSystem.class);
		if(clickedEntity != null){			
			editSys.selected(clickedEntity);
		}else{
			editSys.deselectAll();
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		CameraSystem cam = ServiceLocator.Get(CameraSystem.class);

		Vector3 origin3d = cam.camera.unproject(new Vector3(screenX, screenY, 0));
		Vector2 origin2d = new Vector2(origin3d.x, origin3d.y);
		
		EditorSystem editSys = ServiceLocator.Get(EditorSystem.class);
		editSys.applyDrag(origin2d);
		
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

	@Override
	public void Update(float delta) {
		// TODO Auto-generated method stub

	}

}
