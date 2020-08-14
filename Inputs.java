import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

public class Inputs {

    public static void initializeKeys(InputManager inputManager, ActionListener actionListener, AnalogListener analogListener) {
        // You can map one or several inputs to one named action
        inputManager.addMapping("w", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("a", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("s", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("d", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("shift", new KeyTrigger(KeyInput.KEY_LSHIFT));
        inputManager.addMapping("space", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping("g", new KeyTrigger(KeyInput.KEY_G));

        // Add the names to the action listener.
        //inputManager.addListener(actionListener, "Pause");
        inputManager.addListener(analogListener, "w", "a", "s", "d", "shift", "space", "g");
    }

    private static int maxSpeed = mod_opticore_main.getOc_maxSpeed();
    public static void handleKeys(Camera cam, float tpf, String name, float value ){
        float dir = 0;
        int run2D = 1;
        switch(name) {
            case "w":
                dir = 0;
                break;
            case "s":
                dir = FastMath.PI;
                break;
            case "a":
                dir = FastMath.HALF_PI;
                break;
            case "d":
                dir = FastMath.HALF_PI + FastMath.PI;
                break;
            case "g":
                Vector3f pPos = new Vector3f(Player.getPos());
                Vector3f nPos = new Vector3f(pPos.x, pPos.y - 1, pPos.z);
                Player.setPos(nPos);
            default:
                run2D = 0;
                break;
        }


        switch (run2D) {
            case 1:
                Vector3f inertia = Player.getInertia();

                float[] rot = new float[3];
                cam.getRotation().normalizeLocal().toAngles(rot);

                float x = FastMath.sin(rot[1] + dir);
                float z = FastMath.cos(-rot[1] - dir);
                inertia.x += x * 100 * tpf;
                inertia.z += z * 100 * tpf;

                if(FastMath.abs(inertia.x) < maxSpeed && FastMath.abs(inertia.z) < maxSpeed) {
                    Player.setInertia(inertia);
                } else {
                    Vector3f normalInert = inertia.normalize().mult(maxSpeed);
                    Player.setInertia(new Vector3f(normalInert.x,Player.getInertia().y, normalInert.z));
                }

                break;
            case 0:
                int move = 0;
                switch (name){
                    case "space":
                        move = 1;
                        break;
                    case "shift":
                        if(mod_opticore_main.getOc_isFlyEnabled()){
                            move = -1;
                        }
                        break;
                }

                if(move==0){
                    return;
                }

                if(Player.isOnGround()) {
                    if(mod_opticore_main.isOc_generalDebugOutput()){
                        System.out.println(mod_opticore_main.oc_getCoreOutputSignature() + "Trying to jump");
                    }
                    Player.setJumpBuffer();
                }
                break;
        }

    }
}