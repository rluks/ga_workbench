package at.fhooe.im440.workbench.systems;

import com.badlogic.gdx.math.Vector2;

import at.fhooe.im440.workbench.systems.entity.Entity;
import at.fhooe.im440.workbench.systems.entity.TransformComponent;

public class EditorRotate extends EditorTool {

	@Override
	public void applyDrag(Entity e, Vector2 v) {
		TransformComponent tf = e.GetComponent(TransformComponent.class);	
		
		float rotationDelta = v.len();
		if(v.y < 0)
			rotationDelta *= -1;
		
		tf.Rotation += rotationDelta;
	}

}
