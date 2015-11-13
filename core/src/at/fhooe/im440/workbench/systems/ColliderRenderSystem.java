package at.fhooe.im440.workbench.systems;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import at.fhooe.im440.workbench.ServiceLocator;
import at.fhooe.im440.workbench.entitysystem.CircleColliderComponent;
import at.fhooe.im440.workbench.entitysystem.ColliderComponent;
import at.fhooe.im440.workbench.entitysystem.Component;
import at.fhooe.im440.workbench.entitysystem.SpriteComponent;

public class ColliderRenderSystem extends BaseSystem implements IComponentSystem{

	List<ColliderComponent> colliders;
	ShapeRenderer renderer;
	Color color;
	
	public ColliderRenderSystem(){
		this.colliders = new ArrayList<ColliderComponent>();
		this.renderer = new ShapeRenderer();
		this.color = Color.CYAN;
		this.color.a = 0.3f;
	}
	
	public void Render() {
		//Color BG_COL = new Color(0.5f, 0.5f,  0.7f, 1f);
		//Gdx.gl.glClearColor(BG_COL.r, BG_COL.g, BG_COL.b, BG_COL.a);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		CameraSystem cam = ServiceLocator.Get(CameraSystem.class);

		//Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		//Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		//Gdx.gl.glEnable(GL20.GL_BLEND);
		
		renderer.setProjectionMatrix(cam.camera.combined);
	    renderer.begin(ShapeType.Line);
	    renderer.setColor(this.color);
	    //renderer.scale(1f, 1f, 1f);
	    for(ColliderComponent cc: colliders){
			if (cc.getClass().isAssignableFrom(CircleColliderComponent.class)){
				CircleColliderComponent ccc = CircleColliderComponent.class.cast(cc);
				renderer.circle(ccc.getPosition().x, ccc.getPosition().y, ccc.radius);
				renderer.setColor(Color.RED);
				renderer.point(ccc.getPosition().x, ccc.getPosition().y, 0);
			}
		}
	    //Gdx.gl.glDisable(GL20.GL_BLEND);
	    
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
