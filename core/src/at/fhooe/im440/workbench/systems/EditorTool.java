package at.fhooe.im440.workbench.systems;

import com.badlogic.gdx.math.Vector2;

import at.fhooe.im440.workbench.systems.entity.Entity;

public abstract class EditorTool {

	abstract public void applyDrag(Entity e, Vector2 v);

}
