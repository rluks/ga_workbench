package at.fhooe.im440.workbench.systems.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import at.fhooe.im440.workbench.ServiceLocator;
import at.fhooe.im440.workbench.systems.PhysicsSystem;
import at.fhooe.im440.workbench.systems.VectorRenderSystem;

public class SpringComponent extends PhysicsComponent {

	float BaseLength;
	float CurrentLength;
	float Stiffness;
	Entity e1;
	Entity e2;
	
	VectorRenderSystem vrs;
	
	public SpringComponent(long id1, long id2){
		EntitySystem es = ServiceLocator.Get(EntitySystem.class);
		this.e1 = es.GetEntity(id1);
		this.e2 = es.GetEntity(id2);
		
		System.out.println("Creating spring entity between " + es.GetEntity(id1).Name + " and " + es.GetEntity(id2).Name);
		
		Vector2 pos1 = this.e1.GetComponent(TransformComponent.class).Position.cpy();
		Vector2 pos2 = this.e2.GetComponent(TransformComponent.class).Position.cpy();
		this.BaseLength = pos1.sub(pos2).len();
		this.CurrentLength = this.BaseLength;
		
		this.Stiffness = 1.0f;
		
		this.vrs = ServiceLocator.Get(VectorRenderSystem.class);
	}
	
	public void Update(float delta){
		
		
		
		Vector2 pos1 = this.e1.GetComponent(TransformComponent.class).Position.cpy();
		Vector2 pos2 = this.e2.GetComponent(TransformComponent.class).Position.cpy();
		vrs.RenderVector(pos1, pos2, Color.GREEN);
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
