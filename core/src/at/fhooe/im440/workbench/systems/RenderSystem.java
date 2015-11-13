package at.fhooe.im440.workbench.systems;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import at.fhooe.im440.workbench.ServiceLocator;
import at.fhooe.im440.workbench.entitysystem.Component;
import at.fhooe.im440.workbench.entitysystem.EntitySystem;
import at.fhooe.im440.workbench.entitysystem.SpriteComponent;


public class RenderSystem extends BaseSystem implements IComponentSystem{

	List<SpriteComponent> sprites;
	private SpriteBatch batch;
	
	public RenderSystem(){
		sprites = new ArrayList<SpriteComponent>();
		batch = new SpriteBatch();
	}

	public void Render() {
		Color BG_COL = new Color(0.5f, 0.5f,  0.7f, 1f);
		Gdx.gl.glClearColor(BG_COL.r, BG_COL.g, BG_COL.b, BG_COL.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		CameraSystem cam = ServiceLocator.Get(CameraSystem.class);
		
		batch.setProjectionMatrix(cam.camera.combined);
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

	@Override
	public void Register(Component component) {
		sprites.add((SpriteComponent) component);
	}


	@Override
	public void Unregister(Component component) {
		sprites.remove((SpriteComponent) component);
	}

}
