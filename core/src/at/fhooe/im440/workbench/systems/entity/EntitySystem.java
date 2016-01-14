package at.fhooe.im440.workbench.systems.entity;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;

import at.fhooe.im440.workbench.systems.BaseSystem;

public class EntitySystem extends BaseSystem {
	
	public LinkedHashMap<Long, Entity> Entities;
	Entity e;
	
	public EntitySystem(){	
		Entities = new LinkedHashMap<Long, Entity>();
	}
	
	public void InitBoxEntities(){
	
		TextureAtlas ta = new TextureAtlas("boxes.pack");
		AtlasRegion ar1 = ta.findRegion("box_red");		
		TransformComponent tf1 = new TransformComponent();
		tf1.Rotation = 30;		
		tf1.Position = new Vector2(-20, 75);		
		Entity e1 = new Entity()
				.Name("Little red ridding box")
				.AddComponent(tf1)
				.AddComponent(new PhysicsComponent().ApplyForce(new Vector2(100, 0)))
				.AddComponent(new BoxColliderComponent())
				.AddComponent(new SpriteComponent(ar1));
		Entities.put(e1.Id, e1);
		System.out.println("redbox id:" + e1.Id);
			
		AtlasRegion ar2 = ta.findRegion("box_grey");		
		TransformComponent tf2 = new TransformComponent();
		tf2.Rotation = 0;		
		tf2.Position = new Vector2(0, 0);
		BoxColliderComponent bcc2 = new BoxColliderComponent();
		//bcc2.skipTesting = true;
		Entity e2 = new Entity()
				.Name("basic grey box")
				.AddComponent(tf2)
				.AddComponent(new PhysicsComponent().setAngularVelocity(15))
				.AddComponent(bcc2)
				.AddComponent(new SpriteComponent(ar2));
		Entities.put(e2.Id, e2);
		
		
	}
	
	public void InitTestEntities(){
		TextureAtlas ta = new TextureAtlas("mycogs.pack");
		AtlasRegion cogTex = ta.findRegion("cog_red");
		AtlasRegion cogNTex = ta.findRegion("cog_white");
		AtlasRegion cogSTex = ta.findRegion("cog_grey");
		
		TransformComponent tf1 = new TransformComponent();
		TransformComponent tf2 = new TransformComponent();
		TransformComponent tf3 = new TransformComponent();
		
		tf1.Position = new Vector2(100, 100);
		tf2.Position = new Vector2(-100, 100);
		tf3.Position = new Vector2(200, 100);
		

		 e = new Entity()
				.Name("Red cog wheel")
				.AddComponent(tf1)
				.AddComponent(new PhysicsComponent())
				.AddComponent(new CircleColliderComponent())
				.AddComponent(new SpriteComponent(cogTex));
		Entities.put(e.Id, e);
			
	
		
		//tf2.Position.x += 60.0f;
		 CircleColliderComponent ccc2 =  new CircleColliderComponent();
		 //ccc2.skipTesting = true;
		Entity e2 = new Entity()
				.Name("white cog")
				.AddComponent(tf2)
				.AddComponent(new PhysicsComponent())
				.AddComponent(ccc2)
				.AddComponent(new SpriteComponent(cogNTex))
				;
		Entities.put(e2.Id, e2);//TODO only register components when putting in the list?	
		
		
		InitSpringTest(e.Id, e2.Id, "red and white");
		
		
		//tf3.Position.x += 120.0f;
		CircleColliderComponent ccc3 =  new CircleColliderComponent();
		 //ccc3.skipTesting = true;
		Entity e3 = new Entity()
				.Name("Third wheel")
				.AddComponent(tf3)
				.AddComponent(new PhysicsComponent())
				.AddComponent(ccc3)
				.AddComponent(new SpriteComponent(cogSTex))
				;
		Entities.put(e3.Id, e3);//TODO only register components when putting in the list?
		
		InitSpringTest(e2.Id, e3.Id, "white and grey");
		
		InitSpringChain();
	}
	
	void InitSpringTest(long id1, long id2, String springName){
		Entity s = new Entity()
				.Name(springName)
				.AddComponent(new SpringComponent(id1, id2));
		Entities.put(s.Id, s);
	}
	
	void InitSpringChain(){
		TextureAtlas ta = new TextureAtlas("mycogs.pack");
		AtlasRegion cogTex = ta.findRegion("cog_red");
		
		TransformComponent tf = new TransformComponent();
		tf.Position = new Vector2(0, -50);
		CircleColliderComponent ccc0 =  new CircleColliderComponent();
		ccc0.skipTesting = true;
		Entity e0 = new Entity()
				.Name("chain cog" + 0)
				.AddComponent(tf)
				.AddComponent(new PhysicsComponent())
				.AddComponent(ccc0)
				.AddComponent(new SpriteComponent(cogTex))
				;
		Entities.put(e0.Id, e0);
		
		for(int i = 0; i < 10; i ++){
			TransformComponent tf1 = new TransformComponent();
			tf1.Position = new Vector2(0, i*50);
			CircleColliderComponent ccc1 =  new CircleColliderComponent();
			ccc1.skipTesting = true;
			Entity e1 = new Entity()
					.Name("chain cog" + i)
					.AddComponent(tf1)
					.AddComponent(new PhysicsComponent())
					.AddComponent(ccc1)
					.AddComponent(new SpriteComponent(cogTex))
					;
			Entities.put(e1.Id, e1);
			
			InitSpringTest(e0.Id, e1.Id, "chain" + i);
			
			e0 = e1;
		}
	}
	
	public Entity GetEntity(long id){
		return Entities.get(id);
	}
	
	public Entity GetEntity(String name){
		for(Entry<Long, Entity> entry: Entities.entrySet()){
			Entity currentEntity = entry.getValue();
			if(name == currentEntity.Name)
				return currentEntity;
		}
		
		return null;	
	}
	
	//TODO create Entitz
	
	public void RemoveEntity(long id){
		Entities.remove(id);//TODO deactive entity and its componenets / unregister in systteems
		//dont do this during update, postpone until finish
	}
	
	public Entity GetTest(){
		return Entities.getOrDefault(0, e);
	}
	
	public Collection<Entity> GetEntities(){
		return Entities.values();
	}

	@Override
	public void Update(float delta) {
		// TODO Auto-generated method stub
		
	}
	

	
}
