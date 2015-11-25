package at.fhooe.im440.workbench.systems;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import at.fhooe.im440.workbench.ServiceLocator;
import at.fhooe.im440.workbench.systems.entity.CircleColliderComponent;
import at.fhooe.im440.workbench.systems.entity.ColliderComponent;
import at.fhooe.im440.workbench.systems.entity.Component;
import at.fhooe.im440.workbench.systems.entity.SpriteComponent;

public class ColliderRenderSystem extends BaseSystem implements IComponentSystem{

	List<ColliderComponent> colliders;
	ShapeRenderer renderer;
	Color color;
	Color collidingColor;
	
	public ColliderRenderSystem(){
		this.colliders = new ArrayList<ColliderComponent>();
		this.renderer = new ShapeRenderer();
		this.color = Color.CYAN;
		this.color.a = 0.2f;
		this.collidingColor = Color.PURPLE;
		this.collidingColor.a = 0.2f;
	}
	
	public void Render() {
		CameraSystem cam = ServiceLocator.Get(CameraSystem.class);

		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glEnable(GL30.GL_BLEND);
		
		renderer.setProjectionMatrix(cam.camera.combined);
	    renderer.begin(ShapeType.Filled);
	    renderer.identity();
	    for(ColliderComponent cc: colliders){	    	
	    	setRendererColor(cc.isColliding);
	    	cc.Render(renderer);
		}	    
	    
	    renderer.end();
	    
	    Gdx.gl.glDisable(GL30.GL_BLEND);
	}
	
	@Override
	public void Update(float delta) {
		Render();
	}

	@Override
	public void Register(Component component) {
		colliders.add((ColliderComponent) component);	
	}

	@Override
	public void Unregister(Component component) {
		colliders.remove((ColliderComponent) component);
	}
	
	void setRendererColor(boolean collides){
		if(collides)
			this.renderer.setColor(this.collidingColor);
		else
			this.renderer.setColor(this.color);
	}

}
