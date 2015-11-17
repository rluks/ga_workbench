package at.fhooe.im440.workbench.systems;

public class FakeSystem extends BaseSystem{

	public void Foo(){
		System.out.println("Bar");
	}

	@Override
	public void Update(float delta) {
		Foo();
	}

	@Override
	public void LateUpdate() {
		// TODO Auto-generated method stub
		
	}
}
