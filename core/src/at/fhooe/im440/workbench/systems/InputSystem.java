package at.fhooe.im440.workbench.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import at.fhooe.im440.workbench.ServiceLocator;
import at.fhooe.im440.workbench.systems.entity.Entity;

public class InputSystem extends BaseSystem implements InputProcessor {

	Vector2 startingPos;
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
		RenderSystem rs = ServiceLocator.Get(RenderSystem.class);
		CameraSystem cam = ServiceLocator.Get(CameraSystem.class);

		Vector3 origin3d = cam.camera.unproject(new Vector3(screenX, screenY, 0));
		Vector2 origin2d = new Vector2(origin3d.x, origin3d.y);
		startingPos = origin2d;
		
		//System.out.println("touchdown: startingPos:" + startingPos);
		
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
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		CameraSystem cam = ServiceLocator.Get(CameraSystem.class);

		Vector3 origin3d = cam.camera.unproject(new Vector3(screenX, screenY, 0));
		Vector2 origin2d = new Vector2(origin3d.x, origin3d.y);
		Vector2 origin_backup = new Vector2(origin3d.x, origin3d.y);
		
		//System.out.println("drag");
		//System.out.println("drag: origin2d:" + origin2d);
		
		EditorSystem editSys = ServiceLocator.Get(EditorSystem.class);
		
		//System.out.println("drag: startingPos:" + startingPos);
		
		double distance = Math.sqrt(Math.pow(origin2d.x - startingPos.x, 2) + Math.pow(origin2d.y - startingPos.y, 2));
		Vector2 deltaPos = origin2d.sub(startingPos).nor().scl((float) distance);//this changes the original vector!!!
		
		//System.out.println("drag: distance:" + distance);
		//System.out.println("drag: deltaPos:" + deltaPos);
		
		editSys.applyDrag(deltaPos);
		startingPos = new Vector2(origin_backup.x, origin_backup.y);//TODO so backup vector is needed, or is there a better way?
		
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
