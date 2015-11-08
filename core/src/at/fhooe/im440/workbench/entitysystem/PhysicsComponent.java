package at.fhooe.im440.workbench.entitysystem;

public class PhysicsComponent extends Component{

	public float AngularVelocity;
	
	public void Update(float delta){
		if(Active){
			TransformComponent tc = (TransformComponent) this.Entity().GetComponent("TransformComponent");
			//TODO check null
			tc.Rotation += this.AngularVelocity * delta;
			//TODO 360 if 0
			//System.out.println("PhysicsComponent: AngularVelocity:" + AngularVelocity);
			System.out.println("PhysicsComponent: Rotation:" + tc.Rotation);
		}
		
		
	}
}
