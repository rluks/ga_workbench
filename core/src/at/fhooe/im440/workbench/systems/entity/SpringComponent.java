package at.fhooe.im440.workbench.systems.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import at.fhooe.im440.workbench.ServiceLocator;
import at.fhooe.im440.workbench.systems.PhysicsSystem;
import at.fhooe.im440.workbench.systems.VectorRenderSystem;

public class SpringComponent extends PhysicsComponent {

	float BaseLength;
	float CurrentLength;
	float Stiffness;//What values are reasonable?
	Entity e1;
	PhysicsComponent pc1;
	Entity e2;
	PhysicsComponent pc2;
	
	VectorRenderSystem vrs;
	
	public SpringComponent(long id1, long id2){
		EntitySystem es = ServiceLocator.Get(EntitySystem.class);
		this.e1 = es.GetEntity(id1);
		this.e2 = es.GetEntity(id2);
		this.pc1 = this.e1.GetComponent(PhysicsComponent.class);
		this.pc2 = this.e2.GetComponent(PhysicsComponent.class);
		
		System.out.println("Creating spring entity between " + es.GetEntity(id1).Name + " and " + es.GetEntity(id2).Name);
		
		Vector2 pos1 = this.e1.GetComponent(TransformComponent.class).Position.cpy();
		Vector2 pos2 = this.e2.GetComponent(TransformComponent.class).Position.cpy();
		//this.BaseLength = pos1.sub(pos2).len();
		this.BaseLength = pos1.dst(pos2);
		this.CurrentLength = this.BaseLength;
		
		this.Stiffness = 30f;
		
		this.vrs = ServiceLocator.Get(VectorRenderSystem.class);
	}
	
	public void Update(float delta){
		Vector2 pos1 = this.e1.GetComponent(TransformComponent.class).Position.cpy();
		Vector2 pos2 = this.e2.GetComponent(TransformComponent.class).Position.cpy();
		
		if(showDebug)
			vrs.RenderVector(pos1, pos2, Color.GREEN, 2);
		
		Vector2 dir = new Vector2(pos1.cpy().sub(pos2));
		dir.nor().scl(0.5f);//half force for each entity
		
		this.CurrentLength = pos1.dst(pos2);
		
		//F = -kx
		float force = -this.Stiffness * (this.BaseLength-this.CurrentLength);
		
		if(showDebug)
			System.out.println("spring force " + force);
		
		this.pc1.ApplyForce(dir.cpy().scl(-force));
		this.pc2.ApplyForce(dir.cpy().scl(force));
		
	}
	
	@Override
	void Register() {
		//spring in physics system or where?
		PhysicsSystem s = ServiceLocator.Get(PhysicsSystem.class);
		s.Register(this);
	}

	@Override
	void Unregister() {
		// TODO Auto-generated method stub

	}

}
