package at.fhooe.im440.workbench;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CameraSystem extends BaseSystem {

	public OrthographicCamera camera;
	public Viewport viewport;
	
	
	public CameraSystem() {
		this.camera = new OrthographicCamera();
		this.viewport = new FitViewport(800, 480, camera);
	}


	@Override
	public void Update(float delta) {
		// TODO Auto-generated method stub

	}
	
	public void resize(int width, int height) {
		float ar = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
		camera.setToOrtho(false, Workbench.V_WIDTH, Workbench.V_WIDTH / ar);
		camera.position.set(0, 0, 0);
		camera.update();
		viewport.update(width, height);
	}

}
