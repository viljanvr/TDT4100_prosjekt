package asteroids;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Collection;

public class Asteroid extends Sprite {

    public Asteroid() {
        this(Math.random());
    }

    // Constructor for Large Asteroids.
    private Asteroid(double randomNumber) {
        super(randomNumber > 0.5 ? Math.random() * AsteroidsController.CANVASHEIGHT : -64,
                randomNumber < 0.5 ? Math.random() * AsteroidsController.CANVASHEIGHT : -64,
                1, Math.random() * 6.28, 54, 55,
                "asteroids/asteroid.png");

    }

    // Constructor for Dwarf Asteroids
    public Asteroid(int x1, int y1, Vector velocity) {
        super(x1, y1, 1.4, Math.random() * 6.28, 37, 38, "asteroids/dwarf_asteroid.png");
        getVelocity().addXY(velocity.getX(), velocity.getY());
    }

    public Collection<Sprite> splitLargeAsteroid() {
        Collection<Sprite> list = new ArrayList<>();
        list.add(new Asteroid((int) getPosX(), (int) getPosY(), getVelocity()));
        list.add(new Asteroid((int) getPosX(), (int) getPosY(), getVelocity()));
        list.add(new Asteroid((int) getPosX(), (int) getPosY(), getVelocity()));
        return list;
    }

    @Override
    public void updatePosition() {
        super.updatePosition();
        wrap();
    }

    public Boolean checkCollision(Collection<Sprite> list) {
        return list.stream().filter(sprite -> (sprite instanceof Laser || sprite instanceof Spaceship))
                .anyMatch(sprite -> this.overlapsSprite(sprite));
    }

    public Boolean isLarge() {
        return getImageWidth() == 54;
    }
}
