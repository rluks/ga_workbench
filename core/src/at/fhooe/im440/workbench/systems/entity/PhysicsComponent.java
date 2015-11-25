package at.fhooe.im440.workbench.systems.entity;

import at.fhooe.im440.workbench.ServiceLocator;
import at.fhooe.im440.workbench.systems.PhysicsSystem;

/**
 * Moves objects according to their velocities
 * @author Roman
 *
 */
public class PhysicsComponent extends Component{

	public float AngularVelocity;
	
	public void Update(float delta){
		if(Active){
			TransformComponent tc = this.Entity().GetComponent(TransformComponent.class);
			//TODO check null
			
			float rotation = tc.Rotation + this.AngularVelocity * delta;

			if(rotation > 360.0f){
				rotation = rotation - 360.0f;
			}
			
			if(rotation < 0.0f){
				rotation = 360.0f + rotation;
			}
			
			//System.out.println("PhysicsComponent: AngularVelocity:" + AngularVelocity);
//			/System.out.println("PhysicsComponent: Rotation:" + tc.Rotation);
			tc.Rotation = rotation;
		}
		
		
	}
	
	public PhysicsComponent setAngularVelocity(float av){
		this.AngularVelocity = av;
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
}
