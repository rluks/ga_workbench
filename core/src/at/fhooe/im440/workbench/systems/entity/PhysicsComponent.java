package at.fhooe.im440.workbench.systems.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import at.fhooe.im440.workbench.ServiceLocator;
import at.fhooe.im440.workbench.systems.PhysicsSystem;
import at.fhooe.im440.workbench.systems.VectorRenderSystem;

/**
 * Moves objects according to their velocities
 * @author Roman
 *
 */
public class PhysicsComponent extends Component{

	
	public float AngularVelocity;
	public Vector2 Velocity;
	public float Mass;
	
	Vector2 oldAcceleration;
	
	public Vector2 Acceleration;
	private Vector2 _accelerationAccumulator;
	float _dampening = 0.01f;
	
	private VectorRenderSystem vrs;
	
	public PhysicsComponent(){
		this.AngularVelocity = 0.0f;
		this.Velocity = new Vector2(0, 0);
		this.Mass = 1.0f;
		this.Acceleration = new Vector2(0, 0);
		this._accelerationAccumulator = new Vector2(0, 0);
		
		this.oldAcceleration = new Vector2(0, 0);;
		
		vrs = ServiceLocator.Get(VectorRenderSystem.class);
	}
	
	public void Update(float delta){
		if(Active){
			TransformComponent tc = this.Entity().GetComponent(TransformComponent.class);
			//TODO check null, keep reference
			
			tc.Rotation = getNewRotation(tc, delta);		
			
			//clean it, apply forces (sum up resulting accelerations, and use it for calculation
			this.oldAcceleration.set(this.Acceleration);
			Vector2 a = getNewAcceleration();	
			
			Vector2 v0 = this.Velocity.cpy();
			Vector2 pos0 = tc.Position.cpy();
			
			//euler
			//v.x = v.x + a.x * delta;
			//v.y = v.y + a.y * delta;			
			//pos.x = pos.x + v.x * delta;
			//pos.y = pos.y + v.y * delta;
			
			//velocity verlet - use this one, store previous acceleration
			//v1 = v0 + (a0+a1)/2 . dt
			//x1 = x0 + v0.delta + a0/2 . dt^2
			
			Vector2 accSum = this.oldAcceleration.cpy().add(a);
			Vector2 v = (accSum.scl(0.5f)).scl(delta);
			v.add(v0);
			Vector2 pos = pos0.add(v0.scl(delta));
			pos.add(this.oldAcceleration.cpy().scl(0.5f).scl(delta * delta));
			
			//dampening
			Vector2 dampening = new Vector2();
			dampening.set(v);
			dampening.scl(_dampening);
			v.sub(dampening);
			
			//set new values
			this.Acceleration.set(a);
			this.Velocity.set(v);
			tc.Position.set(pos);
			
			if(showDebug)
				//System.out.println("PhysicsComponent: Acceleration:" + Acceleration);
				//System.out.println("PhysicsComponent: Velocity:" + Velocity);
				//System.out.println("PhysicsComponent: pos:" + pos);
				vrs.RenderVector(pos, pos.cpy().add(Velocity), Color.RED);
			

		}
		
		
	}
	
	public PhysicsComponent ApplyForce(Vector2 f){
		//F = m . a
		//a = F/m
		
		//if(this.Entity() != null){
		//	if(showDebug)
		//		System.out.println(this.Entity().Name + " received force " + f);
		//}		
		
		this._accelerationAccumulator.x += f.x/this.Mass;
		this._accelerationAccumulator.y += f.y/this.Mass;
		//System.out.println("PhysicsComponent: _accelerationAccumulator:" + _accelerationAccumulator);
		
		return this;
	}
	
	private Vector2 getNewAcceleration(){
		Vector2 a = new Vector2();
		a.set(this._accelerationAccumulator);
		this._accelerationAccumulator.set(0, 0);
		return a;
	}
	
	private float getNewRotation(TransformComponent tc, float delta){
		float rotation = tc.Rotation + this.AngularVelocity * delta;
		rotation = clampRotation(rotation);
		return rotation;
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
