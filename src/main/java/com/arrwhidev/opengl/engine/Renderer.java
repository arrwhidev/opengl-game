package com.arrwhidev.opengl.engine;

import com.arrwhidev.opengl.engine.ecs.entity.Entity;
import com.arrwhidev.opengl.engine.ecs.entity.EntityManager;
import static org.lwjgl.opengl.GL11.*;

public abstract class Renderer {

    protected Camera camera;

    public Renderer(Camera camera) {
        this.camera = camera;
    }

    public final void render(float interpolation) {
        clear();
        windowResized();
        renderEntities(interpolation);
    }

    public abstract void renderEntity(Entity e, float interpolation);

    private void renderEntities(float interpolation) {
        for (Entity e : EntityManager.getEntities()) {
            renderEntity(e, interpolation);
        }
    }

    private final void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    private final void windowResized() {
        // TODO
        // if (window.isResized()) {
        //     glViewport(0, 0, window.getWidth(), window.getHeight());
        //     window.setResized(false);
        // }
    }
}
