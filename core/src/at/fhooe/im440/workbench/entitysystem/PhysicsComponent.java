package at.fhooe.im440.workbench.entitysystem;

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
			tc.Rotation += this.AngularVelocity * delta;
			//TODO 360 if 0
			//System.out.println("PhysicsComponent: AngularVelocity:" + AngularVelocity);
			//System.out.println("PhysicsComponent: Rotation:" + tc.Rotation);
		}
		
		
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
