package at.fhooe.im440.workbench.systems;

import com.badlogic.gdx.math.Vector2;

import at.fhooe.im440.workbench.systems.entity.Entity;
import at.fhooe.im440.workbench.systems.entity.TransformComponent;

public class EditorMove extends EditorTool {

	public void applyDrag(Entity e, Vector2 v) {
		TransformComponent tf = e.GetComponent(TransformComponent.class);		
		tf.Position.add(v);	
	}
}
