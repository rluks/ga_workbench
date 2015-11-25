package at.fhooe.im440.workbench.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import at.fhooe.im440.workbench.Workbench;

public class CameraSystem extends BaseSystem {

	public OrthographicCamera camera;
	public Viewport viewport;
	
	
	public CameraSystem() {
		this.camera = new OrthographicCamera();
		this.viewport = new FitViewport(Workbench.V_WIDTH, Workbench.V_HEIGHT, camera);
	}


	@Override
	public void Update(float delta) {
		// TODO Auto-generated method stub

	}
	
	public void resize(int width, int height) {
		System.out.println("resize: " + width + ", " + height);
		float ar = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
		camera.setToOrtho(false, Workbench.V_WIDTH, Workbench.V_WIDTH / ar);
		camera.position.set(0, 0, 0);
		camera.update();
		viewport.update(width, height);
	}

}
