package at.fhooe.im440.workbench.systems;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

import at.fhooe.im440.workbench.ServiceLocator;
import at.fhooe.im440.workbench.systems.entity.CircleColliderComponent;
import at.fhooe.im440.workbench.systems.entity.ColliderComponent;
import at.fhooe.im440.workbench.systems.entity.Entity;
import at.fhooe.im440.workbench.systems.entity.EntitySystem;
import at.fhooe.im440.workbench.systems.entity.TransformComponent;

public class EditorSystem extends BaseSystem {

	LinkedHashMap<Entity, Boolean> entities;
	EntitySystem es;
	ShapeRenderer renderer;
	Color color;
	
	public EditorSystem(){
		entities = new LinkedHashMap<Entity, Boolean>();
		es = ServiceLocator.Get(EntitySystem.class);
		
		for(Entity e : es.GetEntities()){
			entities.put(e, false);
		}
		
		this.renderer = new ShapeRenderer();
		this.color = Color.FOREST;
		this.color.a = 0.2f;
	}
	
	List<Entity> getSelected(){
		List<Entity> selected = new ArrayList<Entity>();
		for(Entry<Entity, Boolean> e : entities.entrySet()){
			if(e.getValue()){
				selected.add(e.getKey());
			}
		}
		
		return selected;
	}
	
	public void selected(Entity e){
		System.out.println("Editor: selected: " + e);
		entities.put(e, true);
	}
	
	public void deselected(Entity e){
		entities.put(e, false);
	}
	
	public void deselectAll(){
		for(Entity e : entities.keySet()){
			entities.put(e, false);
		}
	}
	
	public void applyDrag(Vector2 v){
		for(Entity e : this.getSelected()){
			TransformComponent tf = e.GetComponent(TransformComponent.class);		
			tf.Position.add(v);
		}
	}
	
	public void Render() {
		CameraSystem cam = ServiceLocator.Get(CameraSystem.class);

		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glEnable(GL30.GL_BLEND);
		
		renderer.setProjectionMatrix(cam.camera.combined);
		renderer.setColor(this.color);
	    renderer.begin(ShapeType.Filled);
	    
	    for(Entity e : getSelected()){
	    	CircleColliderComponent ccc = e.GetComponent(CircleColliderComponent.class);
			renderer.setColor(this.color);
			renderer.circle(ccc.getPosition().x, ccc.getPosition().y, ccc.radius);
	    }
	    
    	renderer.end();
	    
	    Gdx.gl.glDisable(GL30.GL_BLEND);
	}
	
	
	@Override
	public void Update(float delta) {
		Render();
	}

	@Override
	public void LateUpdate() {
		// TODO Auto-generated method stub
		
	}

}
