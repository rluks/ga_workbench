package at.fhooe.im440.workbench;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import at.fhooe.im440.workbench.entitysystem.CircleColliderComponent;
import at.fhooe.im440.workbench.entitysystem.ColliderComponent;
import at.fhooe.im440.workbench.entitysystem.Component;
import at.fhooe.im440.workbench.entitysystem.SpriteComponent;

public class ColliderRenderSystem extends BaseSystem implements IComponentSystem{

	List<ColliderComponent> colliders;
	ShapeRenderer renderer;
	
	public ColliderRenderSystem(){
		this.colliders = new ArrayList<ColliderComponent>();
		this.renderer = new ShapeRenderer();
	}
	
	public void Render() {
		CameraSystem cam = ServiceLocator.Get(CameraSystem.class);

		renderer.setProjectionMatrix(cam.camera.combined);
	    renderer.begin(ShapeType.Filled);
	    renderer.setColor(Color.CYAN);
	    renderer.scale(1f, 1f, 1f);
	    for(ColliderComponent cc: colliders){
			if (cc.getClass().isAssignableFrom(CircleColliderComponent.class)){
				CircleColliderComponent ccc = CircleColliderComponent.class.cast(cc);
				renderer.circle(ccc.getPosition().x, ccc.getPosition().y, ccc.radius);
			}
		}
	    
	    renderer.end();
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

}
