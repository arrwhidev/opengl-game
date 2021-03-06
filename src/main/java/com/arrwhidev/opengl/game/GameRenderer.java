package com.arrwhidev.opengl.game;

import com.arrwhidev.opengl.engine.Camera;
import com.arrwhidev.opengl.engine.Renderer;
import com.arrwhidev.opengl.engine.ecs.component.mesh.HasMesh;
import com.arrwhidev.opengl.engine.ecs.component.mesh.Mesh;
import com.arrwhidev.opengl.engine.ecs.component.mesh.MeshComponentManager;
import com.arrwhidev.opengl.engine.ecs.entity.Entity;
import com.arrwhidev.opengl.engine.input.keyboard.KeyboardInput;
import com.arrwhidev.opengl.engine.shader.ShaderProgram;
import com.arrwhidev.opengl.game.ecs.components.movement.HasMovement;
import com.arrwhidev.opengl.game.ecs.components.movement.Movement;
import com.arrwhidev.opengl.game.ecs.components.movement.MovementComponentManager;
import com.arrwhidev.opengl.game.ecs.components.position.HasPosition;
import com.arrwhidev.opengl.game.ecs.components.position.Position;
import com.arrwhidev.opengl.game.ecs.components.position.PositionComponentManager;
import com.arrwhidev.opengl.game.ecs.misc.Transformation;
import com.arrwhidev.opengl.game.shader.Shaders;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;

public class GameRenderer extends Renderer {

    public GameRenderer(Camera camera) {
        super(camera);
    }

    @Override
    public void renderEntity(Entity e) {
        if (e instanceof HasMesh && e instanceof HasPosition && e instanceof HasMovement) {
            Mesh mesh = MeshComponentManager.get((HasMesh) e);
            Position position = PositionComponentManager.get((HasPosition) e);
            Movement movement = MovementComponentManager.get((HasMovement) e);

            render(mesh, position, movement, mesh.getSp());

            if (KeyboardInput.instance().isDown(GLFW_KEY_DOWN)) {
                render(mesh, position, movement, Shaders.AABB.getShader());
            }
        }
    }

    private void render(Mesh mesh, Position position, Movement movement, ShaderProgram shaderProgram) {
        shaderProgram.bind();
        shaderProgram.render(camera, mesh, Transformation.getModelMatrix(camera, position, movement));
        shaderProgram.unbind();
    }
}
