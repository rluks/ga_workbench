package at.fhooe.im440.workbench.entitysystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class SpriteComponent extends Component {

	public static final String NAME = "SpriteComponent";

	public Texture Texture;
	public Sprite Sprite;
	
	public SpriteComponent(com.badlogic.gdx.graphics.Texture texture) {
		this.Texture = texture;
		this.Sprite = new Sprite(texture);
	}

	public void Render(Batch batch){
		if(Active){
			float degrees = ((TransformComponent)this.Entity().GetComponent(TransformComponent.NAME)).Rotation;//TODO hell xD
			this.Sprite.setAlpha(0.5f);
			this.Sprite.setRotation(degrees);
			this.Sprite.draw(batch);
		}
	}
	
}
