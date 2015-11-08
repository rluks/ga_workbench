package at.fhooe.im440.workbench;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

public class ServiceLocator {
	static LinkedHashMap<String, BaseSystem> systems;
	//TODO singleton?
	public static void Init(){
		systems = new LinkedHashMap<String, BaseSystem>();

	}
	
	//TODO should we update all of systems here?
	public static void Update(){
		for(Entry<String, BaseSystem> entry: systems.entrySet()){
			BaseSystem system = entry.getValue();
			system.Update();
		}
	}
	
	//TODO where will i initialize systems? 
	//TODO from wheere i call register?
	public static void Register(BaseSystem system){
		System.out.println("ServiceLocator Register:" + system.getClass().getSimpleName());
		systems.put(system.getClass().getSimpleName(), system);
	}
	
	public static void Unregister(BaseSystem system){
		systems.remove(system.getClass().getSimpleName());
	}
	
	public static BaseSystem Get(String systemName){
		return systems.get(systemName);
	}
}
