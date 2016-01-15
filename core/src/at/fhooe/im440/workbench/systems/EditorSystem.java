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
	EditorTool currentTool;
	
	public EditorSystem(){
		entities = new LinkedHashMap<Entity, Boolean>();
		es = ServiceLocator.Get(EntitySystem.class);
		
		for(Entity e : es.GetEntities()){
			entities.put(e, false);
		}
		
		this.renderer = new ShapeRenderer();
		this.color = Color.FOREST;
		this.color.a = 0.2f;
		
		this.currentTool = new EditorMove();
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
		//call currently selected editor tool with drag vector
		//eg. rotate
		//use state pattern
		//each tool will handle it on its own
		//for each entity <- or whole selection? what is better?
		for(Entity e : this.getSelected()){
			this.currentTool.applyDrag(e, v);
			
		}
	}
	
	public void Render() {
		CameraSystem cam = ServiceLocator.Get(CameraSystem.class);

		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glEnable(GL30.GL_BLEND);
		
		renderer.setProjectionMatrix(cam.camera.combined);
		renderer.setColor(this.color);
	    renderer.begin(ShapeType.Filled);
	    renderer.setColor(this.color);
	    
	    for(Entity e : getSelected()){
	    	ColliderComponent ccc = e.GetComponent(ColliderComponent.class);
	    	ccc.Render(renderer);
	    }
	    
    	renderer.end();
	    
	    Gdx.gl.glDisable(GL30.GL_BLEND);
	}
	
	
	@Override
	public void Update(float delta) {
		Render();
	}

	public void switchTool() {
		if(this.currentTool.getClass() == EditorMove.class){
			System.out.println("Editor - Rotation tool");
			this.currentTool = new EditorRotate();
		}else{
			this.currentTool = new EditorMove();
			System.out.println("Editor - Move tool");
			//TODO not clean creating new instances
			//each tool stores its name, welcome message, etc.
			//config params like amount of effect applied (eg. for zoom tool)
		}
			
		
	}

}
