package asteroids;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AsteroidTest {

    private Asteroid asteroid;
    private Asteroid randomAsteroid;
    Collection<Sprite> sprites;

    @BeforeEach
    public void setup() {
        asteroid = new Asteroid(300, 300, new Vector(0, 0));
        randomAsteroid = new Asteroid();
        sprites = new ArrayList<>();
    }

    @Test
    @DisplayName("Test random asteroid constructor")
    public void testConstructor() {

        Boolean isAsteroidRandom;
        if (randomAsteroid.getPosX() == -64) {
            if (randomAsteroid.getPosY() >= 0 || randomAsteroid.getPosY() <= AsteroidsController.CANVASHEIGHT)
                isAsteroidRandom = true;
            else
                isAsteroidRandom = false;
        } else {
            if (randomAsteroid.getPosY() == -64)
                isAsteroidRandom = true;
            else
                isAsteroidRandom = false;
        }
        assertTrue(isAsteroidRandom);
    }

    @Test
    @DisplayName("Tests splitLargeAsteroid function")
    public void splitLargeAsteroidTest() {
        sprites.addAll(asteroid.splitLargeAsteroid());
        assertEquals(3, sprites.size(), "Checks if the astroids was split into three dwarf asteriods");
        assertTrue(sprites.stream()
                .allMatch(sprite -> sprite instanceof Asteroid && sprite.getPosX() == 300 && sprite.getPosY() == 300),
                "Checks if the attributes of dwarf asteroids matcht with the parent asteroids");
    }

    @Test
    @DisplayName("Tests the collision detector function")
    public void checkCollisionTest() {
        Collection<Sprite> sprites = new ArrayList<>();
        sprites.add(new Asteroid(305, 305, new Vector(0, 0)));
        assertFalse(asteroid.checkCollision(sprites), "Checks collision with another asteroid");
        sprites.add(new Laser(305, 305, 0, 0));
        assertTrue(asteroid.checkCollision(sprites), "Checks collision with a laser");
        sprites.clear();
        sprites.add(new Spaceship());
        asteroid.setPosXY(400, 300);
        assertTrue(asteroid.checkCollision(sprites), "Checks collision with a spaceship");
    }

    @Test
    @DisplayName("Tests the isLarge function")
    public void isLargeTest() {
        assertFalse(asteroid.isLarge());
        assertTrue(randomAsteroid.isLarge());
    }

}
