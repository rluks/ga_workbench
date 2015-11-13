package at.fhooe.im440.workbench.systems.entity;

import com.badlogic.gdx.math.Vector2;

public class TransformComponent extends Component {
	
	public Vector2 Position;
	public Vector2 Scale;
	public float Rotation;//Angle
	
	public TransformComponent() {
		Position = new Vector2();
		Scale = new Vector2();
		Rotation = 0;
	}

	@Override
	void Register() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void Unregister() {
		// TODO Auto-generated method stub
		
	}
	
	
}
