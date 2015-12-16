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
	
	Vector2 oldPosition;
	
	public Vector2 Acceleration;
	private Vector2 _accelerationAccumulator;
	
	public PhysicsComponent(){
		this.AngularVelocity = 0.0f;
		this.Velocity = new Vector2(0, 0);
		this.Mass = 1.0f;
		this.Acceleration = new Vector2(0, 0);
		this._accelerationAccumulator = new Vector2(0, 0);
		
		this.oldPosition = new Vector2(0, 0);
	}
	
	public void Update(float delta){
		if(Active){
			TransformComponent tc = this.Entity().GetComponent(TransformComponent.class);
			//TODO check null, keep reference
			
			tc.Rotation = getNewRotation(tc, delta);
			
			
			//clean it, apply forces (sum up resulting accelerations, and use it for calculation
			Vector2 a = getNewAcceleration();	
			
			Vector2 v = this.Velocity.cpy();
			Vector2 pos = tc.Position.cpy();
			
			//euler
			//v.x = v.x + a.x * delta;
			//v.y = v.y + a.y * delta;			
			//pos.x = pos.x + v.x * delta;
			//pos.y = pos.y + v.y * delta;
			
			//velocity verlet
			//v1 = v0 . (a0+a1)/2 . dt
			//x1 = x0 + v0.delta + a0/2 . dt^2
			//how do i get a1?
			
			//verlet integration
			//x1 = 2x0 - x_old + a.dt^2
			//x_old = x0
			pos.scl(2);
			pos.sub(this.oldPosition);
			pos.add(a.scl(delta*delta));
			this.oldPosition = tc.Position.cpy();
			
			
			this.Acceleration.set(a);
			this.Velocity.set(v);
			tc.Position.set(pos);
			
			//System.out.println("PhysicsComponent: Acceleration:" + Acceleration);
			System.out.println("PhysicsComponent: Velocity:" + Velocity);
			//System.out.println("PhysicsComponent: pos:" + pos);
			
			VectorRenderSystem vrs = ServiceLocator.Get(VectorRenderSystem.class);
			vrs.RenderVector(pos, pos.cpy().add(Velocity), Color.RED);
			

		}
		
		
	}
	
	public void ApplyForce(Vector2 f){
		//F = m . a
		//a = F/m
		this._accelerationAccumulator.x += f.x/this.Mass;
		this._accelerationAccumulator.y += f.y/this.Mass;
		System.out.println("PhysicsComponent: _accelerationAccumulator:" + _accelerationAccumulator);
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
