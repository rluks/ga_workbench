package at.fhooe.im440.workbench;

import java.util.ArrayList;
import java.util.List;

import at.fhooe.im440.workbench.entitysystem.EntitySystem;
import at.fhooe.im440.workbench.entitysystem.PhysicsComponent;
import at.fhooe.im440.workbench.entitysystem.SpriteComponent;

public class PhysicsSystem {
	static List<PhysicsComponent> physics;
	
	public static void Init(){
		physics = new ArrayList<PhysicsComponent>();
		PhysicsComponent pc = (PhysicsComponent) EntitySystem.GetTest().GetComponent("PhysicsComponent");
		physics.add(pc);
	}
	
	public static void Update(float delta){
		for(PhysicsComponent pc: physics){
			pc.AngularVelocity=14.5f;
			pc.Update(delta);
		}
	}
}
