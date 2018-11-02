package com.arrwhidev.opengl.game;

import com.arrwhidev.opengl.engine.Window;
import com.arrwhidev.opengl.engine.input.mouse.MouseInput;
import com.arrwhidev.opengl.engine.input.mouse.event.MouseClickEvent;
import com.arrwhidev.opengl.game.ecs.entities.player.PlayerFactory;
import com.arrwhidev.opengl.game.ecs.entities.quad.QuadFactory;

import static com.arrwhidev.opengl.engine.input.mouse.event.MouseClickEvent.Action.PRESS;

public class TestEntityFactory {

    public void createTestEntitiesFromMouseEvents() {
        MouseInput.instance().subscribe(event -> {
            switch (event.getType()) {
                case CLICK:
                    MouseClickEvent mce = (MouseClickEvent) event;
                    if (mce.getAction().equals(PRESS) && MouseInput.instance().isInWindow()) {
                        QuadFactory.createRandom(MouseInput.instance().getPosition());
                    }
                    break;
                default:
                    break;
            }
        });
    }

    public void createTestEntities(Window window) {
        PlayerFactory.create(100, 100);

//        QuadFactory.createRandom(
//                new Vector2f(400, 400),
//                new Vector2f(160, 100),
//                10);
//
//        QuadFactory.createRandom(
//                new Vector2f(500, 500),
//                new Vector2f(-25, -25),
//                70);
    }
}
