package at.fhooe.im440.workbench.entitysystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class SpriteComponent extends Component {

	public Texture Texture;
	public Sprite Sprite;
	
	public SpriteComponent(com.badlogic.gdx.graphics.Texture texture) {
		Texture = texture;
		Sprite = new Sprite(texture);
	}

	public void Render(Batch batch){
		if(Active){
			this.Sprite.draw(batch);
		}
	}
	
}
