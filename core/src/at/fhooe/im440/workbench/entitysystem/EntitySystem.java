package at.fhooe.im440.workbench.entitysystem;

import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.Texture;

public class EntitySystem {
	public static LinkedHashMap<Long, Entity> Entities;
	static Entity e;
	
	public static void Init(){
		Entities = new LinkedHashMap<Long, Entity>();
		 e = new Entity()
				.Name("x")
				.AddComponent(new TransformComponent())
				.AddComponent(new SpriteComponent(new Texture("x.png")));
		Entities.put(e.Id, e);
	}
	public static Entity GetEntity(long id){
		return Entities.get(id);
	}
	
	public static Entity GetTest(){
		return Entities.getOrDefault(0, e);
	}
	
}
