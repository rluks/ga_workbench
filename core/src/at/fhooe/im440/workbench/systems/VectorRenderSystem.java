package at.fhooe.im440.workbench.systems;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

import at.fhooe.im440.workbench.ServiceLocator;
import at.fhooe.im440.workbench.systems.entity.CircleColliderComponent;
import at.fhooe.im440.workbench.systems.entity.ColliderComponent;

public class VectorRenderSystem extends BaseSystem {

	List<Vector2> origins;
	List<Vector2> destinations;
	List<Color> cs;
	
	ShapeRenderer renderer;
	float width = 2.0f;
	
	public VectorRenderSystem(){
		origins = new ArrayList<Vector2>();
		destinations = new ArrayList<Vector2>();
		cs = new ArrayList<Color>();
		
		this.renderer = new ShapeRenderer();
	}
	
	public void RenderVector(Vector2 o, Vector2 d, com.badlogic.gdx.graphics.Color color){
		origins.add(o);
		destinations.add(d);
		cs.add(color);
	}
	
	void Cleanup(){
		origins.clear();
		destinations.clear();
		cs.clear();
	}
	
	void Render(){
		CameraSystem cam = ServiceLocator.Get(CameraSystem.class);

		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glEnable(GL30.GL_BLEND);
		
		renderer.setProjectionMatrix(cam.camera.combined);
	    renderer.begin(ShapeType.Filled);
	    
	    for(int i = 0; i < origins.size(); i++){
	    	renderer.setColor(cs.get(i));
	    	renderer.rectLine(origins.get(i), destinations.get(i), width);
	    }	
	    
	    renderer.end();
	    
	    Gdx.gl.glDisable(GL30.GL_BLEND);
	}
	
	@Override
	public void Update(float delta) {
		Render();
		Cleanup();
	}

}
