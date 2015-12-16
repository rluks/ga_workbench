package at.fhooe.im440.workbench.systems.entity;

import com.badlogic.gdx.math.Vector2;

import at.fhooe.im440.workbench.ServiceLocator;
import at.fhooe.im440.workbench.systems.PhysicsSystem;

/**
 * Moves objects according to their velocities
 * @author Roman
 *
 */
public class PhysicsComponent extends Component{

	public float AngularVelocity;
	public Vector2 Velocity;
	
	public PhysicsComponent(){
		this.Velocity = new Vector2(0, 0);
	}
	
	public void Update(float delta){
		if(Active){
			TransformComponent tc = this.Entity().GetComponent(TransformComponent.class);
			//TODO check null, keep reference
			
			float rotation = tc.Rotation + this.AngularVelocity * delta;
			rotation = clampRotation(rotation);
			tc.Rotation = rotation;
			
			Vector2 pos = tc.Position.cpy();
			
			pos.x = pos.x + this.Velocity.x * delta;
			pos.y = pos.y + this.Velocity.y * delta;
			
			tc.Position = pos;
			
			System.out.println("PhysicsComponent: Velocity:" + Velocity);
			System.out.println("PhysicsComponent: pos:" + tc.Position);
		}
		
		
	}
	
	public PhysicsComponent setAngularVelocity(float av){
		this.AngularVelocity = av;
		return this;
	}
	
	public PhysicsComponent setVelocity(Vector2 v){
		this.Velocity = v;
		return this;
	}

	@Override
	void Register() {
		PhysicsSystem s = ServiceLocator.Get(PhysicsSystem.class);
		s.Register(this);	
	}

	@Override
	void Unregister() {
		// TODO Auto-generated method stub
		
	}
	
	float clampRotation(float rotation){
		if(rotation > 360.0f){
			rotation = rotation - 360.0f;
		}
		
		if(rotation < 0.0f){
			rotation = 360.0f + rotation;
		}
		return rotation;
	}
}
