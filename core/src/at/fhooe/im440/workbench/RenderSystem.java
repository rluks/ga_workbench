package at.fhooe.im440.workbench;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import at.fhooe.im440.workbench.entitysystem.Component;
import at.fhooe.im440.workbench.entitysystem.EntitySystem;
import at.fhooe.im440.workbench.entitysystem.SpriteComponent;


public class RenderSystem extends BaseSystem implements IComponentSystem{

	List<SpriteComponent> sprites;
	private SpriteBatch batch;
	
	private OrthographicCamera camera;
	private Viewport viewport;
	
	public RenderSystem(){
		sprites = new ArrayList<SpriteComponent>();
		batch = new SpriteBatch();
		
		this.camera = new OrthographicCamera();
		this.viewport = new FitViewport(800, 480, camera);
	}
	

	public void Render() {
		Color BG_COL = new Color(0.5f, 0.5f,  0.7f, 1f);
		Gdx.gl.glClearColor(BG_COL.r, BG_COL.g, BG_COL.b, BG_COL.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		//batch.enableBlending();
		//batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		batch.begin();
       
		for(SpriteComponent sc: sprites){
			sc.Render(batch);
		}
		
		 batch.end();

	}


	@Override
	public void Update(float delta) {
		Render();	
	}
	
	public void resize(int width, int height) {
		float ar = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
		camera.setToOrtho(false, Workbench.V_WIDTH, Workbench.V_WIDTH / ar);
		camera.position.set(0, 0, 0);
		camera.update();
		viewport.update(width, height);
	}


	@Override
	public void Register(Component component) {
		sprites.add((SpriteComponent) component);
	}


	@Override
	public void Unregister(Component component) {
		sprites.remove((SpriteComponent) component);
	}

}
